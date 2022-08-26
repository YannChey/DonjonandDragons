package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.ennemis.Dragon;
import com.example.donjonanddragons.ennemis.Ennemi;
import com.example.donjonanddragons.ennemis.Gobelin;
import com.example.donjonanddragons.ennemis.Sorcier;

public class CaseEnnemi extends Case{
    Ennemi ennemi;
    public CaseEnnemi(){
        this.ennemi = this.ennemiRace();
    }

    public Ennemi ennemiRace(){
        Ennemi ennemi;
        int throughDices;
        throughDices = (int) (Math.random() * 100) + 1;
        if(throughDices < 40){
            ennemi = new Gobelin();
        }
        else if(throughDices < 75){
            ennemi = new Sorcier();
        }else{
            ennemi = new Dragon();
        }
        return ennemi;
    }

    @Override
    public void aEvent() {
        System.out.println("Un ennemi est present ici !");
        System.out.println(this.ennemi);
    }
}
