package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.equipements.potions.BigPotion;
import com.example.donjonanddragons.equipements.potions.Potion;
import com.example.donjonanddragons.equipements.potions.StandardPotion;
import com.example.donjonanddragons.personnages.CharacterPlayer;

public class CaseCaisse extends Case{
    private Potion potion;
    public CaseCaisse(){
        this.potion = this.givePotion();
    }

    public Potion givePotion(){
        int throughDices;
        Potion potion;
        throughDices = (int) (Math.random() * 6) + 1;
        if(throughDices <= 4){
            potion = new StandardPotion();
        }
        else {
            potion = new BigPotion();
        }
        return potion;
    }

    @Override
    public void aEvent() {
        System.out.println(this.potion);
    }

    @Override
    public void interaction(CharacterPlayer character) {

    }
}
