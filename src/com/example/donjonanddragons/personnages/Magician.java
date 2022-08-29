package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.armes.attaque.BaseArme;
import com.example.donjonanddragons.equipements.armes.attaque.FireBall;
import com.example.donjonanddragons.equipements.armes.defense.Philtre;

public class Magician extends CharacterPlayer {
    public Magician(){
        super(15,6,"Magician",new BaseArme(),new Philtre());
//        setAttackObject(new Sort());
//        setDefendObject(new Philtre());
//        setName("Magician");
//        setLife(6);
//        setPower(15);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
