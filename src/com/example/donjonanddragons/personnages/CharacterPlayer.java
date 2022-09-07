package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.Combattants;
import com.example.donjonanddragons.equipements.armes.defense.EquipementDefensif;
import com.example.donjonanddragons.equipements.armes.attaque.EquipementOffensif;

import java.awt.*;
import java.sql.*;

public abstract class CharacterPlayer implements Combattants {
    private int power;
    private int life;
    private String name;
    private String type;
    int position;
    private EquipementOffensif attackObject;
    private EquipementDefensif defendObject;

    public CharacterPlayer(int power, int life, String name, String type, EquipementOffensif attackObject, EquipementDefensif defendObject) {
        this.position = 1;
        this.power = power;
        this.life = life;
        this.name = name;
        this.type = type;
        this.attackObject = attackObject;
        this.defendObject = defendObject;
    }

    public String toString() {
        return "Bienvenue " + name + ". Vous avez choisi le personnage : " + type + ". Votre vie est de: " + life + ". Et votre puissance est de: " + power;
    }

    @Override
    public boolean fighter() {
        return true;
    }

    public int diceResult() {
        return (int) (Math.random() * 6) + 1;
    }

    public void updatePosition(int dices) {
        this.position = this.position + dices;
    }

    public void updateNegativePosition(int dices) {
        this.position = this.position - dices;
        if(this.position < 0){
            this.position = 0;
        }
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
