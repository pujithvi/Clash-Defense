package com.example.towerdefense;

import android.widget.ImageButton;

public class Cannon3 extends Tower {

    public Cannon3(Player player, ImageButton button) {
        this.setImgRes(R.drawable.cannon3newnew);
        this.setExplosionImgRes(R.drawable.cannon3explosion);
        this.setPlayer(player);
        this.setButton(button);
        int cost = 150;
        double upgradeMultiplier = 1.2;

        if (player.getDifficulty().equals("medium")) {
            cost = 175;
            upgradeMultiplier = 1.4;
        } else if (player.getDifficulty().equals("hard")) {
            cost = 200;
            upgradeMultiplier = 1.5;
        }
        int upgradeCost = (int) (cost * 0.60);
        this.setUpgradeCost(upgradeCost);
        this.setCost(cost);
        this.setUpgradeMultiplier(upgradeMultiplier);
        this.setAttackSpeed(1000);
        this.setAttackDamage(60);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
