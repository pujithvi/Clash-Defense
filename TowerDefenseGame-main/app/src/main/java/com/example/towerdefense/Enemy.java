package com.example.towerdefense;

import android.view.View;

public class Enemy {
    private int movementSpeed;
    private int timeBetween;
    private int damage;
    private int health;
    private int totalHealth;
    private int value;
    private String type = "";
    private int layout;
    private View view;

    public Enemy(String type, View view) {
        this(type);
        this.setView(view);
    }

    public Enemy(String type) {
        if (type.equals("witch")) {
            setLayout(R.layout.witch);
            setMovementSpeed(10000);
            setTimeBetween(3000);
            setDamage(30);
            setHealth(210);
            setTotalHealth(210);
            setValue(50);
        } else if (type.equals("wizard")) {
            setLayout(R.layout.wizard);
            setMovementSpeed(10000);
            setTimeBetween(3000);
            setDamage(50);
            setHealth(360);
            setTotalHealth(360);
            setValue(75);
        } else if (type.equals("archer")){
            setLayout(R.layout.archer);
            setMovementSpeed(10000);
            setTimeBetween(3000);
            setDamage(10);
            setHealth(120);
            setTotalHealth(120);
            setValue(25);
        } else {
            setType("boss");
            setLayout(R.layout.boss);
            setMovementSpeed(40000);
            setTimeBetween(2000);
            setDamage(101);
            setHealth(2500);
            setTotalHealth(2500);
            setValue(100);
        }
    }

    public void attack(Player player) {
        player.setMonumentHealth(player.getMonumentHealth() - this.damage);
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getTimeBetween() {
        return timeBetween;
    }

    public void setTimeBetween(int timeBetween) {
        this.timeBetween = timeBetween;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
    }

    public String getType() { return type;}

    public void setType(String type) { this.type = type;}
}