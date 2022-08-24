package com.example.donjonanddragons;
import java.util.Scanner;
import java.lang.Math;

public class Game {
    private int max = 6;
    private int min= 1;
    private int range = max - min +1;
    private int position;
    Scanner myObj = new Scanner(System.in);
    private Menu menu;
    private Character perso1;

    public Game(){
        this.position = 1;
        menu = new Menu();
    }

    public void start(){
        menu.displayWelcome();
        String name = menu.getCharacterType();
        perso1 = menu.createCharacter(name);
//        perso1 = new Character(name);
        menu.showStats(perso1);
        menu.changeName(perso1);
        menu.menuSelect(perso1, perso1.getAttackObject(), perso1.getDefendObject());
        System.out.println("C'est parti ! Votre objectif sera d'atteindre la case 64. Bonne chance !");
        System.out.println("Pour commencer, veuillez lancer les des (cliquez sur enter)");
    }

    public void next_turn(){
        Scanner turnplay = new Scanner(System.in);
        System.out.println("Veuillez lancer les des (cliquez sur enter)");
        turnplay.nextLine();
        this.position = movePlayer();
    }

    public boolean isOver(){
        return this.position == 64;
    }

    public int movePlayer(){
        int throughDices;
        throughDices = (int)(Math.random()*range) + min;
        int new_position = this.position + throughDices;
        if (new_position <= 64){
            System.out.println("Le resultat de votre lancer de des est : "+ throughDices +". Votre nouvelle position est : " + new_position + "/64");
        }else{
            new_position = 64;
            System.out.println("Le resultat de votre lancer de des est : "+ throughDices +". Votre nouvelle position est : " + new_position + "/64");
        }
        return new_position;
    }
    public void end(){
        int choiceEnd;
        System.out.println("Bravo ! Vous avez gagnés !");
        System.out.println("Tapez 1 pour recommencer une partie, tapez 2 pour quitter le jeu et revenir dans le menu");
        choiceEnd = myObj.nextInt();
        if (choiceEnd == 1){
            do {
                next_turn();
            } while (!isOver());
            this.position=1;
            end();
        }else if(choiceEnd == 2){
            menu.menuSelect(perso1, perso1.getAttackObject(), perso1.getDefendObject());
            do {
                next_turn();
            } while (!isOver());
            this.position=1;
            end();
        }
    }

    public void gameDevelopment(){
        start();
        do {
            next_turn();
        } while (!isOver());
        this.position=1;
        end();
    }
}
