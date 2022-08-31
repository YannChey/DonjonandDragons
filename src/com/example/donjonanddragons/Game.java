package com.example.donjonanddragons;

import com.example.donjonanddragons.cases.*;
import com.example.donjonanddragons.ennemis.Dragon;
import com.example.donjonanddragons.ennemis.Gobelin;
import com.example.donjonanddragons.ennemis.Sorcier;
import com.example.donjonanddragons.equipements.armes.attaque.Eclair;
import com.example.donjonanddragons.equipements.armes.attaque.Epee;
import com.example.donjonanddragons.equipements.armes.attaque.FireBall;
import com.example.donjonanddragons.equipements.armes.attaque.Massue;
import com.example.donjonanddragons.equipements.potions.BigPotion;
import com.example.donjonanddragons.equipements.potions.StandardPotion;
import com.example.donjonanddragons.exceptions.PersonnageHorsPlateauException;
import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private int position;
    Scanner myObj = new Scanner(System.in);
    private final Menu menu;
    private CharacterPlayer perso1;
    private final ArrayList<Case> plateau = new ArrayList<>();
    GameState state = GameState.WELCOME;
    boolean continuePlay = true;

    public Game() {
        this.position = 1;
        this.menu = new Menu();
        generateBoard();
    }

    private void generateBoard() {
        plateau.clear();
        for (int i = 1; i < 65; i++) {
            if (i <= 10) {
                boolean caseGobelin = plateau.add(new CaseEnnemi(new Gobelin()));
            } else if (i <= 20) {
                plateau.add(new CaseEnnemi(new Sorcier()));
            } else if (i <= 24) {
                plateau.add(new CaseEnnemi(new Dragon()));
            } else if (i <= 30) {
                plateau.add(new CaseCaisse(new StandardPotion()));
            } else if (i <= 32) {
                plateau.add(new CaseCaisse(new BigPotion()));
            } else if (i <= 37) {
                plateau.add(new CaseArme(new Massue()));
            } else if (i <= 41) {
                plateau.add(new CaseArme(new Epee()));
            } else if (i <= 46) {
                plateau.add(new CaseArme(new Eclair()));
            } else if (i <= 48) {
                plateau.add(new CaseArme(new FireBall()));
            } else {
                plateau.add(new CaseVide());
            }
        }
        shuffle(this.plateau);
//        plateau.add(new CaseVide());
//        plateau.add(new CaseVide());
//        plateau.add(new CaseEnnemi());
//        plateau.add(new CaseArme());
//        plateau.add(new CaseCaisse());
//        plateau.add(new CaseVide());
    }

    public static <T> void shuffle(ArrayList<T> plateau) {
        Collections.shuffle(plateau);
    }

    private void welcome() {
        menu.displayWelcome();
        this.state = GameState.DEBUT;
    }

    public void start() {
        String name = menu.getCharacterType();
        perso1 = menu.createCharacter(name);
        menu.displayCharacter(perso1);
        generateBoard();
        for (Case aCase : plateau) {
            System.out.println(aCase.getClass().getName());
        }
        menu.startPhrase();
        this.state = GameState.JEU;
    }

    public void next_turn() {
        while (continuePlay) {
           menu.scanLetThrowDices();
            try {
                this.position = movePlayer();
            } catch (PersonnageHorsPlateauException e) {
                e.printStackTrace();
                this.position = plateau.toArray().length;
                this.state = GameState.FIN;
                continuePlay = false;
            }
        }
    }

    public int movePlayer() throws PersonnageHorsPlateauException {
        int DiceResult = menu.diceResult();
//        throughDices = 1;
        int new_position = this.position + DiceResult;
        if (new_position <= plateau.toArray().length) {
            menu.printDiceResult(DiceResult, new_position, plateau);
            evenements(new_position);
        } else {
            throw new PersonnageHorsPlateauException();
        }
        return new_position;
    }

    public void evenements(int new_position) {
        Case currentCase = plateau.get(new_position - 1);
        currentCase.aEvent();
        currentCase.interaction(perso1);
        boolean isNowEmpty = currentCase.isEmptyCase();
//        boolean isComeBack = currentCase.characterIsBack();
        if(isNowEmpty){
            plateau.set(new_position - 1, new CaseVide());
        }
//        else if (isComeBack){
//            new_position = new_position - menu.diceResult();
//        }
//todo interface à créer pour lier les ennemis, potions, armes... qui va juste avoir une méthode en commun checkStay() pour vérifier si il y a encore quelque chose sur la case.
    }

    public void end() {
        menu.printEnd();
        int choiceEnd = menu.scanChoiceEnd();
        if (choiceEnd == 1) {
            resetGame();
        } else if (choiceEnd == 2) {
            menu.menuSelect(perso1, perso1.getAttackObject(), perso1.getDefendObject());
            resetGame();
        }
    }

    public void runGame() {
        do {
            switch (this.state) {
                case WELCOME:
                    this.welcome();
                case DEBUT:
                    this.start();
                case JEU:
                    this.next_turn();
                case FIN:
                    for (Case aCase : plateau) {
                        System.out.println(aCase.getClass().getName());
                    }
                    this.end();
            }
        } while (continuePlay);
    }

    public void resetGame() {
        generateBoard();
        this.position = 1;
        this.state = GameState.JEU;
        continuePlay = true;
    }

}

