package com.example.donjonanddragons.equipements;

public class Arme extends EquipementOffensif{

    public Arme(){
        setName("Epee");
        setLevel(5);
        setType("Arme");
    }

    public String toString(){
        return "Votre " + getType() + " sera " + getName() + " d'un niveau de : " + getLevel();
    }
}
