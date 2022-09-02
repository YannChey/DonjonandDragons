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
    private final Menu menu;
    private CharacterPlayer perso1;
    private final ArrayList<Case> plateau = new ArrayList<>();
    GameState state = GameState.WELCOME;

    boolean continuePlay = true;

    public Game() {
        this.menu = new Menu();
        generateBoard();
    }

    private void generateBoard() {
        plateau.clear();
        for (int i = 1; i < 65; i++) {
            if (i <= 10) {
                plateau.add(new CaseEnnemi(new Gobelin(), new MenuFight()));
            } else if (i <= 20) {
                plateau.add(new CaseEnnemi(new Sorcier(), new MenuFight()));
            } else if (i <= 24) {
                plateau.add(new CaseEnnemi(new Dragon(), new MenuFight()));
            } else if (i <= 30) {
                plateau.add(new CaseCaisse(new StandardPotion(), new Menu()));
            } else if (i <= 32) {
                plateau.add(new CaseCaisse(new BigPotion(), new Menu()));
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
        while (continuePlay && !this.state.equals(GameState.FIN)) {
           menu.scanLetThrowDices();
            try {
                movePlayer();
            } catch (PersonnageHorsPlateauException e) {
                e.printStackTrace();
                perso1.setPosition(plateau.toArray().length);
                this.state = GameState.FIN;
                continuePlay = false;
            }
        }
    }

    public void movePlayer() throws PersonnageHorsPlateauException {
        int throughtDices = perso1.diceResult();
//        throughDices = 1;
        perso1.updatePosition(throughtDices);
        if (perso1.getPosition() <= plateau.toArray().length) {
            menu.printDiceResult(throughtDices, perso1.getPosition(), plateau);
            evenements();
        } else {
            throw new PersonnageHorsPlateauException();
        }
    }

    public void evenements() {
        Case currentCase = plateau.get(perso1.getPosition() - 1);
        currentCase.aEvent();
        currentCase.interaction(perso1);
        boolean areYouDead = currentCase.consequences(plateau, perso1.getPosition());
        if(areYouDead){
            this.state = GameState.FIN;
        }
        if (currentCase instanceof CaseEnnemi caseEnnemi){
            if(caseEnnemi.turnBack()){
                int dices = perso1.diceResult();
                perso1.updateNegativePosition(dices);
                System.out.println("Vous avez fuit et recule de " + dices + " vous etes a la case " + perso1.getPosition());
            }
        }
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
        perso1.setPosition(1);
        this.state = GameState.JEU;
        continuePlay = true;
    }

}

