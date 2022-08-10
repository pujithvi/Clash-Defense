package com.example.towerdefense;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameScreen extends AppCompatActivity {
    private static Player player;
    private int milliSeconds = 0;
    private TextView money;
    private TextView health;
    private TextView cannon1Cost;
    private TextView cannon2Cost;
    private TextView cannon3Cost;
    private ImageButton cannon1;
    private ImageButton cannon2;
    private ImageButton cannon3;
    private ImageView img;
    private ImageButton place1ImageButton;
    private ImageButton place2ImageButton;
    private ImageButton place3ImageButton;
    private ImageButton place4ImageButton;
    private ImageButton place5ImageButton;
    private Button cancelButton;
    private Button startCombatButton;
    private ArrayList<Place> places;
    private final ArrayList<Integer[]> placeCoords = new ArrayList<>();
    private final ArrayList<Place> existingPlaces = new ArrayList<>();
    private ArrayList<Place> cannonsPlaced;
    private Place place1;
    private Place place2;
    private Place place3;
    private Place place4;
    private Place place5;
    private Tower cannonSelected;
    private int layout;
    private Path path;
    private ImageView witch;
    private ImageView wizard;
    private ImageView archer;
    private RelativeLayout layoutParent;
    private LayoutInflater layoutInflater;
    private View newView;
    private int enemyMade;
    private int enemyPassed;
    private boolean bossDead = false;

    public static String deployRightEnemy(int num, Player player) {
        Difficulty difficultyObj = new Difficulty(player);
        String enemyType = "";
        if (num >= 0 && num < difficultyObj.getNumArchers()) {
            enemyType = "archer";
        } else if (num < difficultyObj.getNumArchers()
            + difficultyObj.getNumWitches()) {
            enemyType = "witch";
        } else if (num < difficultyObj.getNumArchers()
            + difficultyObj.getNumWitches() + difficultyObj.getNumWizards()){
            enemyType = "wizard";
        } else {
            enemyType = "boss";
        }
        return enemyType;
    }

    public static float pxFromDp(double d) {
        float scale = 432f;
        float dp = (float) d;
        float px = dp * (scale / 160);
        return px;
    }

    public static boolean inRange(int x1, int x2, int y1, int y2, int radius) {
        return getDistance(x1, x2, y1, y2) <= radius;
    }

    public static double getDistance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                setMilliSeconds(getMilliSeconds() + 1);
            }
        };
        timer.scheduleAtFixedRate(task, 1, 1);
        super.onCreate(savedInstanceState);
        String nameInputted = getIntent().getStringExtra("nameInputted");
        String difficulty = getIntent().getStringExtra("difficulty");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        player = new Player(difficulty, nameInputted);
        img = (ImageView) findViewById(R.id.imageView5);
        Difficulty difficultyObj = new Difficulty(player);
        difficultyObj.setPath();
        layout = difficultyObj.getLayout();
        path = difficultyObj.getPath();
        setContentView(layout);
        money = findViewById(R.id.money3);
        health = findViewById(R.id.health3);
        money.setText("Money: " + player.getBalance());
        health.setText("Health: " + player.getMonumentHealth());
        cannon1 = (ImageButton) findViewById(R.id.cannon1);
        cannon2 = (ImageButton) findViewById(R.id.cannon2);
        cannon3 = (ImageButton) findViewById(R.id.cannon3);
        cancelButton = (Button) findViewById(R.id.cancel);
        cancelButton.setVisibility(View.GONE);
        Cannon1 cannon1Object = new Cannon1(player, cannon1);
        Cannon2 cannon2Object = new Cannon2(player, cannon2);
        Cannon3 cannon3Object = new Cannon3(player, cannon3);
        cannon1Cost = (TextView) findViewById(R.id.cannon1cost);
        cannon2Cost = (TextView) findViewById(R.id.cannon2cost);
        cannon3Cost = (TextView) findViewById(R.id.cannon3cost);
        cannon1Cost.setText("Cost: " + cannon1Object.getCost());
        cannon2Cost.setText("Cost: " + cannon2Object.getCost());
        cannon3Cost.setText("Cost: " + cannon3Object.getCost());
        setPlaceButtons();
        place1 = new Place(place1ImageButton, player);
        place2 = new Place(place2ImageButton, player);
        place3 = new Place(place3ImageButton, player);
        place4 = new Place(place4ImageButton, player);
        place5 = new Place(place5ImageButton, player);
        places = new ArrayList<>(Arrays.asList(place1, place2, place3, place4, place5));
        cannonsPlaced = new ArrayList<>();
        layoutParent = (RelativeLayout) findViewById(R.id.RelativeLayout);
        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        startCombatButton = (Button) findViewById(R.id.startCombat);
        startCombatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCombatButton.setVisibility(View.GONE);
                ArrayList<Enemy> enemies = new ArrayList<>();
                for (int i = 0; i < cannonsPlaced.size(); i++) {
                    cannonsPlaced.get(i).getCannon().startTimer();
                }
                final Handler handler = new Handler();
                Runnable task = new Runnable() {
                    private int i = 0;
                    private int j = 0;
                    private boolean flag = false;
                    @Override
                    public void run() {
                        Enemy temp = new Enemy(deployRightEnemy(i, player));
                        if (j < temp.getTimeBetween() / 100 || flag) {
                            if (player.getMonumentHealth() > 0) {
                                int enemyDead = 0;
                                for (Enemy enemy : enemies) {
                                    if (player.getMonumentHealth() == 0) {
                                        gameOver();
                                    }
                                    View enemyView = enemy.getView();
                                    int enemyX = (int) enemyView.getX();
                                    int enemyY = (int) enemyView.getY();
                                    int i = 1;
                                    int arrInd = 0;
                                    for (Integer[] place : placeCoords) {
                                        if (inRange(enemyX, place[0], enemyY, place[1], 450)) {
                                            cannonsPlaced.get(arrInd)
                                                .attackEnemy(getMilliSeconds(),
                                                    arrInd, enemyView, money,
                                                    enemy);
                                        }
                                        arrInd++;
                                        i++;
                                    }
                                    if (enemy.getType().equals("boss") && enemy.getHealth() <= 0){
                                        bossDead = true;
                                    }
                                    enemyDead = player.getEnemyDefeated();
                                    if (enemyView.getX() == difficultyObj.getMonumentCoords()[0]
                                        &&
                                        enemyView.getY() == difficultyObj.getMonumentCoords()[1]) {
                                        if (enemyView.getVisibility() == View.VISIBLE) {
                                            enemy.attack(player);
                                            enemyPassed++;
                                            health.setText("Health: " + player.getMonumentHealth());
                                            if (player.getMonumentHealth() <= 0) {
                                                player.setMonumentHealth(100);
                                                gameOver();
                                                return;
                                            }
                                        }
                                        enemyView.setVisibility(View.GONE);
                                    }
                                }
                                if (enemyMade == (difficultyObj.getNumArchers() + difficultyObj.getNumWitches()
                                        + difficultyObj.getNumWizards() + difficultyObj.getNumBoss()) && enemyPassed + enemyDead  == (difficultyObj.getNumArchers()
                                        + difficultyObj.getNumWitches() + difficultyObj.getNumWizards() + difficultyObj.getNumBoss()) && bossDead) {
                                    win();
                                    return;
                                }
                            } else {
                                gameOver();
                            }
                            j++;
                            handler.postDelayed(this, 100);
                        } else {
                            newView = layoutInflater.inflate(temp.getLayout(), null, false);
                            newView.setLayoutParams(new RelativeLayout.LayoutParams(180, 200));
                            newView.setX(280);
                            newView.setY(60);
                            newView.setVisibility(View.VISIBLE);
                            layoutParent.addView(newView);
                            temp.setView(newView);
                            enemies.add(temp);
                            enemyMade++;
                            ObjectAnimator animator = ObjectAnimator.ofFloat(newView,
                                View.X, View.Y, path);
                            animator.setDuration(temp.getMovementSpeed());
                            animator.start();
                            i++;
                            j = 0;
                            if (i < (difficultyObj.getNumArchers()
                                + difficultyObj.getNumWitches() + difficultyObj.getNumWizards() +
                                difficultyObj.getNumBoss())) {
                                handler.post(this);
                            } else if (player.getMonumentHealth() > 0) {
                                flag = true;
                                handler.post(this);
                            }
                        }
                    }
                };
                handler.post(task);
            }
        });
        placeCannons(cannon1, cannon2, cannon3, cannon1Object, cannon2Object, cannon3Object);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cannonSelected != null) {
                    visibilityOff();
                }
                cancelButton.setVisibility(View.GONE);
            }
        });
    }

    private void setPlaceButtons() {
        place1ImageButton = (ImageButton) findViewById(R.id.place1);
        place2ImageButton = (ImageButton) findViewById(R.id.place2);
        place3ImageButton = (ImageButton) findViewById(R.id.place3);
        place4ImageButton = (ImageButton) findViewById(R.id.place4);
        place5ImageButton = (ImageButton) findViewById(R.id.place5);
    }

    private void placeCannons(ImageButton cannon1, ImageButton cannon2, ImageButton cannon3,
                              Cannon1 cannon1Object, Cannon2 cannon2Object, Cannon3 cannon3Object) {
        cannon1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (places.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All Places are Filled",
                        Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Shop.buyTower(cannon1Object, player)) {
                    placement(R.drawable.cannon1new);
                    updateMoney(player.getBalance());
                    cannonSelected = cannon1Object;
                } else {
                    insufficientFunds();
                }
            }
        });

        cannon2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (places.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All Places are Filled",
                        Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Shop.buyTower(cannon2Object, player)) {
                    placement(R.drawable.cannon2newnew);
                    updateMoney(player.getBalance());
                    cannonSelected = cannon2Object;
                } else {
                    insufficientFunds();
                }
            }
        });

        cannon3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (places.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All Places are Filled",
                        Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Shop.buyTower(cannon3Object, player)) {
                    placement(R.drawable.cannon3newnew);
                    updateMoney(player.getBalance());
                    cannonSelected = cannon3Object;
                } else {
                    insufficientFunds();
                }
            }
        });
    }

    public void placement(int imgRes) {
        Toast.makeText(getApplicationContext(), "Select Location",
            Toast.LENGTH_SHORT).show();
        for (Place b : places) {
            if (!b.isVisible()) {
                b.getPlace().setVisibility(View.VISIBLE);
                b.setVisible(true);
            }
        }
        cancelButton.setVisibility(View.VISIBLE);

        place1ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (place1.getCannonType() != null) {
                    showUpgradePopup(place1);
                } else {
                    places.remove(place1);
                    visibilityOff();
                    placeTower(place1ImageButton, place1, imgRes);
                    cannonsPlaced.add(place1);
                }

            }
        });

        place2ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (place2.getCannonType() != null) {
                    showUpgradePopup(place2);
                } else {
                    places.remove(place2);
                    visibilityOff();
                    placeTower(place2ImageButton, place2, imgRes);
                    cannonsPlaced.add(place2);
                }
            }
        });

        place3ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (place3.getCannonType() != null) {
                    showUpgradePopup(place3);
                } else {
                    places.remove(place3);
                    visibilityOff();
                    placeTower(place3ImageButton, place3, imgRes);
                    cannonsPlaced.add(place3);
                }
            }
        });

        place4ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (place4.getCannonType() != null) {
                    showUpgradePopup(place4);
                } else {
                    places.remove(place4);
                    visibilityOff();
                    placeTower(place4ImageButton, place4, imgRes);
                    cannonsPlaced.add(place4);
                }
            }
        });

        place5ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (place5.getCannonType() != null) {
                    showUpgradePopup(place5);
                } else {
                    places.remove(place5);
                    visibilityOff();
                    placeTower(place5ImageButton, place5, imgRes);
                    cannonsPlaced.add(place5);
                }
            }
        });

    }

    private void visibilityOff() {
        for (Place b : places) {
            b.getPlace().setVisibility(View.GONE);
            b.setVisible(false);
        }
    }

    private boolean placeTower(ImageButton button, Place place, int imgRes) {
        Integer resource = (Integer) button.getTag();
        if (resource != null) {
            towerAlreadyExists();
            return false;
        }

        Integer[] buttonCoords = new Integer[2];
        buttonCoords[0] = Math.round(button.getX());
        buttonCoords[1] = Math.round(button.getY());
        placeCoords.add(buttonCoords);

        button.setBackgroundColor(Color.TRANSPARENT);
        button.setImageResource(imgRes);
        if (imgRes == R.drawable.cannon1new) {
            place.setCannonType("Cannon1");
        } else if (imgRes == R.drawable.cannon2newnew) {
            place.setCannonType("Cannon2");
        } else {
            place.setCannonType("Cannon3");
        }
        button.setScaleType(ImageView.ScaleType.FIT_START);
        button.setTag(imgRes);
        cancelButton.setVisibility(View.GONE);
        player.updateBalance(-1 * cannonSelected.getCost());
        updateMoney(player.getBalance());
        return true;
    }

    private void insufficientFunds() {
        Toast.makeText(getApplicationContext(), "Insufficient Funds to Buy/Upgrade Tower",
            Toast.LENGTH_LONG).show();
    }

    private void towerAlreadyExists() {
        Toast.makeText(getApplicationContext(), "Tower already exists in this place!",
            Toast.LENGTH_LONG).show();
    }

    private void updateMoney(int mon) {
        money.setText("Money: " + mon);
    }

    public void gameOver() {
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }

    public void showUpgradePopup(Place place) {
        Tower tower = place.getCannon();
        if (tower.getLevel() < 3) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            RelativeLayout layoutParent2 = (RelativeLayout) findViewById(R.id.RelativeLayout);
            View customView = layoutInflater.inflate(R.layout.popupwindow,null);

            //instantiate popup window
            PopupWindow popupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            //display the popup window
            popupWindow.showAtLocation(layoutParent2, Gravity.CENTER, 0, 0);
            Button closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);
            Button upgradeBtn = (Button) customView.findViewById(R.id.upgradeBtn);

            TextView upgradeDetails = (TextView) customView.findViewById(R.id.upgradeDetails);
            upgradeDetails.setText("Attack Damage: " + tower.getAttackDamage() + "--> " + round(tower.getUpgradeMultiplier() * (double) tower.getAttackDamage()) + "\n" +
                "Attack Speed: " + tower.getAttackSpeed() + "-->" + round((double) tower.getAttackSpeed()/tower.getUpgradeMultiplier())
             + "\n\n" + "Cost: " + tower.getUpgradeCost());

            //upgradeDetails.setText(tower.getUpgradeMultiplier() + "");

            //close the popup window on button click
            closePopupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            upgradeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        tower.upgrade();
                        updateMoney(player.getBalance());
                        changeUpgradePicture(place);
                    } catch (IllegalArgumentException e) {
                        insufficientFunds();
                    }

                    popupWindow.dismiss();
                }
            });
        }
    }

    public void changeUpgradePicture(Place place) {
        if (place.getCannonType().equals("Cannon1")) {
            if (place.getCannon().getLevel() - 1 == 1) {
                place.getPlace().setImageResource(R.drawable.cannon1star);
                place.getCannon().setImgRes(R.drawable.cannon1star);
            } else if (place.getCannon().getLevel() - 1 == 2) {
                place.getPlace().setImageResource(R.drawable.cannon1star2);
                place.getCannon().setImgRes(R.drawable.cannon1star2);
            }
        } else if (place.getCannonType().equals("Cannon2")) {
            if (place.getCannon().getLevel() - 1 == 1) {
                place.getPlace().setImageResource(R.drawable.cannon2star);
                place.getCannon().setImgRes(R.drawable.cannon2star);
            } else if (place.getCannon().getLevel() - 1 == 2) {
                place.getPlace().setImageResource(R.drawable.cannon2star2);
                place.getCannon().setImgRes(R.drawable.cannon2star2);
            }

        } else if (place.getCannonType().equals("Cannon3")) {
            if (place.getCannon().getLevel() - 1 == 1) {
                place.getPlace().setImageResource(R.drawable.cannon3star);
                place.getCannon().setImgRes(R.drawable.cannon3star);
            } else if (place.getCannon().getLevel() - 1 == 2) {
                place.getPlace().setImageResource(R.drawable.cannon3star2);
                place.getCannon().setImgRes(R.drawable.cannon3star2);
            }
        }
    }
    public void win() {
        Intent intent = new Intent(this, Win.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }

    public int getMilliSeconds() {
        return milliSeconds;
    }

    public void setMilliSeconds(int milliSeconds) {
        this.milliSeconds = milliSeconds;
    }


    public static double round(double a) {
        return Math.round(a * 100.0) / 100.0;
    }
}
