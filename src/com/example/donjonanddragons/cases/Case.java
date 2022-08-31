package com.example.donjonanddragons.cases;


import com.example.donjonanddragons.inter.checkStay;
import com.example.donjonanddragons.personnages.CharacterPlayer;

public abstract class Case implements checkStay{
    public Case(){

    }
    public abstract void aEvent();

    @Override
    public String toString() {
       return "";
    }

    public abstract void interaction(CharacterPlayer character);

    @Override
    public abstract boolean isEmptyCase();

    //    public abstract Optional<Object> getContent();
}
