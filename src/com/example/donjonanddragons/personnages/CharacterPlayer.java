package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.Combattants;
import com.example.donjonanddragons.equipements.armes.defense.EquipementDefensif;
import com.example.donjonanddragons.equipements.armes.attaque.EquipementOffensif;

public abstract class CharacterPlayer implements Combattants {
    private int power;
    private int life;
    private String name;
    int position;
    private EquipementOffensif attackObject;
    private EquipementDefensif defendObject;

    public CharacterPlayer(int power, int life, String name, EquipementOffensif attackObject, EquipementDefensif defendObject) {
        this.power = power;
        this.life = life;
        this.name = name;
        this.attackObject = attackObject;
        this.defendObject = defendObject;
        this.position=1;
    }

    public String toString() {
        return "Vous avez choisi le personnage : " + name  + ". Votre vie est de: " + life + ". Et votre puissance est de: " + power;
    }

    @Override
    public boolean fighter() {
        return true;
    }

    public int diceResult(){
        return (int) (Math.random() * 6) + 1;
    }

    public int updatePosition(int dices){
       this.position = this.position + dices;
       return this.position;
    }

    public int updateNegativePosition(int dices){
        this.position = this.position - dices;
        return this.position;
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
}
