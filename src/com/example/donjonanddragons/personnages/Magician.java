package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.armes.FireBall;
import com.example.donjonanddragons.equipements.armes.Philtre;

public class Magician extends CharacterPlayer {
    public Magician(){
        super(15,6,"Magician",new FireBall(),new Philtre());
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
