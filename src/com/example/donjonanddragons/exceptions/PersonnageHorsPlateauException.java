package com.example.donjonanddragons.exceptions;


import com.example.donjonanddragons.Game;

public class PersonnageHorsPlateauException extends Exception{
    public PersonnageHorsPlateauException (){
        super("Vous avez dépassé 64 !");
    }
}
