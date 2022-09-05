package com.example.donjonanddragons;

import com.example.donjonanddragons.cases.Case;
import com.example.donjonanddragons.equipements.armes.defense.EquipementDefensif;
import com.example.donjonanddragons.equipements.armes.attaque.EquipementOffensif;
import com.example.donjonanddragons.personnages.CharacterPlayer;
import com.example.donjonanddragons.personnages.Guerrier;
import com.example.donjonanddragons.personnages.Magician;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements CaisseInteractions, ArmeInteractions {
    Scanner myObj = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    DBConnection dbConnection = new DBConnection();

    public void displayWelcome() {
        System.out.println(ANSI_RED + "Bienvenue dans notre nouveau jeu Donjons et Dragons !" + ANSI_RESET);
    }

    public String makeAChoice() {
        String num;
        System.out.println("Bien le bonjour ! Souhaitez-vous créer un nouveau personnage ou reprendre une partie en cours? (Tapez 1 ou 2)");
        num = myObj.nextLine().toLowerCase();
        while (!num.equals("1") && !num.equals("2")) {
            System.out.println(ANSI_RED + "Vous devez choisir 1 ou 2 ! " + ANSI_RESET);
            try {
                num = myObj.nextLine().toLowerCase();
            } catch (InputMismatchException e) {
            }
        }
        return num;
    }

    public CharacterPlayer menuSwap(String num) {
        CharacterPlayer perso1;
        if (num.equals("1")) {
            perso1 = insertCharacterFromBDD();
        }
        else {
            displayCharactersFromBDD();
            int id = selectCharacterFromBDD();
            perso1 = getCharacterFromDBB(id);
        }
        return perso1;
    }

    public String getCharacterType() {
        String name;
        System.out.println("Pour commencer, choisissez quel personnage vous souhaitez incarner entre le Magician ou le Warrior");
        name = myObj.nextLine().toLowerCase();

        while (!name.equals("warrior") && !name.equals("magician")) {
            System.out.println(ANSI_RED + "Error. You have to choose a hero between Warrior or Magician" + ANSI_RESET);
            try {
                name = myObj.nextLine().toLowerCase();
            } catch (InputMismatchException e) {
            }
        }
        System.out.println("Vous avez choisi le: " + name);
        return name;
    }

    public CharacterPlayer createCharacter(String name) {
        CharacterPlayer perso1;
        if (name.equals("warrior")) {
            perso1 = new Guerrier();
        } else {
            perso1 = new Magician();
        }
        return perso1;
    }

    public CharacterPlayer insertCharacterFromBDD() {
        String name = getCharacterType();
        CharacterPlayer characterPlayer = createCharacter(name);
        try {
            Statement st = dbConnection.connectionDB();
//            System.out.println("Connexion réussie");
//            ResultSet rs = st.executeQuery("SELECT * FROM hero");
            st.executeUpdate("INSERT INTO `hero`(`Type`, `Nom`, `NiveauVie`, `NiveauForce`, `Weapon`, `Bouclier`) " +
                    "VALUES ('" + name + "','" + characterPlayer.getName() + "','" + characterPlayer.getLife() + "','"
                    + characterPlayer.getPower() + "','" + characterPlayer.getAttackObject().getName() + "','" + characterPlayer.getDefendObject().getName() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characterPlayer;
    }

    public void displayCharactersFromBDD() {
        try {
            Statement st = dbConnection.connectionDB();
            ResultSet rs = st.executeQuery("SELECT * FROM hero");
            while (rs.next()) {
                System.out.println("Votre personnage : " + rs.getString("Id") + " de type : " + rs.getString("Type") +
                        " qui a comme nom : " + rs.getString("Nom") + " d'un niveau de vie de : " + rs.getString("NiveauVie")
                        + " et un niveau de force de : " + rs.getString("NiveauForce") + " avec une arme qui est : " + rs.getString("Weapon")
                        + " et une défense qui est : " + rs.getString("Bouclier"));
//                System.out.println(rs.getString("Type"));
//                System.out.println(rs.getString("Nom"));
//                System.out.println(rs.getString("NiveauVie"));
//                System.out.println(rs.getString("NiveauForce"));
//                System.out.println(rs.getString("Weapon"));
//                System.out.println(rs.getString("Bouclier"));
//                CharacterPlayer c = new Guerrier(rs.getString("Id"),
//                                                    ..., .... , ....)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int selectCharacterFromBDD() {
        int id;
        System.out.println("Pour commencer, sélectionner le personnage que vous souhaitez jouer");
        id = Integer.parseInt(myObj.nextLine().toLowerCase());
        return id;
    }

    public void getCharacterFromDBB(int id) {
        try {
            Statement st = dbConnection.connectionDB();
            ResultSet rs = st.executeQuery("SELECT * FROM hero WHERE ID = " + id + "");
            while (rs.next()) {
                System.out.println("Votre personnage : " + rs.getString("Id") + " de type : " + rs.getString("Type") +
                        " qui a comme nom : " + rs.getString("Nom") + " d'un niveau de vie de : " + rs.getString("NiveauVie")
                        + " et un niveau de force de : " + rs.getString("NiveauForce") + " avec une arme qui est : " + rs.getString("Weapon")
                        + " et une défense qui est : " + rs.getString("Bouclier"));
                if (rs.getString("Type").equals("warrior")){
                    CharacterPlayer c = new Guerrier(rs.getInt("Id"),rs.getString("Type"),rs.getString("Nom"),
                            rs.getInt("NiveauVie"),rs.getInt("NiveauForce"),rs.getString("Weapon"),rs.getString("Bouclier"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getMenu(CharacterPlayer perso1) {
        changeName(perso1);
        showStats(perso1);
        menuSelect(perso1, perso1.getAttackObject(), perso1.getDefendObject());
    }

    public String defineNameCharacter() {
        String characterName;
        System.out.println("Veuillez maintenant definir un nom pour votre heros");
        characterName = myObj.nextLine().toLowerCase();
        return characterName;
    }

    public String choiceMenu() {
        String choiceMenu;
        System.out.println(ANSI_BLUE + "Vous pouvez consulter votre personnage en tapant 1, changer de personnage ou de nom en tapant 2 et si tout est ok, ON ATTAQUE ! en tapant 3" + ANSI_RESET);
        choiceMenu = myObj.nextLine().toLowerCase();
        return choiceMenu;
    }

    public String choiceChangement() {
        String choiceChangement;
        System.out.println("1 pour changer de personnage, 2 pour changer de nom");
        choiceChangement = myObj.nextLine().toLowerCase();
        return choiceChangement;
    }

    public void menuSelect(CharacterPlayer perso1, EquipementOffensif attack1, EquipementDefensif defend1) {
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

    public void showStats(CharacterPlayer perso) {
        System.out.println(perso);
        System.out.println(perso.getAttackObject());
        System.out.println(perso.getDefendObject());
    }

    public void changeName(CharacterPlayer perso) {
        String characterName = defineNameCharacter();
        try {
            Statement st = dbConnection.connectionDB();
            st.executeUpdate("UPDATE Hero SET `Nom`='" + characterName + "'");
//            "UPDATE EMPLOYE SET ID = '"+tfIDemploye.getText()+"',nom='"+tfnomemploye.getText()+"', prenom='"+tfprenomemploye.getText()+"', " +
//                    "salaire='"+tfsalaireemploye.getText()+"', age='"+tfageemploye.getText()+"' where ID = "ID de l'enregistrement à modifier"
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        perso.setName(characterName);
//        System.out.println("Votre personnage s'appelle désormais : " + characterName);

//        System.out.println(perso);
    }

    public void printEnd() {
        System.out.println("Partie terminee ! ");
        System.out.println("Tapez 1 pour recommencer une partie, tapez 2 pour quitter le jeu et revenir dans le menu");
    }

    public void printDiceResult(int DiceResult, int new_position, ArrayList<Case> plateau) {
        System.out.println("Le resultat de votre lancer de des est : " + DiceResult + ". Votre nouvelle position est : " + new_position + "/" + plateau.toArray().length);
    }

    public void scanLetThrowDices() {
        Scanner turnplay = new Scanner(System.in);
        System.out.println(ANSI_RED + "Veuillez lancer les des (cliquez sur enter)" + ANSI_RESET);
        turnplay.nextLine();
    }

    public int scanChoiceEnd() {
        return myObj.nextInt();
    }

    public void startPhrase() {
        System.out.println("C'est parti ! Votre objectif sera d'atteindre la case 64. Bonne chance !");

    }

    @Override
    public void yourLife(int life) {
        System.out.println("Votre niveau de vie augmente !");
        System.out.println("Votre vie est desormais de : " + life);
    }

    @Override
    public void displayYouCantTakeThisPotion() {
        System.out.println("Votre vie est deja au maximum");
    }

    public void displayYouAreEquiped(String type, String weaponName) {
        System.out.println(weaponName);
        System.out.println(type);
        System.out.println("Vous etes deja equipe de cette arme");
    }

    @Override
    public void displayYouCantTakeThisWeapon() {
        System.out.println("Vous ne pouvez pas equiper cette arme car elle ne fait pas parti de votre categorie");
    }

    @Override
    public void displayYouAreAlreadyEquiped(String weaponName, String typeYouAreEquiped) {
        System.out.println(weaponName);
        System.out.println(typeYouAreEquiped);
        System.out.println("Vous etes deja equipe de cette arme");
    }
}


