package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.equipements.potions.Potion;

public class CaseCaisse extends Case{
    Potion potion;
    public CaseCaisse(){
        this.potion = this.givePotion();
    }

    public Potion givePotion(){
        return new Potion();
    }

    @Override
    public void aEvent() {
        System.out.println("Vous avez trouve une potion !");
        System.out.println(this.potion);
    }
}
