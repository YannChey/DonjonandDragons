package com.example.donjonanddragons;

import com.example.donjonanddragons.cases.*;
import com.example.donjonanddragons.ennemis.Dragon;
import com.example.donjonanddragons.ennemis.Ennemi;
import com.example.donjonanddragons.ennemis.Gobelin;
import com.example.donjonanddragons.ennemis.Sorcier;
import com.example.donjonanddragons.equipements.armes.Eclair;
import com.example.donjonanddragons.equipements.armes.Epee;
import com.example.donjonanddragons.equipements.armes.FireBall;
import com.example.donjonanddragons.equipements.armes.Massue;
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
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public Game() {
        this.position = 1;
        this.menu = new Menu();
        generateBoard();
    }

    private void generateBoard() {
        plateau.clear();
        for(int i =1;i<65;i++){
            if (i <= 10){
                plateau.add(new CaseEnnemi(new Gobelin()));
            }else if (i <= 20){
                plateau.add(new CaseEnnemi(new Sorcier()));
            }else if (i <= 24){
                plateau.add(new CaseEnnemi(new Dragon()));
            }else if(i <= 30){
                plateau.add(new CaseCaisse(new StandardPotion()));
            }else if(i <= 32){
                plateau.add(new CaseCaisse(new BigPotion()));
            }else if(i <= 37){
                plateau.add(new CaseArme(new Massue()));
            }
            else if(i <= 41){
                plateau.add(new CaseArme(new Epee()));
            }
            else if(i <= 46){
                plateau.add(new CaseArme(new Eclair()));
            }
            else if(i <= 48){
                plateau.add(new CaseArme(new FireBall()));
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
        generateBoard();
        for (Case aCase : plateau) {
            System.out.println(aCase.getClass().getName());
        }
        System.out.println("C'est parti ! Votre objectif sera d'atteindre la case 64. Bonne chance !");
        System.out.println(ANSI_RED + "Pour commencer, veuillez lancer les des (cliquez sur enter)" + ANSI_RESET);
    }

    public void next_turn() {
        Scanner turnplay = new Scanner(System.in);
        System.out.println(ANSI_RED + "Veuillez lancer les des (cliquez sur enter)" + ANSI_RESET);
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
                generateBoard();
                do {
                    next_turn();
                } while (!isOver());
                this.position = 1;
                end();
            } else if (choiceEnd == 2) {
                menu.menuSelect(perso1, perso1.getAttackObject(), perso1.getDefendObject());
                generateBoard();
                do {
                    next_turn();
                } while (!isOver());
                this.position = 1;
                end();
            }
    }
}

