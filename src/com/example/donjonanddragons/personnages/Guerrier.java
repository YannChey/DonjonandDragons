package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.Arme;
import com.example.donjonanddragons.equipements.Bouclier;

public class Guerrier extends Character{
     public Guerrier(){
         setAttackObject(new Arme());
         setDefendObject(new Bouclier());
         setName("Warrior");
         setLife(10);
         setPower(10);
     }

    @Override
    public String toString() {
        return super.toString();
    }
}
