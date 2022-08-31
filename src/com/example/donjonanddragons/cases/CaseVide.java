package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.inter.checkStay;
import com.example.donjonanddragons.personnages.CharacterPlayer;

public class CaseVide extends Case implements checkStay {

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
    public boolean isEmptyCase() {
        return true;
    }

    public void aEvent(){
        System.out.println("Il ne se passe rien ici!");
    }

//    @Override
//    public Optional<Object> getContent() {
//        return Optional.empty();
//    }
}
