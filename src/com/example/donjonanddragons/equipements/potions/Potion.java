package com.example.donjonanddragons.equipements.potions;

import com.example.donjonanddragons.ennemis.Dragon;
import com.example.donjonanddragons.ennemis.Gobelin;
import com.example.donjonanddragons.ennemis.Sorcier;

public class Potion {
    private int level;
    private String name;

    public Potion(int level, String name){
        this.level = level;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vous avez trouve une potion : "+ name + " qui est de niveau : " + level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
