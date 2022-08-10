package com.example.towerdefense;

import android.widget.ImageButton;

public class Cannon2 extends Tower {

    public Cannon2(Player player, ImageButton button) {
        this.setImgRes(R.drawable.cannon2newnew);
        this.setExplosionImgRes(R.drawable.cannon2explosion);
        this.setPlayer(player);
        this.setButton(button);
        int cost = 100;
        double upgradeMultiplier = 1.2;

        if (player.getDifficulty().equals("medium")) {
            cost = 125;
            upgradeMultiplier = 1.4;
        } else if (player.getDifficulty().equals("hard")) {
            cost = 150;
            upgradeMultiplier = 1.5;
        }
        int upgradeCost = (int) (cost * 0.60);
        this.setUpgradeCost(upgradeCost);
        this.setCost(cost);
        this.setUpgradeCost(upgradeCost);
        this.setUpgradeMultiplier(upgradeMultiplier);
        this.setAttackSpeed(1500);
        this.setAttackDamage(50);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
