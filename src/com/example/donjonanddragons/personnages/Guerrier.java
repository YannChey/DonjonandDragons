package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.armes.Arme;
import com.example.donjonanddragons.equipements.armes.Bouclier;

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
