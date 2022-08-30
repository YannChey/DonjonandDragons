package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.equipements.potions.BigPotion;
import com.example.donjonanddragons.equipements.potions.Potion;
import com.example.donjonanddragons.equipements.potions.StandardPotion;
import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.util.ArrayList;
import java.util.Optional;

public class CaseCaisse extends Case{
    private Potion potion;
    public CaseCaisse(Potion potion){
//        this.potion = this.givePotion();
        this.potion = potion;
    }

    public Potion givePotion(){
        int throughDices;
        Potion potion;
        throughDices = (int) (Math.random() * 8) + 1;
        if(throughDices <= 6){
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
        if (character.getLife() < 15){
            character.setLife(Math.min(character.getLife() + this.potion.getLevel(), 15));
            System.out.println("Votre niveau de vie augmente !");
            System.out.println("Votre vie est desormais de : " + character.getLife());
        }else{
            System.out.println("Votre vie est deja au maximum");
        }
    }

    @Override
    public Optional<Object> getContent() {
        return Optional.of(this.potion);
    }
}
