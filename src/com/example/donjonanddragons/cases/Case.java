package com.example.donjonanddragons.cases;


import com.example.donjonanddragons.personnages.CharacterPlayer;

public abstract class Case {
    public Case(){

    }
    public abstract void aEvent();

    @Override
    public String toString() {
       return "";
    }

    public abstract void interaction(CharacterPlayer character);
}
