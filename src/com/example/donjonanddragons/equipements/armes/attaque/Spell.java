package com.example.donjonanddragons.equipements.armes.attaque;

public abstract class Spell extends EquipementOffensif {
    public Spell(int level, String name) {
        super("Sort", level, name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
