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

public class Game {
    private final Menu menu;
    private CharacterPlayer perso1;
    private final ArrayList<Case> plateau = new ArrayList<>();
    GameState state = GameState.WELCOME;

    boolean areYouDead;

    boolean continuePlay = true;

    public Game() {
        this.menu = new Menu();
        generateBoard();
    }

    private void generateBoard() {
        plateau.clear();
        for (int i = 1; i < 65; i++) {
            if (i <= 10) {
                plateau.add(new CaseEnnemi(new Gobelin(), new MenuFight(), new DBUse()));
            } else if (i <= 20) {
                plateau.add(new CaseEnnemi(new Sorcier(), new MenuFight(), new DBUse()));
            } else if (i <= 24) {
                plateau.add(new CaseEnnemi(new Dragon(), new MenuFight(), new DBUse()));
            } else if (i <= 30) {
                plateau.add(new CaseCaisse(new StandardPotion(), new Menu(), new DBUse()));
            } else if (i <= 32) {
                plateau.add(new CaseCaisse(new BigPotion(), new Menu(), new DBUse()));
            } else if (i <= 37) {
                plateau.add(new CaseArme(new Massue(), new Menu(), new DBUse()));
            } else if (i <= 41) {
                plateau.add(new CaseArme(new Epee(), new Menu(), new DBUse()));
            } else if (i <= 46) {
                plateau.add(new CaseArme(new Eclair(), new Menu(), new DBUse()));
            } else if (i <= 48) {
                plateau.add(new CaseArme(new FireBall(), new Menu(), new DBUse()));
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
        String num = menu.makeAChoice();
        perso1 = menu.menuSwap(num);
        menu.getMenu(perso1, num);
        generateBoard();
//        for (Case aCase : plateau) {
//            System.out.println(aCase.getClass().getName());
//        }
        menu.startPhrase();
        this.state = GameState.JEU;
    }

    public void next_turn() {
        while (continuePlay && !this.state.equals(GameState.FIN)) {
            String choice = menu.scanLetThrowDices();
            if (choice.equals("1")) {
                this.state = GameState.FIN;
            } else if (choice.equals("2")) {
                System.exit(0);
            } else {
                try {
                    movePlayer();
                } catch (PersonnageHorsPlateauException e) {
                    e.printStackTrace();
                    System.out.println("Bravo ! Vous avez gagne ! ");
                    perso1.setPosition(plateau.toArray().length);
                    this.state = GameState.FIN;
                    continuePlay = false;
                }
            }
        }
    }

    public void movePlayer() throws PersonnageHorsPlateauException {
        int throughtDices = perso1.diceResult();
//        throughDices = 1;
        perso1.updatePosition(throughtDices);
        if (perso1.getPosition() < plateau.toArray().length) {
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
        currentCase.interaction(perso1, menu.id);
        this.areYouDead = currentCase.consequences(plateau, perso1.getPosition());
        if (areYouDead) {
            menu.removeCharacterFromBDD(menu.id);
            this.state = GameState.FIN;
            menu.youLost();
        }
        if (currentCase instanceof CaseEnnemi caseEnnemi) {
            if (caseEnnemi.turnBack()) {
                int dices = perso1.diceResult();
                perso1.updateNegativePosition(dices);
                menu.youFear(dices,perso1);
            }
        }
    }

    public void end() {
        if (this.areYouDead) {
            if(menu.chooseRestartOrNot().equals("oui")){
                this.state = GameState.WELCOME;
            }else{
                System.exit(0);
            }
        } else {
            menu.printEnd();
            int choiceEnd = menu.scanChoiceEnd();
            if (choiceEnd == 1) {
                resetGame();
            } else if (choiceEnd == 2) {
                menu.menuSelect(perso1, perso1.getAttackObject(), perso1.getDefendObject());
                resetGame();
            } else {
                System.exit(0);
            }
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
//                    for (Case aCase : plateau) {
//                        System.out.println(aCase.getClass().getName());
//                    }
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

