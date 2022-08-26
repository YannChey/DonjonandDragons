package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.armes.Bouclier;
import com.example.donjonanddragons.equipements.armes.Epee;

public class Guerrier extends CharacterPlayer {
     public Guerrier(){
         super(10,10,"Warrior",new Epee(),new Bouclier());
     }

    @Override
    public String toString() {
        return super.toString();
    }
}
