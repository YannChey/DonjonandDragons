package com.example.donjonanddragons.ennemis;


public class Ennemi {
    private String name;
    private int attack;
    private int life;
    private int max = 6;
    private int min = 1;
    private int range = max - min + 1;

    public Ennemi(String name, int attack, int life) {
        this.name = name;
        this.attack = attack;
        this.life = life;
    }

    @Override
    public String toString() {
        return ("Vous etes tombes sur le : " + name + ". Son attaque est de : " + attack + " et son niveau de vie est de : " + life);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
