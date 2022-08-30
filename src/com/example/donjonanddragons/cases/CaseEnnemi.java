package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.Game;
import com.example.donjonanddragons.ennemis.Dragon;
import com.example.donjonanddragons.ennemis.Ennemi;
import com.example.donjonanddragons.ennemis.Gobelin;
import com.example.donjonanddragons.ennemis.Sorcier;
import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CaseEnnemi extends Case{
    Ennemi ennemi;
    public CaseEnnemi(Ennemi ennemiRace){
        this.ennemi = ennemiRace;
    }

    public Ennemi ennemiRace(){
        Ennemi ennemi;
        int throughDices;
        throughDices = (int) (Math.random() * 24) + 1;
        if(throughDices < 11){
            ennemi = new Gobelin();
        }
        else if(throughDices < 21){
            ennemi = new Sorcier();
        }else{
            ennemi = new Dragon();
        }
        return ennemi;
    }

    @Override
    public void aEvent() {
        System.out.println("Un ennemi est present ici !");
        System.out.println(this.ennemi);
    }

    @Override
    public void interaction(CharacterPlayer character) {
        System.out.println("C'est l'heure du combat !!!");
        Scanner attack = new Scanner(System.in);
        System.out.println("Attaquez le mechant ! (cliquez sur enter)");
        attack.nextLine();
        int somme = character.getPower()+character.getAttackObject().getLevel();
        System.out.println("Votre force d'attaque est de : " + somme + " points. Vous lui infligez vos degats !");
        ennemi.setLife(Math.max(ennemi.getLife()-(character.getPower()+character.getAttackObject().getLevel()),0));
        if(ennemi.getLife()<=0){
            System.out.println("Il reste : " + ennemi.getLife() + " PV a votre ennemi");
            System.out.println("Bravo vous avez vaincu l'ennemi !");

        }else{
            System.out.println("Il reste : " + ennemi.getLife() + " PV a votre ennemi");
            System.out.println("Il n'est pas mort ! L'ennemi riposte");
            character.setLife(character.getLife() - ennemi.getAttack());
            if (character.getLife() <= 0){
                System.out.println("L'ennemi vous a attaque, il vous reste : " + character.getLife() + " PV");
                System.out.println("Apparement vous avez vraiment pris une trop grosse pichenette... Vous etes mort !");
                Game game = new Game();
                game.end();
            }else{
                System.out.println("L'ennemi vous a attaque, il vous reste : " + character.getLife() + " PV");
                System.out.println("L'ennemi s'enfui en vous ayant mis une belle pichenette");
            }
        }
    }

    public Ennemi getEnnemi() {
        return ennemi;
    }

    @Override
    public Optional<Object> getContent() {
        return Optional.of(this.ennemi);
    }
}
