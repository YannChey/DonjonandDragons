package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.Arme;
import com.example.donjonanddragons.equipements.Bouclier;
import com.example.donjonanddragons.equipements.Philtre;
import com.example.donjonanddragons.equipements.Sort;

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
