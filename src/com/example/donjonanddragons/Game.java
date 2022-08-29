package com.example.donjonanddragons;

import com.example.donjonanddragons.cases.*;
import com.example.donjonanddragons.exceptions.PersonnageHorsPlateauException;
import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private int position;
    Scanner myObj = new Scanner(System.in);
    private final Menu menu;
    private CharacterPlayer perso1;
    private final ArrayList<Case> plateau = new ArrayList<>();

    public Game() {
        this.position = 1;
        this.menu = new Menu();
        generateBoard();
    }

    private void generateBoard() {
        plateau.clear();
        for(int i =1;i<64;i++){
            if (i <= 24){
                plateau.add(new CaseEnnemi());
            }else if(i <= 32){
                plateau.add(new CaseCaisse());
            }else if(i <= 48){
                plateau.add(new CaseArme());
            }else{
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

    public static<T> void shuffle(ArrayList<T> plateau){
        Collections.shuffle(plateau);
    }

    public void start() {
        menu.displayWelcome();
        String name = menu.getCharacterType();
        perso1 = menu.createCharacter(name);
        menu.displayCharacter(perso1);
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

    public int movePlayer() throws PersonnageHorsPlateauException {
        int throughDices;
        int min = 1;
        int max = 6;
        int range = max - min + 1;
        throughDices = (int) (Math.random() * range) + min;
//        throughDices = 1;
        int new_position = this.position + throughDices;
        if (new_position <= plateau.toArray().length) {
            System.out.println("Le resultat de votre lancer de des est : " + throughDices + ". Votre nouvelle position est : " + new_position + "/" + plateau.toArray().length);
            Case currentCase = plateau.get(new_position-1);
            currentCase.aEvent();
            currentCase.interaction(perso1);
        } else {
            throw new PersonnageHorsPlateauException();
        }
        return new_position;
    }

    public boolean isOver() {
        return this.position == plateau.toArray().length;
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
        System.out.println("Bravo ! Vous avez gagne !");
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

