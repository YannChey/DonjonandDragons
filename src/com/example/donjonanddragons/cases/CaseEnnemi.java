package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.FightInteractions;
import com.example.donjonanddragons.ennemis.Ennemi;
import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.util.ArrayList;

public class CaseEnnemi extends Case implements CharacterComeBack {
    Ennemi ennemi;
    FightInteractions fightInteractions;
    boolean isNowEmpty;
    boolean didYouComeBack = false;

    boolean areYouDead = false;

    public CaseEnnemi(Ennemi ennemiRace, FightInteractions fightInteractions) {
        this.ennemi = ennemiRace;
        this.fightInteractions = fightInteractions;
    }


    @Override
    public void aEvent() {
        this.fightInteractions.isAnEnnemy();
        System.out.println(this.ennemi);
    }

    @Override
    public void interaction(CharacterPlayer character) {
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

    @Override
    public boolean consequences(ArrayList<Case> plateau, int position) {
        if (this.isNowEmpty) {
            plateau.set(position - 1, new CaseVide());
        }
        return areYouDead;
    }

    public boolean turnBack() {
        return didYouComeBack;
    }

    @Override
    public boolean characterIsBack() {
        return this.didYouComeBack;
    }
}
