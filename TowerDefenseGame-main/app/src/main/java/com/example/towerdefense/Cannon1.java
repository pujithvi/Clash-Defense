package com.example.towerdefense;

import android.widget.ImageButton;

public class Cannon1 extends Tower {


    public Cannon1(Player player, ImageButton button) {
        this.setImgRes(R.drawable.cannon1new);
        this.setExplosionImgRes(R.drawable.cannon1explosion);
        this.setPlayer(player);
        this.setButton(button);
        int cost = 50;
        double upgradeMultiplier = 1.2;

        if (player.getDifficulty().equals("medium")) {
            cost = 75;
            upgradeMultiplier = 1.4;
        } else if (player.getDifficulty().equals("hard")) {
            cost = 100;
            upgradeMultiplier = 1.5;
        }
        int upgradeCost = (int) (cost * 0.60);
        this.setCost(cost);
        this.setUpgradeCost(upgradeCost);
        this.setUpgradeMultiplier(upgradeMultiplier);
        this.setAttackSpeed(2000);
        this.setAttackDamage(40);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
