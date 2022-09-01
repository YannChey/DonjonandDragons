package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.CaisseInteractions;
import com.example.donjonanddragons.equipements.potions.Potion;
import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.util.ArrayList;

public class CaseCaisse extends Case {
    private final Potion potion;

    private final CaisseInteractions caisseInteractions;
    boolean isNowEmpty;
    public CaseCaisse(Potion potion, CaisseInteractions caisseInteractions){
//        this.potion = this.givePotion();
        this.potion = potion;
        this.caisseInteractions = caisseInteractions;
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
            this.caisseInteractions.yourLife(character.getLife());
            this.isNowEmpty = true;
        }else{
            this.caisseInteractions.displayYouCantTakeThisPotion();
        }
    }

    @Override
    public boolean consequences(ArrayList<Case> plateau, int position) {
        if(this.isNowEmpty){
            plateau.set(position - 1, new CaseVide());
        }
        return false;
    }

    public Potion getPotion() {
            return potion;
    }
}
