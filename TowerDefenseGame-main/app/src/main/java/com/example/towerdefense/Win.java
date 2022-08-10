package com.example.towerdefense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Win extends AppCompatActivity {
    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_win_screen);

        Player player = (Player) getIntent().getSerializableExtra("player");
        TextView statistics = (TextView) findViewById(R.id.statistics);
        statistics.setText("Total Money Earned: " + player.getTotalMoneyEarned() + "\n" + "Upgrades Bought: " + player.getUpgradesBought() + "\n" + "Enemies Defeated: " + player.getEnemyDefeated());

        restartButton = (Button) findViewById(R.id.restart_button);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartActivity();
            }
        });
    }

    public void restartActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
