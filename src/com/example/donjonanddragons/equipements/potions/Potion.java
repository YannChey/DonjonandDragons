package com.example.donjonanddragons.equipements.potions;

import com.example.donjonanddragons.ennemis.Dragon;
import com.example.donjonanddragons.ennemis.Gobelin;
import com.example.donjonanddragons.ennemis.Sorcier;

public class Potion {
    private int level;

    public Potion(){

    }

    public int wichPotion(){
        int throughDices;
        throughDices = (int) (Math.random() * 6) + 1;
        if(throughDices <= 4){
            this.level = 2;
        }
        else {
            this.level = 5;
        }
        return this.level;
    }

    @Override
    public String toString() {
        return "La potion est de niveau : " + wichPotion();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
