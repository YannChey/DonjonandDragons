package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.Arme;
import com.example.donjonanddragons.equipements.Bouclier;

public class Guerrier extends Character{
     public Guerrier(){
         super(10,10,"Warrior",new Arme(),new Bouclier());
//         setAttackObject(new Arme());
//         setDefendObject(new Bouclier());
//         setName("Warrior");
//         setLife(10);
//         setPower(10);
     }

    @Override
    public String toString() {
        return super.toString();
    }
}
