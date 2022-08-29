package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.ennemis.Dragon;
import com.example.donjonanddragons.ennemis.Ennemi;
import com.example.donjonanddragons.ennemis.Gobelin;
import com.example.donjonanddragons.ennemis.Sorcier;
import com.example.donjonanddragons.personnages.CharacterPlayer;

public class CaseEnnemi extends Case{
    Ennemi ennemi;
    public CaseEnnemi(Ennemi ennemiRace){
        this.ennemi = ennemiRace;
    }

    public Ennemi ennemiRace(){
        Ennemi ennemi;
        int throughDices;
        throughDices = (int) (Math.random() * 24) + 1;
        if(throughDices < 11){
            ennemi = new Gobelin();
        }
        else if(throughDices < 21){
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

    @Override
    public void interaction(CharacterPlayer character) {
        System.out.println("C'est l'heure du combat !!!");
    }
}
