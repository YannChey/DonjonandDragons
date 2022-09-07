package com.example.donjonanddragons;

import com.example.donjonanddragons.inter.FightInteractions;

import java.util.Scanner;

public class MenuFight implements FightInteractions {

    public boolean scanChoiceAttackEnnemi(){
        String areYouAttacking;
        Scanner attack = new Scanner(System.in);
        System.out.println("Voulez-vous attaquer le mechant ? (1) / Voulez-vous fuir ? (2)");
        areYouAttacking = attack.nextLine();
        return areYouAttacking.equals("1");
    }

    @Override
    public boolean userWantToFlee() {
        return scanChoiceAttackEnnemi();
    }

    @Override
    public void displayStartGame() {
        System.out.println("C'est l'heure du combat !!!");
    }

    @Override
    public void isAnEnnemy() {
        System.out.println("Un ennemi est present !");
    }

    public void damageAttack(int somme){
        System.out.println("Votre force d'attaque est de : " + somme + " points. Vous lui infligez vos degats !");
    }


    @Override
    public void displayEnnemyKill(int life) {
        System.out.println("Il reste : " + life + " PV a votre ennemi");
        System.out.println("Bravo vous avez vaincu l'ennemi !");
    }

    @Override
    public void displayEnnemySurvive(int life) {
        System.out.println("Il reste : " + life + " PV a votre ennemi");
        System.out.println("Il n'est pas mort ! L'ennemi riposte");
    }

    @Override
    public void displayYouAreDead(int life) {
        System.out.println("L'ennemi vous a attaque, il vous reste : " + life + " PV");
        System.out.println("Apparement vous avez vraiment pris une trop grosse pichenette... Vous etes mort !");
    }

    @Override
    public void displayYouAreAlive(int life) {
        System.out.println("L'ennemi vous a attaque, il vous reste : " + life + " PV");
        System.out.println("Vous avez encore de la chance d'être en vie...");
    }

    @Override
    public void flee() {
        System.out.println("Vous fuyez comme un lache et l'ennemi vous regarde avec dedain tout en rigolant");
    }

}
