package com.example.donjonanddragons;

public class Guerrier extends Character{
     public Guerrier(String name){
         setAttackObject(new EquipementOffensif(name));
         setDefendObject(new EquipementDefensif(name));
         setName("Warrior");
         setLife(10);
         setPower(10);
     }
}
