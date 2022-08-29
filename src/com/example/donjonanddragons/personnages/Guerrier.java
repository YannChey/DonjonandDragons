package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.armes.attaque.BaseArme;
import com.example.donjonanddragons.equipements.armes.defense.Bouclier;
import com.example.donjonanddragons.equipements.armes.attaque.Epee;

public class Guerrier extends CharacterPlayer {
     public Guerrier(){
         super(10,10,"Warrior",new BaseArme(),new Bouclier());
     }

    @Override
    public String toString() {
        return super.toString();
    }
}
