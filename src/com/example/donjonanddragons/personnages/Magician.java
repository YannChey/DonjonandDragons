package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.armes.Philtre;
import com.example.donjonanddragons.equipements.armes.Sort;

public class Magician extends Character{
    public Magician(){
        super(15,6,"Magician",new Sort(),new Philtre());
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
