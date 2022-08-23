package com.example.donjonanddragons;

public class Magician extends Character{
    public Magician(String name){
        setAttackObject(new EquipementOffensif(name));
        setDefendObject(new EquipementDefensif(name));
        setName("Magician");
        setLife(6);
        setPower(15);
    }
}
