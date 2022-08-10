package com.example.towerdefense;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class TestingM4 {

    private final Player easyPlayer = new Player("easy", "easy player");
    private final Player dummyEasyPlayer = new Player("easy", "dummy easy player");
    private final Player mediumPlayer = new Player("medium", "medium player");
    private final Player dummyMediumPlayer = new Player("medium", "dummy medium player");
    private final Player hardPlayer = new Player("hard", "hard player");
    private final Player dummyHardPlayer = new Player("hard", "dummy hard player");
    private final Enemy witch = new Enemy("witch");
    private final Enemy wizard = new Enemy("wizard");
    private final Enemy archer = new Enemy("archer");
    private final Difficulty easy = new Difficulty(easyPlayer);
    private final Difficulty medium = new Difficulty(mediumPlayer);
    private final Difficulty hard = new Difficulty(hardPlayer);

    @Test
    public void diffEnemyDamage() {
        assertTrue(witch.getDamage()
            != wizard.getDamage()
            && witch.getDamage()
            != archer.getDamage()
            && wizard.getDamage()
            != archer.getDamage());
    }

    @Test
    public void diffEnemyMovementSpeed() {
        assertTrue(witch.getMovementSpeed()
            != wizard.getMovementSpeed()
            && witch.getMovementSpeed()
            != archer.getMovementSpeed()
            && wizard.getMovementSpeed()
            != archer.getMovementSpeed());
    }

    @Test
    public void diffTimeBetween() {
        assertTrue(witch.getTimeBetween()
            != wizard.getTimeBetween()
            && witch.getTimeBetween()
            != archer.getTimeBetween()
            && wizard.getTimeBetween()
            != archer.getTimeBetween());
    }

    @Test
    public void diffEnemyHealth() {
        assertTrue(witch.getHealth() != wizard.getHealth()
            && witch.getHealth() != archer.getHealth()
            && wizard.getHealth() != archer.getHealth());
    }

    @Test
    public void witchAttack() {
        witch.attack(easyPlayer);
        witch.attack(mediumPlayer);
        witch.attack(hardPlayer);
        assertTrue((easyPlayer.getMonumentHealth()
            == (dummyEasyPlayer.getMonumentHealth() - witch.getDamage()))
            && (mediumPlayer.getMonumentHealth()
            == (dummyMediumPlayer.getMonumentHealth() - witch.getDamage()))
            && (hardPlayer.getMonumentHealth()
            == (dummyHardPlayer.getMonumentHealth() - witch.getDamage())));
    }

    @Test
    public void wizardAttack() {
        wizard.attack(easyPlayer);
        wizard.attack(mediumPlayer);
        wizard.attack(hardPlayer);
        assertTrue((easyPlayer.getMonumentHealth()
            == (dummyEasyPlayer.getMonumentHealth() - wizard.getDamage()))
            && (mediumPlayer.getMonumentHealth()
            == (dummyMediumPlayer.getMonumentHealth() - wizard.getDamage()))
            && (hardPlayer.getMonumentHealth()
            == (dummyHardPlayer.getMonumentHealth() - wizard.getDamage())));
    }

    @Test
    public void archerAttack() {
        archer.attack(easyPlayer);
        archer.attack(mediumPlayer);
        archer.attack(hardPlayer);
        assertTrue((easyPlayer.getMonumentHealth()
            == (dummyEasyPlayer.getMonumentHealth() - archer.getDamage()))
            && (mediumPlayer.getMonumentHealth()
            == (dummyMediumPlayer.getMonumentHealth() - archer.getDamage()))
            && (hardPlayer.getMonumentHealth()
            == (dummyHardPlayer.getMonumentHealth() - archer.getDamage())));
    }

    @Test
    public void diffNumWitches() {
        assertTrue(easy.getNumWitches() != medium.getNumWitches()
            && easy.getNumWitches()
            != hard.getNumWitches()
            && medium.getNumWitches()
            != hard.getNumWitches());
    }

    @Test
    public void diffNumWizards() {
        assertTrue(easy.getNumWizards()
            != medium.getNumWizards()
            && easy.getNumWizards()
            != hard.getNumWizards()
            && medium.getNumWizards()
            != hard.getNumWizards());
    }

    @Test
    public void diffNumArchers() {
        assertTrue(easy.getNumArchers()
            != medium.getNumArchers()
            && easy.getNumArchers()
            != hard.getNumArchers()
            && medium.getNumArchers()
            != hard.getNumArchers());
    }
}
