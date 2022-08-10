package com.example.towerdefense;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.widget.ImageButton;

import org.junit.Test;

public class TestingM6 {

    private Player easyPlayer = new Player("easy", "easy player");
    private final Player dummyEasyPlayer = new Player("easy", "dummy easy player");
    private Player mediumPlayer = new Player("medium", "medium player");
    private final Player dummyMediumPlayer = new Player("medium", "dummy medium player");
    private Player hardPlayer = new Player("hard", "hard player");
    private final Player dummyHardPlayer = new Player("hard", "dummy hard player");
    private ImageButton imageButton1;
    private Cannon1 easyCannon1 = new Cannon1(easyPlayer, imageButton1);
    private Cannon1 mediumCannon1 = new Cannon1(mediumPlayer, imageButton1);
    private Cannon1 hardCannon1 = new Cannon1(hardPlayer, imageButton1);
    private ImageButton imageButton2;
    private Cannon2 easyCannon2 = new Cannon2(easyPlayer, imageButton2);
    private Cannon2 mediumCannon2 = new Cannon2(mediumPlayer, imageButton2);
    private Cannon2 hardCannon2 = new Cannon2(hardPlayer, imageButton2);
    private ImageButton imageButton3;
    private Cannon3 easyCannon3 = new Cannon3(easyPlayer, imageButton3);
    private Cannon3 mediumCannon3 = new Cannon3(mediumPlayer, imageButton3);
    private Cannon3 hardCannon3 = new Cannon3(hardPlayer, imageButton3);
    private ImageButton dummyImageButton1;
    private Cannon1 dummyEasyCannon1 = new Cannon1(easyPlayer, dummyImageButton1);
    private Cannon1 dummyMediumCannon1 = new Cannon1(mediumPlayer, dummyImageButton1);
    private Cannon1 dummyHardCannon1 = new Cannon1(hardPlayer, dummyImageButton1);
    private ImageButton dummyImageButton2;
    private Cannon2 dummyEasyCannon2 = new Cannon2(easyPlayer, dummyImageButton2);
    private Cannon2 dummyMediumCannon2 = new Cannon2(mediumPlayer, dummyImageButton2);
    private Cannon2 dummyHardCannon2 = new Cannon2(hardPlayer, dummyImageButton2);
    private ImageButton dummyImageButton3;
    private Cannon3 dummyEasyCannon3 = new Cannon3(easyPlayer, dummyImageButton3);
    private Cannon3 dummyMediumCannon3 = new Cannon3(mediumPlayer, dummyImageButton3);
    private Cannon3 dummyHardCannon3 = new Cannon3(hardPlayer, dummyImageButton3);
    private final Enemy boss = new Enemy("boss");

    @Test
    public void finalBossAttack() {
        boss.attack(easyPlayer);
        boss.attack(mediumPlayer);
        boss.attack(hardPlayer);
        assertTrue((easyPlayer.getMonumentHealth()
                == (dummyEasyPlayer.getMonumentHealth() - boss.getDamage()))
                && (mediumPlayer.getMonumentHealth()
                == (dummyMediumPlayer.getMonumentHealth() - boss.getDamage()))
                && (hardPlayer.getMonumentHealth()
                == (dummyHardPlayer.getMonumentHealth() - boss.getDamage())));
    }

    @Test
    public void cannon1UpgradesBalance() {
        easyCannon1.upgrade();
        mediumCannon1.upgrade();
        hardCannon1.upgrade();
        dummyEasyPlayer.setBalance(dummyEasyPlayer.getBalance() - easyCannon1.getUpgradeCost());
        dummyMediumPlayer.setBalance(dummyMediumPlayer.getBalance() - mediumCannon1.getUpgradeCost());
        dummyHardPlayer.setBalance(dummyHardPlayer.getBalance() - hardCannon1.getUpgradeCost());
        assertTrue((easyPlayer.getBalance()
                == (dummyEasyPlayer.getBalance()))
                && (mediumPlayer.getBalance()
                == (dummyMediumPlayer.getBalance()))
                && (hardPlayer.getBalance()
                == (dummyHardPlayer.getBalance())));
    }

    @Test
    public void cannon2UpgradesBalance() {
        easyCannon2.upgrade();
        mediumCannon2.upgrade();
        hardCannon2.upgrade();
        dummyEasyPlayer.setBalance(dummyEasyPlayer.getBalance() - easyCannon2.getUpgradeCost());
        dummyMediumPlayer.setBalance(dummyMediumPlayer.getBalance() - mediumCannon2.getUpgradeCost());
        dummyHardPlayer.setBalance(dummyHardPlayer.getBalance() - hardCannon2.getUpgradeCost());
        assertTrue((easyPlayer.getBalance()
                == (dummyEasyPlayer.getBalance()))
                && (mediumPlayer.getBalance()
                == (dummyMediumPlayer.getBalance()))
                && (hardPlayer.getBalance()
                == (dummyHardPlayer.getBalance())));
    }

    @Test
    public void cannon3UpgradesBalance() {
        easyCannon3.upgrade();
        mediumCannon3.upgrade();
        hardCannon3.upgrade();
        dummyEasyPlayer.setBalance(dummyEasyPlayer.getBalance() - easyCannon3.getUpgradeCost());
        dummyMediumPlayer.setBalance(dummyMediumPlayer.getBalance() - mediumCannon3.getUpgradeCost());
        dummyHardPlayer.setBalance(dummyHardPlayer.getBalance() - hardCannon3.getUpgradeCost());
        assertTrue((easyPlayer.getBalance()
                == (dummyEasyPlayer.getBalance()))
                && (mediumPlayer.getBalance()
                == (dummyMediumPlayer.getBalance()))
                && (hardPlayer.getBalance()
                == (dummyHardPlayer.getBalance())));
    }

    @Test
    public void cannon1UpgradesAttackDamage() {
        easyCannon1.upgrade();
        mediumCannon1.upgrade();
        hardCannon1.upgrade();
        dummyEasyCannon1.setAttackDamage((int) GameScreen.round(dummyEasyCannon1.getAttackDamage() * dummyEasyCannon1.getUpgradeMultiplier()));
        dummyMediumCannon1.setAttackDamage((int) GameScreen.round(dummyMediumCannon1.getAttackDamage() * dummyMediumCannon1.getUpgradeMultiplier()));
        dummyHardCannon1.setAttackDamage((int) GameScreen.round(dummyHardCannon1.getAttackDamage() * dummyHardCannon1.getUpgradeMultiplier()));
        assertTrue((easyCannon1.getAttackDamage()
                == (dummyEasyCannon1.getAttackDamage()))
                && (mediumCannon1.getAttackDamage()
                == (dummyMediumCannon1.getAttackDamage()))
                && (hardCannon1.getAttackDamage()
                == (dummyHardCannon1.getAttackDamage())));
    }

    @Test
    public void cannon2UpgradesAttackDamage() {
        easyCannon2.upgrade();
        mediumCannon2.upgrade();
        hardCannon2.upgrade();
        dummyEasyCannon2.setAttackDamage((int) GameScreen.round(dummyEasyCannon2.getAttackDamage() * dummyEasyCannon2.getUpgradeMultiplier()));
        dummyMediumCannon2.setAttackDamage((int) GameScreen.round(dummyMediumCannon2.getAttackDamage() * dummyMediumCannon2.getUpgradeMultiplier()));
        dummyHardCannon2.setAttackDamage((int) GameScreen.round(dummyHardCannon2.getAttackDamage() * dummyHardCannon2.getUpgradeMultiplier()));
        assertTrue((easyCannon2.getAttackDamage()
                == (dummyEasyCannon2.getAttackDamage()))
                && (mediumCannon2.getAttackDamage()
                == (dummyMediumCannon2.getAttackDamage()))
                && (hardCannon2.getAttackDamage()
                == (dummyHardCannon2.getAttackDamage())));
    }

    @Test
    public void cannon3UpgradesAttackDamage() {
        easyCannon3.upgrade();
        mediumCannon3.upgrade();
        hardCannon3.upgrade();
        dummyEasyCannon3.setAttackDamage((int) GameScreen.round(dummyEasyCannon3.getAttackDamage() * dummyEasyCannon3.getUpgradeMultiplier()));
        dummyMediumCannon3.setAttackDamage((int) GameScreen.round(dummyMediumCannon3.getAttackDamage() * dummyMediumCannon3.getUpgradeMultiplier()));
        dummyHardCannon3.setAttackDamage((int) GameScreen.round(dummyHardCannon3.getAttackDamage() * dummyHardCannon3.getUpgradeMultiplier()));
        assertTrue((easyCannon3.getAttackDamage()
                == (dummyEasyCannon3.getAttackDamage()))
                && (mediumCannon3.getAttackDamage()
                == (dummyMediumCannon3.getAttackDamage()))
                && (hardCannon3.getAttackDamage()
                == (dummyHardCannon3.getAttackDamage())));
    }

    @Test
    public void cannon1UpgradesAttackSpeed() {
        easyCannon1.upgrade();
        mediumCannon1.upgrade();
        hardCannon1.upgrade();
        dummyEasyCannon1.setAttackSpeed(GameScreen.round((dummyEasyCannon1.getAttackSpeed() / dummyEasyCannon1.getUpgradeMultiplier())));
        dummyMediumCannon1.setAttackSpeed(GameScreen.round((dummyMediumCannon1.getAttackSpeed() / dummyMediumCannon1.getUpgradeMultiplier())));
        dummyHardCannon1.setAttackSpeed(GameScreen.round((dummyHardCannon1.getAttackSpeed() / dummyHardCannon1.getUpgradeMultiplier())));
        assertTrue((easyCannon1.getAttackSpeed()
                == (dummyEasyCannon1.getAttackSpeed()))
                && (mediumCannon1.getAttackSpeed()
                == (dummyMediumCannon1.getAttackSpeed()))
                && (hardCannon1.getAttackSpeed()
                == (dummyHardCannon1.getAttackSpeed())));
    }

    @Test
    public void cannon2UpgradesAttackSpeed() {
        easyCannon2.upgrade();
        mediumCannon2.upgrade();
        hardCannon2.upgrade();
        dummyEasyCannon2.setAttackSpeed(GameScreen.round((dummyEasyCannon2.getAttackSpeed() / dummyEasyCannon2.getUpgradeMultiplier())));
        dummyMediumCannon2.setAttackSpeed(GameScreen.round((dummyMediumCannon2.getAttackSpeed() / dummyMediumCannon2.getUpgradeMultiplier())));
        dummyHardCannon2.setAttackSpeed(GameScreen.round((dummyHardCannon2.getAttackSpeed() / dummyHardCannon2.getUpgradeMultiplier())));
        assertTrue((easyCannon2.getAttackSpeed()
                == (dummyEasyCannon2.getAttackSpeed()))
                && (mediumCannon2.getAttackSpeed()
                == (dummyMediumCannon2.getAttackSpeed()))
                && (hardCannon2.getAttackSpeed()
                == (dummyHardCannon2.getAttackSpeed())));
    }

    @Test
    public void cannon3UpgradesAttackSpeed() {
        easyCannon3.upgrade();
        mediumCannon3.upgrade();
        hardCannon3.upgrade();
        dummyEasyCannon3.setAttackSpeed(GameScreen.round((dummyEasyCannon3.getAttackSpeed() / dummyEasyCannon3.getUpgradeMultiplier())));
        dummyMediumCannon3.setAttackSpeed(GameScreen.round((dummyMediumCannon3.getAttackSpeed() / dummyMediumCannon3.getUpgradeMultiplier())));
        dummyHardCannon3.setAttackSpeed(GameScreen.round((dummyHardCannon3.getAttackSpeed() / dummyHardCannon3.getUpgradeMultiplier())));
        assertTrue((easyCannon3.getAttackSpeed()
                == (dummyEasyCannon3.getAttackSpeed()))
                && (mediumCannon3.getAttackSpeed()
                == (dummyMediumCannon3.getAttackSpeed()))
                && (hardCannon3.getAttackSpeed()
                == (dummyHardCannon3.getAttackSpeed())));
    }
}
