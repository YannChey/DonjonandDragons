package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.inter.checkStay;
import com.example.donjonanddragons.equipements.potions.Potion;
import com.example.donjonanddragons.personnages.CharacterPlayer;

public class CaseCaisse extends Case implements checkStay {
    private Potion potion;
    boolean isNowEmpty;
    public CaseCaisse(Potion potion){
//        this.potion = this.givePotion();
        this.potion = potion;
    }

    @Override
    public void aEvent() {
        System.out.println(this.potion);
    }

    @Override
    public void interaction(CharacterPlayer character) {
        this.isNowEmpty = false;
        if (character.getLife() < 15){
            character.setLife(Math.min(character.getLife() + this.potion.getLevel(), 15));
            System.out.println("Votre niveau de vie augmente !");
            System.out.println("Votre vie est desormais de : " + character.getLife());
            //todo utiliser la potion
            this.isNowEmpty = true;
        }else{
            System.out.println("Votre vie est deja au maximum");
        }
    }

    @Override
    public boolean isEmptyCase() {
        return this.isNowEmpty;
    }

    //    @Override
//    public Optional<Object> getContent() {
//        return Optional.of(this.potion);
//    }

    public Potion getPotion() {
            return potion;
    }
}
