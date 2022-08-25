package com.example.donjonanddragons.cases;

import org.w3c.dom.events.Event;

public class CaseVide extends Case{

    public CaseVide() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void aEvent(){
        System.out.println("Il ne se passe rien ici!");
    }
}
