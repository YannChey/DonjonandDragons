package com.example.donjonanddragons.equipements;

public class Sort extends EquipementOffensif{
    public Sort(){
        setName("Une boule de feu");
        setType("Sort");
        setLevel(7);
    }
    public String toString(){
        return "Votre " + getType() + " sera " + getName() + " d'un niveau de : " + getLevel();
    }
}
