package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.equipements.armes.*;
import com.example.donjonanddragons.personnages.CharacterPlayer;
import com.example.donjonanddragons.personnages.Guerrier;

public class CaseArme extends Case{
    private EquipementOffensif arme;
    public CaseArme(){
        this.arme = this.weapon();
    }

    public EquipementOffensif weapon(){
        int throughDices;
        throughDices = (int) (Math.random() * 100) + 1;
        if (throughDices <= 30){
            this.arme = new Massue();
        }else if(throughDices <= 50){
            this.arme = new Epee();
        }else if(throughDices <= 60){
            this.arme = new FireBall();
        }else{
            this.arme = new Eclair();
        }
        return arme;
    }

    @Override
    public void aEvent() {
        System.out.println("Vous avez trouve une arme ! Celle-ci est une : " + arme.getName());
        System.out.println(this.arme);
    }

    @Override
    public void interaction(CharacterPlayer character) {
        if (character instanceof Guerrier guerrier){
            guerrier.setAttackObject(this.arme);
        }
    }
}
