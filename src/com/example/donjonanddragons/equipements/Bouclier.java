package com.example.donjonanddragons.equipements;

public class Bouclier extends EquipementDefensif{
    public Bouclier(){
        setName("Un bouclier");
        setType("Defense");
        setLevel(8);
    }

    public String toString(){
        return "Votre " + getType() + " sera " + getName() + " d'un niveau de : " + getLevel();
    }
}
