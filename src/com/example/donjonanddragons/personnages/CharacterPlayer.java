package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.armes.EquipementDefensif;
import com.example.donjonanddragons.equipements.armes.EquipementOffensif;

public abstract class CharacterPlayer {
    private int power;
    private int life;
    private String name;
    private EquipementOffensif attackObject;
    private EquipementDefensif defendObject;

    public CharacterPlayer(int power, int life, String name, EquipementOffensif attackObject, EquipementDefensif defendObject) {
        this.power = power;
        this.life = life;
        this.name = name;
        this.attackObject = attackObject;
        this.defendObject = defendObject;
    }

    public String toString() {
        return "Vous avez choisi le personnage : " + name  + ". Votre vie est de: " + life + ". Et votre puissance est de: " + power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public EquipementOffensif getAttackObject() {
        return this.attackObject;
    }

    public EquipementDefensif getDefendObject() {
        return this.defendObject;
    }

    public void setAttackObject(EquipementOffensif attack) {
        this.attackObject = attack;
    }

    public void setDefendObject(EquipementDefensif defend) {
        this.defendObject = defend;
    }

}
