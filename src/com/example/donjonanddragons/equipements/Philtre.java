package com.example.donjonanddragons.equipements;

public class Philtre extends EquipementDefensif{
    public Philtre(){
        setName("Philtre");
        setType("Defense");
        setLevel(10);
    }

    public String toString(){
        return "Votre " + getType() + " sera " + getName() + " d'un niveau de : " + getLevel();
    }
}
