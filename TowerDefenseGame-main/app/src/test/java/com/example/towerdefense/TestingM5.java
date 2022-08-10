package com.example.towerdefense;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestingM5 {

    private final Player easyPlayer = new Player("easy", "easy player");
    private final Player dummyEasyPlayer = new Player("easy", "dummy easy player");
    private final Player mediumPlayer = new Player("medium", "medium player");
    private final Player dummyMediumPlayer = new Player("medium", "dummy medium player");
    private final Player hardPlayer = new Player("hard", "hard player");
    private final Player dummyHardPlayer = new Player("hard", "dummy hard player");
    private final Enemy witch = new Enemy("witch");
    private final Enemy wizard = new Enemy("wizard");
    private final Enemy archer = new Enemy("archer");


    @Test
    public void diffEnemyValue() {
        assertTrue(witch.getValue() != archer.getValue()
            && archer.getValue() != wizard.getValue()
            && wizard.getValue() != witch.getValue());
    }

    @Test
    public void witchAddBalance() {
        easyPlayer.addBalance(witch);
        mediumPlayer.addBalance(witch);
        hardPlayer.addBalance(witch);
        assertTrue((easyPlayer.getBalance()
            == (dummyEasyPlayer.getBalance() + witch.getValue()))
            && (mediumPlayer.getBalance()
            == (dummyMediumPlayer.getBalance() + witch.getValue()))
            && (hardPlayer.getBalance()
            == (dummyHardPlayer.getBalance() + witch.getValue())));
    }

    @Test
    public void wizardAddBalance() {
        easyPlayer.addBalance(wizard);
        mediumPlayer.addBalance(wizard);
        hardPlayer.addBalance(wizard);
        assertTrue((easyPlayer.getBalance()
            == (dummyEasyPlayer.getBalance() + wizard.getValue()))
            && (mediumPlayer.getBalance()
            == (dummyMediumPlayer.getBalance() + wizard.getValue()))
            && (hardPlayer.getBalance()
            == (dummyHardPlayer.getBalance() + wizard.getValue())));
    }

    @Test
    public void archerAddBalance() {
        easyPlayer.addBalance(archer);
        mediumPlayer.addBalance(archer);
        hardPlayer.addBalance(archer);
        assertTrue((easyPlayer.getBalance()
            == (dummyEasyPlayer.getBalance() + archer.getValue()))
            && (mediumPlayer.getBalance()
            == (dummyMediumPlayer.getBalance() + archer.getValue()))
            && (hardPlayer.getBalance()
            == (dummyHardPlayer.getBalance() + archer.getValue())));
    }

    @Test
    public void getDistanceTest() {
        int enemyX = 5;
        int enemyY = 0;
        int placeX = 0;
        int placeY = 0;
        double distance = GameScreen.getDistance(enemyX, placeX, enemyY, placeY);
        assertTrue(distance == 5);
    }

    @Test
    public void inRangeTestSuccess() {
        int enemyX = 5;
        int enemyY = 0;
        int placeX = 0;
        int placeY = 0;
        int placeRadius = 5;
        boolean range = GameScreen.inRange(enemyX, placeX, enemyY, placeY, placeRadius);
        assertTrue(range);
    }

    @Test
    public void inRangeTestFailure() {
        int enemyX = 5;
        int enemyY = 0;
        int placeX = 0;
        int placeY = 0;
        int placeRadius = 3;
        boolean range = GameScreen.inRange(enemyX, placeX, enemyY, placeY, placeRadius);
        assertFalse(range);
    }

    @Test
    public void easyEnemyOrder() {
        assertTrue(GameScreen.deployRightEnemy(0, easyPlayer).equals("archer")
            && (GameScreen.deployRightEnemy(1, easyPlayer)).equals("archer")
            && GameScreen.deployRightEnemy(2, easyPlayer).equals("witch")
            && GameScreen.deployRightEnemy(3, easyPlayer).equals("witch")
            && GameScreen.deployRightEnemy(4, easyPlayer).equals("wizard")
            && GameScreen.deployRightEnemy(5, easyPlayer).equals("wizard"));
    }

    @Test
    public void mediumEnemyOrder() {
        assertTrue(GameScreen.deployRightEnemy(0, mediumPlayer).equals("archer")
            && (GameScreen.deployRightEnemy(1, mediumPlayer)).equals("archer")
            && (GameScreen.deployRightEnemy(2, mediumPlayer)).equals("archer")
            && GameScreen.deployRightEnemy(3, mediumPlayer).equals("witch")
            && GameScreen.deployRightEnemy(4, mediumPlayer).equals("witch")
            && GameScreen.deployRightEnemy(5, mediumPlayer).equals("witch")
            && GameScreen.deployRightEnemy(6, mediumPlayer).equals("wizard")
            && GameScreen.deployRightEnemy(7, mediumPlayer).equals("wizard")
            && GameScreen.deployRightEnemy(8, mediumPlayer).equals("wizard"));
    }

    @Test
    public void hardEnemyOrder() {
        assertTrue(GameScreen.deployRightEnemy(0, hardPlayer).equals("archer")
            && (GameScreen.deployRightEnemy(1, hardPlayer)).equals("archer")
            && GameScreen.deployRightEnemy(2, hardPlayer).equals("archer")
            && (GameScreen.deployRightEnemy(3, hardPlayer)).equals("archer")
            && GameScreen.deployRightEnemy(4, hardPlayer).equals("witch")
            && GameScreen.deployRightEnemy(5, hardPlayer).equals("witch")
            && GameScreen.deployRightEnemy(6, hardPlayer).equals("witch")
            && GameScreen.deployRightEnemy(7, hardPlayer).equals("witch")
            && GameScreen.deployRightEnemy(8, hardPlayer).equals("wizard")
            && GameScreen.deployRightEnemy(9, hardPlayer).equals("wizard")
            && GameScreen.deployRightEnemy(10, hardPlayer).equals("wizard")
            && GameScreen.deployRightEnemy(11, hardPlayer).equals("wizard"));
    }

}