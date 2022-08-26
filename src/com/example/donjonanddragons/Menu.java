package com.example.donjonanddragons;

import com.example.donjonanddragons.equipements.armes.EquipementDefensif;
import com.example.donjonanddragons.equipements.armes.EquipementOffensif;
import com.example.donjonanddragons.personnages.Character;
import com.example.donjonanddragons.personnages.Guerrier;
import com.example.donjonanddragons.personnages.Magician;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner myObj = new Scanner(System.in);

    public String getCharacterType() {

        String name;
        System.out.println("Pour commencer, choisissez quel personnage vous souhaitez incarner entre le Magician ou le Warrior");
        name = myObj.nextLine().toLowerCase();

        while (!name.equals("warrior") && !name.equals("magician")) {
            System.out.println("Error. You have to choose a hero between Warrior or Magician");
            try {
                name = myObj.nextLine().toLowerCase();
            } catch (InputMismatchException e) {
            }
        }
        System.out.println("Username is: " + name);
        return name;
    }

    public String defineNameCharacter() {
        String characterName;
        System.out.println("Veuillez maintenant definir un nom pour votre heros");
        characterName = myObj.nextLine().toLowerCase();
        return characterName;
    }

    public void displayWelcome() {
        System.out.println("Bienvenue dans notre nouveau jeu Donjons et Dragons !");
    }

    public String choiceMenu() {
        String choiceMenu;
        System.out.println("Vous pouvez consulter votre personnage en tapant 1, changer de personnage ou de nom en tapant 2 et si tout est ok, ON ATTAQUE ! en tapant 3");
        choiceMenu = myObj.nextLine().toLowerCase();
        return choiceMenu;
    }

    public String choiceChangement() {
        String choiceChangement;
        System.out.println("1 pour changer de personnage, 2 pour changer de nom");
        choiceChangement = myObj.nextLine().toLowerCase();
        return choiceChangement;
    }

    public void menuSelect(Character perso1, EquipementOffensif attack1, EquipementDefensif defend1) {
        String choixUtilisateur;
        do {
            choixUtilisateur = choiceMenu();
            switch (choixUtilisateur) {
                case "1":
                    System.out.println(perso1);
                    System.out.println(attack1);
                    System.out.println(defend1);
                    break;
                case "2":
                    choiceChangement();
                    if (choiceChangement().equals("1")) {
                        String name = getCharacterType();
                        perso1 = createCharacter(name);
                        attack1 = perso1.getAttackObject();
                        defend1 = perso1.getDefendObject();
                    } else if (choiceChangement().equals("2")) {
                        perso1.setName(defineNameCharacter());
                    }
                    break;
                case "3":
                    System.out.println("Bienvenue dans D&D !");
                    break;
            }
        } while (!choixUtilisateur.equals("3"));
    }

    public void showStats(Character perso) {
        System.out.println(perso);
        System.out.println(perso.getAttackObject());
        System.out.println(perso.getDefendObject());
    }

    public void changeName(Character perso) {
        String characterName = defineNameCharacter();
        perso.setName(characterName);
        System.out.println("Votre personnage s'appelle désormais : " + characterName);
        System.out.println(perso);
    }

    public Character createCharacter(String name) {
        Character perso1;
        if (name.equals("warrior")) {
            perso1 = new Guerrier();
        } else {
            perso1 = new Magician();
        }
        return perso1;
    }

}


