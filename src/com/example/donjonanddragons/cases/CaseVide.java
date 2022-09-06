package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.util.ArrayList;

public class CaseVide extends Case {

    public CaseVide() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void interaction(CharacterPlayer character) {
    }

    @Override
    public void interaction(CharacterPlayer character, int id) {

    }

    @Override
    public boolean consequences(ArrayList<Case> plateau, int position) {
        return false;
    }

    public void aEvent() {
        System.out.println("Il ne se passe rien ici!");
    }

//    @Override
//    public Optional<Object> getContent() {
//        return Optional.empty();
//    }
}
