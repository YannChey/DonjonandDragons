package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.inter.ConnectionDBBInterface;
import com.example.donjonanddragons.inter.FightInteractions;
import com.example.donjonanddragons.ennemis.Ennemi;
import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Déclaration des attributs de ma classe CaseEnnemi
 */
public class CaseEnnemi extends Case {
    Ennemi ennemi;
    FightInteractions fightInteractions;
    boolean isNowEmpty;
    boolean didYouComeBack = false;

    boolean areYouDead = false;

    ConnectionDBBInterface connectionDBBInterface;

    /**
     * Constructeur de ma class CaseEnnemi
     * @param ennemiRace
     * @param fightInteractions
     */
    public CaseEnnemi(Ennemi ennemiRace, FightInteractions fightInteractions, ConnectionDBBInterface connectionDBBInterface) {
        this.ennemi = ennemiRace;
        this.fightInteractions = fightInteractions;
        this.connectionDBBInterface = connectionDBBInterface;
    }


    /**
     * Méthode appelée dans ma classe Game, qui permet d'afficher si un ennemi est présent sur cette case, et quel type d'ennemi.
     * Le this.ennemi permet de déterminer quel enfant est appelé dans cette case.
     */
    @Override
    public void aEvent() {
        this.fightInteractions.isAnEnnemy();
        System.out.println(this.ennemi);
    }

    /**
     * Méthode principale de ma classe qui permet de réaliser l'action de combat.
     * Celle-ci intéragit avec une interface FightInteractions qui appelle le menuFight où tout mon display sera présent.
     * Enfin elle intéragit également avec la classe Ennemi et notre méthode va influencer sur la vie de notre personnage et sur celle des ennemis.
     * La déclaration de la variable areYouAttacking permet de renvoyer l'information de si le personnage veut attaquer ou non.
     * Par la suite, on joue avec les getters et les setters pour modifier la vie du joueur et de l'ennemi.
     * Enfin on utilise trois attributs déclarés au préalable : isNowEmpty pour voir si l'ennemi a été battu et donc pour vider la case,
     * areYouDead pour vérifier si le personnage n'est pas mort, et didYouComeBack pour vérifier si le personnage n'a pas fuit le combat.
     * @param character
     */
    @Override
    public void interaction(CharacterPlayer character) {

    }

    @Override
    public void interaction(CharacterPlayer character, int id) {
        boolean areYouAttacking;
        do {
            areYouAttacking = this.fightInteractions.userWantToFlee();
            this.isNowEmpty = false;
            if (areYouAttacking) {
                int somme = character.getPower() + character.getAttackObject().getLevel();
                this.fightInteractions.damageAttack(somme);
                ennemi.setLife(Math.max(ennemi.getLife() - (character.getPower() + character.getAttackObject().getLevel()), 0));
                if (ennemi.getLife() <= 0) {
                    this.fightInteractions.displayEnnemyKill(ennemi.getLife());
                    this.isNowEmpty = true;
                } else {
                    this.fightInteractions.displayEnnemySurvive(ennemi.getLife());
                    character.setLife(character.getLife() - ennemi.getAttack());
                    updateLifeInBDD(character, id);
                    if (character.getLife() <= 0) {
                        this.fightInteractions.displayYouAreDead(character.getLife());
                        areYouDead = true;
                    } else {
                        this.fightInteractions.displayYouAreAlive(character.getLife());
                    }
                }
            } else {
                this.fightInteractions.flee();
                this.didYouComeBack = true;
            }
        } while (character.getLife() > 0 && this.ennemi.getLife() > 0 && areYouAttacking);
    }

    /**
     * Méthode qui permet de renvoyer à mon game la modification de mon plateau de jeu si l'ennemi est mort et de retourner si notre personnage est mort.
     * La classe game appelle cette méthode et applique les conséquences suivant le retour du booléan.
     * @param plateau
     * @param position
     * @return areYouDead
     */
    @Override
    public boolean consequences(ArrayList<Case> plateau, int position) {
        if (this.isNowEmpty) {
            plateau.set(position - 1, new CaseVide());
        }
        return areYouDead;
    }

    public void updateLifeInBDD(CharacterPlayer characterPlayer, int id){
        try {
            Statement st= connectionDBBInterface.connectToDBB();
            st.executeUpdate("UPDATE Hero SET `NiveauVie` = '"+ characterPlayer.getLife() +"' WHERE ID = "+ id +"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return notre boolean qui informe si notre personnage a fuit le combat
     */
    public boolean turnBack() {
        return didYouComeBack;
    }

}
