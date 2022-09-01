package com.example.donjonanddragons;

public interface FightInteractions {
    boolean scanChoiceAttackEnnemi();
    boolean userWantToFlee();

    void displayStartGame();

    void isAnEnnemy();

    void damageAttack(int somme);

    void displayEnnemyKill(int life);

    void displayEnnemySurvive(int life);

    void displayYouAreDead(int life);

    void displayYouAreAlive(int life);

    void flee();
}

