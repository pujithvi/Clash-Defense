package com.example.towerdefense;

import java.io.Serializable;

public class Player implements Serializable {
    private int balance;
    private String name;
    private String difficulty;
    private int monumentHealth;
    private int totalMoneyEarned;
    private int upgradesBought = 0;
    private int enemyDefeated;

    Player(String difficulty, String name) {
        this.setDifficulty(difficulty);
        this.setName(name);
        if (difficulty.equals("easy")) {
            setBalance(100);
            setMonumentHealth(100);
        } else if (difficulty.equals("medium")) {
            setBalance(150);
            setMonumentHealth(75);
        } else if (difficulty.equals("hard")) {
            setBalance(200);
            setMonumentHealth(50);
        }
        totalMoneyEarned = balance;
    }

    public void updateBalance(int balance) {
        if (balance > 0) {
            totalMoneyEarned += balance;
        }
        this.balance += balance;
    }

    public void addBalance(Enemy enemy) {
        updateBalance(enemy.getValue());
    }
    public void enemyDefeated() {
        enemyDefeated++;
    }

    public int getBalance() {
        return balance;
    }

    public int getEnemyDefeated() {
        return enemyDefeated;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getMonumentHealth() {
        return monumentHealth;
    }

    public void setMonumentHealth(int monumentHealth) {
        this.monumentHealth = monumentHealth;
    }

    public int getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public void setTotalMoneyEarned(int totalMoneyEarned) {
        this.totalMoneyEarned = totalMoneyEarned;
    }

    public int getUpgradesBought() {
        return upgradesBought;
    }

    public void setUpgradesBought(int upgradesBought) {
        this.upgradesBought = upgradesBought;
    }
}
