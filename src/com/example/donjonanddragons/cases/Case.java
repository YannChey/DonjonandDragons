package com.example.donjonanddragons.cases;


import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.util.Optional;

public abstract class Case {
    public Case(){

    }
    public abstract void aEvent();

    @Override
    public String toString() {
       return "";
    }

    public abstract void interaction(CharacterPlayer character);

    public abstract Optional<Object> getContent();
}
