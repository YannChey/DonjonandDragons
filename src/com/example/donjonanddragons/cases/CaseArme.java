package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.equipements.armes.Arme;
import com.example.donjonanddragons.equipements.armes.EquipementOffensif;
import com.example.donjonanddragons.equipements.armes.Sort;

public class CaseArme extends Case{
    EquipementOffensif arme;
    public CaseArme(){
        this.arme = this.weapon();
    }

    public EquipementOffensif weapon(){
        int throughDices;
        throughDices = (int) (Math.random() * 100) + 1;
        if (throughDices <= 50){
            this.arme = new Arme();
        }else{
            this.arme = new Sort();
        }
        return arme;
    }

    @Override
    public void aEvent() {
        System.out.println("Vous avez trouve une arme ! Celle-ci est : " + arme.getName());
        System.out.println(this.arme);
    }
}
