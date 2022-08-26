package com.example.donjonanddragons;

import com.example.donjonanddragons.cases.*;
import com.example.donjonanddragons.exceptions.PersonnageHorsPlateauException;
import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int max = 6;
    private int min = 1;
    private int range = max - min + 1;
    private int position;
    Scanner myObj = new Scanner(System.in);
    private Menu menu;
    private CharacterPlayer perso1;
    private ArrayList<Case> plateau = new ArrayList<>();

    public Game() {
        this.position = 1;
        this.menu = new Menu();
//        for(int i=0;i<64;i++){
//            plateau.add(new CaseVide());
//        }
        generateBoard();
    }

    private void generateBoard() {
        plateau.clear();
        plateau.add(new CaseVide());
        plateau.add(new CaseVide());
        plateau.add(new CaseEnnemi());
        plateau.add(new CaseArme());
        plateau.add(new CaseCaisse());
        plateau.add(new CaseVide());
    }

    public void start() {
        menu.displayWelcome();
        String name = menu.getCharacterType();
        perso1 = menu.createCharacter(name);
        menu.showStats(perso1);
        menu.changeName(perso1);
        menu.menuSelect(perso1, perso1.getAttackObject(), perso1.getDefendObject());
        System.out.println("C'est parti ! Votre objectif sera d'atteindre la case 64. Bonne chance !");
        System.out.println("Pour commencer, veuillez lancer les des (cliquez sur enter)");
    }

    public void next_turn() {
        Scanner turnplay = new Scanner(System.in);
        System.out.println("Veuillez lancer les des (cliquez sur enter)");
        turnplay.nextLine();
        try{
            this.position = movePlayer();
        } catch(PersonnageHorsPlateauException e){
            e.printStackTrace();
            this.position = plateau.toArray().length;
        }
    }

    public boolean isOver() {
        return this.position == plateau.toArray().length;
    }

    public int movePlayer() throws PersonnageHorsPlateauException {
        int throughDices;
//        throughDices = (int) (Math.random() * range) + min;
        throughDices = 1;
        int new_position = this.position + throughDices;
        if (new_position <= plateau.toArray().length) {
            System.out.println("Le resultat de votre lancer de des est : " + throughDices + ". Votre nouvelle position est : " + new_position + "/" + plateau.toArray().length);
            plateau.get(new_position-1).aEvent();
        } else {
            throw new PersonnageHorsPlateauException();
        }
        return new_position;
    }

    public void gameDevelopment() {
        start();
        do {
            next_turn();
        } while (!isOver());
        this.position = 1;
        generateBoard();
        end();
    }

    public void end(){
        int choiceEnd;
        System.out.println("Bravo ! Vous avez gagné !");
        System.out.println("Tapez 1 pour recommencer une partie, tapez 2 pour quitter le jeu et revenir dans le menu");
        choiceEnd = myObj.nextInt();
            if (choiceEnd == 1) {
                do {
                    next_turn();
                } while (!isOver());
                this.position = 1;
                generateBoard();
                end();
            } else if (choiceEnd == 2) {
                menu.menuSelect(perso1, perso1.getAttackObject(), perso1.getDefendObject());
                do {
                    next_turn();
                } while (!isOver());
                this.position = 1;
                generateBoard();
                end();
            }
    }
}

