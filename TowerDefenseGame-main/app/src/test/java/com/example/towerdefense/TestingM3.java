package com.example.towerdefense;

import static org.junit.Assert.assertTrue;

import android.widget.ImageButton;

import org.junit.Test;

public class TestingM3 {

    private Player easyPlayer = new Player("easy", "easy player");
    private Player mediumPlayer = new Player("medium", "medium player");
    private Player hardPlayer = new Player("hard", "hard player");

    private ImageButton imageButton1;
    private Cannon1 easyCannon1 = new Cannon1(getEasyPlayer(), imageButton1);
    private Cannon1 mediumCannon1 = new Cannon1(getMediumPlayer(), imageButton1);
    private Cannon1 hardCannon1 = new Cannon1(getHardPlayer(), imageButton1);
    private ImageButton imageButton2;
    private Cannon2 easyCannon2 = new Cannon2(getEasyPlayer(), imageButton2);
    private Cannon2 mediumCannon2 = new Cannon2(getMediumPlayer(), imageButton2);
    private Cannon2 hardCannon2 = new Cannon2(getHardPlayer(), imageButton2);
    private ImageButton imageButton3;
    private Cannon3 easyCannon3 = new Cannon3(getEasyPlayer(), imageButton3);
    private Cannon3 mediumCannon3 = new Cannon3(getMediumPlayer(), imageButton3);
    private Cannon3 hardCannon3 = new Cannon3(getHardPlayer(), imageButton3);

    @Test
    public void diffInitialBalance() {
        assertTrue(getEasyPlayer().getBalance() != getMediumPlayer().getBalance()
            && getMediumPlayer().getBalance() != getHardPlayer().getBalance()
            && getEasyPlayer().getBalance() != getHardPlayer().getBalance());
    }

    @Test
    public void diffMonumentHealth() {
        assertTrue(getEasyPlayer().getMonumentHealth() != getMediumPlayer().getMonumentHealth()
            && getMediumPlayer().getMonumentHealth() != getHardPlayer().getMonumentHealth()
            && getEasyPlayer().getMonumentHealth() != getHardPlayer().getMonumentHealth());
    }

    @Test
    public void diffDifficulty() {
        assertTrue(getEasyPlayer().getDifficulty() != getMediumPlayer().getDifficulty()
            && getMediumPlayer().getDifficulty() != getHardPlayer().getDifficulty()
            && getEasyPlayer().getDifficulty() != getHardPlayer().getDifficulty());
    }

    @Test
    public void diffCannon1InitialCosts() {
        assertTrue(getEasyCannon1().getCost() != getMediumCannon1().getCost()
            && getEasyCannon1().getCost() != getHardCannon1().getCost()
            && getMediumCannon1().getCost() != getHardCannon1().getCost());
    }

    @Test
    public void diffCannon2InitialCosts() {
        assertTrue(getEasyCannon2().getCost() != getMediumCannon2().getCost()
            && getEasyCannon2().getCost() != getHardCannon2().getCost()
            && getMediumCannon2().getCost() != getHardCannon2().getCost());
    }

    @Test
    public void diffCannon3InitialCosts() {
        assertTrue(getEasyCannon3().getCost() != getMediumCannon3().getCost()
            && getEasyCannon3().getCost() != getHardCannon3().getCost()
            && getMediumCannon3().getCost() != getHardCannon3().getCost());
    }

    @Test
    public void testBuyTower() {
        boolean greater = Shop.buyTower(getEasyCannon1(), getEasyPlayer());
        getEasyPlayer().updateBalance(-1 * 450);
        boolean equals = Shop.buyTower(getEasyCannon1(), getEasyPlayer());
        getEasyPlayer().updateBalance(-1 * 10);
        boolean less = Shop.buyTower(getEasyCannon1(), getEasyPlayer());
        assertTrue(greater == equals && greater != less && equals != less);
    }

    @Test
    public void diffCannonsUpgradeMultipliers() {
        assertTrue(
            getEasyCannon1().getUpgradeMultiplier() != getMediumCannon1().getUpgradeMultiplier()
                && getEasyCannon1().getUpgradeMultiplier()
                != getHardCannon1().getUpgradeMultiplier()
                && getMediumCannon1().getUpgradeMultiplier()
                != getHardCannon1().getUpgradeMultiplier()
                && getEasyCannon2().getUpgradeMultiplier()
                != getMediumCannon2().getUpgradeMultiplier()
                && getEasyCannon2().getUpgradeMultiplier()
                != getHardCannon2().getUpgradeMultiplier()
                && getMediumCannon2().getUpgradeMultiplier()
                != getHardCannon2().getUpgradeMultiplier()
                && getEasyCannon3().getUpgradeMultiplier()
                != getMediumCannon3().getUpgradeMultiplier()
                && getEasyCannon3().getUpgradeMultiplier()
                != getHardCannon3().getUpgradeMultiplier()
                && getMediumCannon3().getUpgradeMultiplier()
                != getHardCannon3().getUpgradeMultiplier());
    }

    @Test
    public void diffCannonsAttackSpeeds() {
        assertTrue(getEasyCannon1().getAttackSpeed() != getEasyCannon2().getAttackSpeed()
            && getEasyCannon1().getAttackSpeed()
            != getEasyCannon3().getAttackSpeed()
            && getEasyCannon2().getAttackSpeed()
            != getEasyCannon3().getAttackSpeed()
            && getMediumCannon1().getAttackSpeed()
            != getMediumCannon2().getAttackSpeed()
            && getMediumCannon1().getAttackSpeed()
            != getMediumCannon3().getAttackSpeed()
            && getMediumCannon2().getAttackSpeed()
            != getMediumCannon3().getAttackSpeed()
            && getHardCannon1().getAttackSpeed()
            != getHardCannon2().getAttackSpeed()
            && getHardCannon1().getAttackSpeed()
            != getHardCannon3().getAttackSpeed()
            && getHardCannon2().getAttackSpeed()
            != getHardCannon3().getAttackSpeed());
    }

    @Test
    public void diffCannonsAttackDamage() {
        assertTrue(getEasyCannon1().getAttackDamage()
            != getEasyCannon2().getAttackDamage()
            && getEasyCannon1().getAttackDamage()
            != getEasyCannon3().getAttackDamage()
            && getEasyCannon2().getAttackDamage()
            != getEasyCannon3().getAttackDamage()
            && getMediumCannon1().getAttackDamage()
            != getMediumCannon2().getAttackDamage()
            && getMediumCannon1().getAttackDamage()
            != getMediumCannon3().getAttackDamage()
            && getMediumCannon2().getAttackDamage()
            != getMediumCannon3().getAttackDamage()
            && getHardCannon1().getAttackDamage()
            != getHardCannon2().getAttackDamage()
            && getHardCannon1().getAttackDamage()
            != getHardCannon3().getAttackDamage()
            && getHardCannon2().getAttackDamage()
            != getHardCannon3().getAttackDamage());
    }

    public Player getEasyPlayer() {
        return easyPlayer;
    }

    public void setEasyPlayer(Player easyPlayer) {
        this.easyPlayer = easyPlayer;
    }

    public Player getMediumPlayer() {
        return mediumPlayer;
    }

    public void setMediumPlayer(Player mediumPlayer) {
        this.mediumPlayer = mediumPlayer;
    }

    public Player getHardPlayer() {
        return hardPlayer;
    }

    public void setHardPlayer(Player hardPlayer) {
        this.hardPlayer = hardPlayer;
    }

    public Cannon1 getEasyCannon1() {
        return easyCannon1;
    }

    public void setEasyCannon1(Cannon1 easyCannon1) {
        this.easyCannon1 = easyCannon1;
    }

    public Cannon1 getMediumCannon1() {
        return mediumCannon1;
    }

    public void setMediumCannon1(Cannon1 mediumCannon1) {
        this.mediumCannon1 = mediumCannon1;
    }

    public Cannon1 getHardCannon1() {
        return hardCannon1;
    }

    public void setHardCannon1(Cannon1 hardCannon1) {
        this.hardCannon1 = hardCannon1;
    }

    public Cannon2 getEasyCannon2() {
        return easyCannon2;
    }

    public void setEasyCannon2(Cannon2 easyCannon2) {
        this.easyCannon2 = easyCannon2;
    }

    public Cannon2 getMediumCannon2() {
        return mediumCannon2;
    }

    public void setMediumCannon2(Cannon2 mediumCannon2) {
        this.mediumCannon2 = mediumCannon2;
    }

    public Cannon2 getHardCannon2() {
        return hardCannon2;
    }

    public void setHardCannon2(Cannon2 hardCannon2) {
        this.hardCannon2 = hardCannon2;
    }

    public Cannon3 getEasyCannon3() {
        return easyCannon3;
    }

    public void setEasyCannon3(Cannon3 easyCannon3) {
        this.easyCannon3 = easyCannon3;
    }

    public Cannon3 getMediumCannon3() {
        return mediumCannon3;
    }

    public void setMediumCannon3(Cannon3 mediumCannon3) {
        this.mediumCannon3 = mediumCannon3;
    }

    public Cannon3 getHardCannon3() {
        return hardCannon3;
    }

    public void setHardCannon3(Cannon3 hardCannon3) {
        this.hardCannon3 = hardCannon3;
    }
}
