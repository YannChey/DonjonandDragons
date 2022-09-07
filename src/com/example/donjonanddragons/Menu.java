package com.example.donjonanddragons;

import com.example.donjonanddragons.cases.Case;
import com.example.donjonanddragons.equipements.armes.attaque.*;
import com.example.donjonanddragons.equipements.armes.defense.Bouclier;
import com.example.donjonanddragons.equipements.armes.defense.EquipementDefensif;
import com.example.donjonanddragons.equipements.armes.defense.Philtre;
import com.example.donjonanddragons.inter.ArmeInteractions;
import com.example.donjonanddragons.inter.CaisseInteractions;
import com.example.donjonanddragons.personnages.CharacterPlayer;
import com.example.donjonanddragons.personnages.Guerrier;
import com.example.donjonanddragons.personnages.Magician;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu implements CaisseInteractions, ArmeInteractions {
    Scanner myObj = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    DBUse dbUse = new DBUse();
    int id;

    public void displayWelcome() {
        System.out.println(ANSI_RED + "Bienvenue dans notre nouveau jeu Donjons et Dragons !" + ANSI_RESET);
    }

    // Menu pour faire un choix entre créer ou charger un character

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
            perso1 = insertCharacterIntoBDD();
            dbUse.takeIdFromBDD();
        } else {
            List idList = dbUse.displayCharactersFromBDD();
            int id = selectCharacterFromBDD(idList);
            perso1 = getCharacterFromDBB(id);
        }
        return perso1;
    }

    // Menu pour choisir le caractère à créer (guerrier ou magicien)

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

    // Instancier le type de character (guerrier ou magicien)

    public CharacterPlayer instanceCharacterType(String name) {
        CharacterPlayer perso1;
        if (name.equals("warrior")) {
            perso1 = new Guerrier();
        } else {
            perso1 = new Magician();
        }
        return perso1;
    }

    // Inserer un character dans la BDD

    public CharacterPlayer insertCharacterIntoBDD() {
        String name = getCharacterType();
        CharacterPlayer characterPlayer = instanceCharacterType(name);
        try {
            Statement st = dbUse.connectToDBB();
            st.executeUpdate("INSERT INTO `hero`(`Type`, `Nom`, `NiveauVie`, `NiveauForce`, `Weapon`, `Bouclier`) " +
                    "VALUES ('" + name + "','" + characterPlayer.getName() + "','" + characterPlayer.getLife() + "','"
                    + characterPlayer.getPower() + "','" + characterPlayer.getAttackObject().getName() + "','" + characterPlayer.getDefendObject().getName() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characterPlayer;
    }

    // Selectionner un character dans la BDD

    public int selectCharacterFromBDD(List idList) {
        System.out.println("Pour commencer, sélectionner le personnage que vous souhaitez jouer (rentrez le numero de votre sauvegarde)");
        this.id = Integer.parseInt(myObj.nextLine());
        while (!idList.contains(this.id)) {
            System.out.println(ANSI_RED + "Vous devez choisir une sauvegarde existante ! " + ANSI_RESET);
            try {
                this.id = Integer.parseInt(myObj.nextLine());
            } catch (InputMismatchException e) {
            }
        }
        return this.id;
    }

    //Instancier un character déjà dans la BDD

    public EquipementOffensif equipementOffensif(int id) {
        EquipementOffensif equipementOffensif1 = null;
        try {
            Statement st = dbUse.connectToDBB();
            ResultSet rs = st.executeQuery("SELECT * FROM hero WHERE ID = " + id + "");
            while (rs.next()) {
                if (rs.getString("Weapon").equals("une epee")) {
                    equipementOffensif1 = new Epee();
                } else if (rs.getString("Weapon").equals("une massue")) {
                    equipementOffensif1 = new Massue();
                } else if (rs.getString("Weapon").equals("un eclair")) {
                    equipementOffensif1 = new Eclair();
                } else if (rs.getString("Weapon").equals("une boule de feu")) {
                    equipementOffensif1 = new FireBall();
                } else {
                    equipementOffensif1 = new BaseArme();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipementOffensif1;
    }

    public EquipementDefensif equipementDefensif(int id) {
        EquipementDefensif equipementDefensif = null;
        try {
            Statement st = dbUse.connectToDBB();
            ResultSet rs = st.executeQuery("SELECT * FROM hero WHERE ID = " + id + "");
            while (rs.next()) {
                if (rs.getString("Bouclier").equals("Bouclier")) {
                    equipementDefensif = new Bouclier();
                } else {
                    equipementDefensif = new Philtre();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipementDefensif;
    }

    public CharacterPlayer getCharacterFromDBB(int id) {
        CharacterPlayer characterPlayer = null;
        try {
            Statement st = dbUse.connectToDBB();
            ResultSet rs = st.executeQuery("SELECT * FROM hero WHERE Id = " + id + "");
            while (rs.next()) {
                System.out.println("Votre personnage : " + rs.getString("Id") + " de type : " + rs.getString("Type") +
                        " qui a comme nom : " + rs.getString("Nom") + " d'un niveau de vie de : " + rs.getString("NiveauVie")
                        + " et un niveau de force de : " + rs.getString("NiveauForce") + " avec une arme qui est : " + rs.getString("Weapon")
                        + " et une défense qui est : " + rs.getString("Bouclier"));
                if (rs.getString("Type").equals("warrior")) {
                    characterPlayer = new Guerrier(rs.getInt("NiveauForce"), rs.getInt("NiveauVie"), rs.getString("Nom"),
                            rs.getString("Type"), equipementOffensif(id), equipementDefensif(id));
                } else {
                    characterPlayer = new Magician(rs.getInt("NiveauForce"), rs.getInt("NiveauVie"), rs.getString("Nom"),
                            rs.getString("Type"), equipementOffensif(id), equipementDefensif(id));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characterPlayer;
    }

    // Supprimer un character de la BDD si le personnage est mort

    public void removeCharacterFromBDD(int id){
        try {
            Statement st = dbUse.connectToDBB();
            st.executeUpdate("DELETE FROM `hero` WHERE Id = " + id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getMenu(CharacterPlayer perso1, String choice) {
        if (choice.equals("1")) {
            changeName(perso1, dbUse.takeIdFromBDD());
        }
        showStats(perso1);
        menuSelect(perso1, perso1.getAttackObject(), perso1.getDefendObject());
    }

    // Methode Menu qui renvoi sur le changement de nom du personnage ou l'affichage du menu principal ou attaquer

    public String choiceMenu() {
        String choiceMenu;
        System.out.println(ANSI_BLUE + "Vous pouvez consulter votre personnage en tapant 1, changer de personnage ou de nom en tapant 2 et si tout est ok, ON ATTAQUE ! en tapant 3. Enfin vous pouvez quitter le jeu en tapant 4" + ANSI_RESET);
        choiceMenu = myObj.nextLine().toLowerCase();
        return choiceMenu;
    }

    public String choiceChangment() {
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
                    choiceChangment();
                    if (choiceChangment().equals("1")) {
                        String name = getCharacterType();
                        perso1 = instanceCharacterType(name);
                        attack1 = perso1.getAttackObject();
                        defend1 = perso1.getDefendObject();
                    } else if (choiceChangment().equals("2")) {
                        changeName(perso1, dbUse.takeIdFromBDD());
                    }
                    break;
                case "3":
                    System.out.println("Bienvenue dans D&D !");
                    break;
                case "4":
                    System.exit(0);
            }
        } while (!choixUtilisateur.equals("3"));
    }

    // Affichage des caractéristiques du personnage

    public void showStats(CharacterPlayer perso) {
        System.out.println(perso);
        System.out.println(perso.getAttackObject());
        System.out.println(perso.getDefendObject());
    }

    // Méthode pour changer de nom
    public void changeName(CharacterPlayer perso, int id) {
        String characterName;
        System.out.println("Veuillez maintenant definir un nom pour votre heros");
        characterName = myObj.nextLine();
        try {
            Statement st = dbUse.connectToDBB();
            st.executeUpdate("UPDATE Hero SET `Nom` = '" + characterName + "' WHERE ID = " + id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        perso.setName(characterName);
        System.out.println("Votre personnage s'appelle désormais : " + characterName);
    }

    // Méthode qui affiche la fin de la partie et demande un choix à l'utilisateur
    public void printEnd() {
        System.out.println("Partie terminee ! ");
        System.out.println("Tapez 1 pour recommencer une partie, tapez 2 pour quitter le jeu et revenir dans le menu, tapez 3 pour quitter le jeu");
    }

    public void printDiceResult(int DiceResult, int new_position, ArrayList<Case> plateau) {
        System.out.println("Le resultat de votre lancer de des est : " + DiceResult + ". Votre nouvelle position est : " + new_position + "/" + plateau.toArray().length);
    }

    public String scanLetThrowDices() {
        Scanner turnplay = new Scanner(System.in);
        System.out.println(ANSI_RED + "Veuillez lancer les des (cliquez sur enter), vous pouvez aussi cliquer sur 1 pour revenir au menu ou sur 2 pour quitter le jeu. (ATTENTION ! L'avancée dans votre partie ne sera pas sauvegardée !)" + ANSI_RESET);
        return turnplay.nextLine();
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
        System.out.println("Vous etes deja equipe de cette arme ! ");
    }

    @Override
    public String displayChooseIfYouWantThisWeapon() {
        String choice;
        System.out.println("Voulez-vous vous equiper de cette arme ? oui/non");
        choice = myObj.nextLine().toLowerCase();
        while (!choice.equals("oui") && !choice.equals("non")) {
            System.out.println(ANSI_RED + "Vous devez choisir oui ou non ! " + ANSI_RESET);
            try {
                choice = myObj.nextLine().toLowerCase();
            } catch (InputMismatchException e) {
            }
        }
        return choice;
    }

    @Override
    public void displayDontTakeThisWeapon() {
        System.out.println("Ok, tu veux pas de mon arme ? Retourne combattre !");
    }

    public String chooseRestartOrNot() {
        String choice;
        System.out.println("Voulez-vous recommencer une partie ou quitter le jeu? oui/non");
        choice = myObj.nextLine().toLowerCase();
        while (!choice.equals("oui") && !choice.equals("non")) {
            System.out.println(ANSI_RED + "Vous devez choisir oui ou non ! " + ANSI_RESET);
            try {
                choice = myObj.nextLine().toLowerCase();
            } catch (InputMismatchException e) {
            }
        }
        return choice;
    }

    public void youLost(){
        System.out.println("Vous avez perdu...");
    }

    public void youFear(int dices, CharacterPlayer perso1){
        System.out.println("Vous avez fuit et reculez de " + dices + " vous etes a la case " + perso1.getPosition());
    }
}


