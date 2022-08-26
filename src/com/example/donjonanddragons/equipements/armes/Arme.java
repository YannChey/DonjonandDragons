package com.example.donjonanddragons.equipements.armes;

public abstract class Arme extends EquipementOffensif {

    public Arme(int level, String name){
        super("Arme",level,name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
