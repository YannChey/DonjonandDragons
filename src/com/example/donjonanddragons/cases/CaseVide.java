package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.personnages.CharacterPlayer;
import org.w3c.dom.events.Event;

public class CaseVide extends Case{

    public CaseVide() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void interaction(CharacterPlayer character) {

    }

    public void aEvent(){
        System.out.println("Il ne se passe rien ici!");
    }
}
