package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.Combattants;
import com.example.donjonanddragons.Game;
import com.example.donjonanddragons.inter.checkStay;
import com.example.donjonanddragons.ennemis.Dragon;
import com.example.donjonanddragons.ennemis.Ennemi;
import com.example.donjonanddragons.ennemis.Gobelin;
import com.example.donjonanddragons.ennemis.Sorcier;
import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.util.Scanner;

public class CaseEnnemi extends Case implements checkStay,characterComeBack {
    Ennemi ennemi;
    boolean isNowEmpty;
    boolean didYouComeBack = false;

    public CaseEnnemi(Ennemi ennemiRace) {
        this.ennemi = ennemiRace;
    }

    public Ennemi ennemiRace() {
        Ennemi ennemi;
        int throughDices;
        throughDices = (int) (Math.random() * 24) + 1;
        if (throughDices < 11) {
            ennemi = new Gobelin();
        } else if (throughDices < 21) {
            ennemi = new Sorcier();
        } else {
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
        this.isNowEmpty = false;
        String areYouAttacking;
        System.out.println("C'est l'heure du combat !!!");
        do{
        Scanner attack = new Scanner(System.in);
        System.out.println("Voulez-vous attaquer le mechant ? (1) / Voulez-vous fuir ? (2)");
        areYouAttacking = attack.nextLine();
            if (areYouAttacking.equals("1")) {
                int somme = character.getPower() + character.getAttackObject().getLevel();
                System.out.println("Votre force d'attaque est de : " + somme + " points. Vous lui infligez vos degats !");
                ennemi.setLife(Math.max(ennemi.getLife() - (character.getPower() + character.getAttackObject().getLevel()), 0));
                if (ennemi.getLife() <= 0) {
                    System.out.println("Il reste : " + ennemi.getLife() + " PV a votre ennemi");
                    System.out.println("Bravo vous avez vaincu l'ennemi !");
                    this.isNowEmpty = true;
                } else {
                    System.out.println("Il reste : " + ennemi.getLife() + " PV a votre ennemi");
                    System.out.println("Il n'est pas mort ! L'ennemi riposte");
                    character.setLife(character.getLife() - ennemi.getAttack());
                    if (character.getLife() <= 0) {
                        System.out.println("L'ennemi vous a attaque, il vous reste : " + character.getLife() + " PV");
                        System.out.println("Apparement vous avez vraiment pris une trop grosse pichenette... Vous etes mort !");
                        Game game = new Game();
                        game.end();
                    } else {
                        System.out.println("L'ennemi vous a attaque, il vous reste : " + character.getLife() + " PV");
                        System.out.println("Vous avez encore de la chance d'être en vie...");
                    }
                }
            }
            else{
                System.out.println("Vous fuyez comme un lache et l'ennemi vous regarde avec dedain tout en rigolant");
                this.didYouComeBack = true;
            }
        }while(character.getLife()>0 && this.ennemi.getLife()>0 && !areYouAttacking.equals("2"));

    }

    public Ennemi getEnnemi() {
        return ennemi;
    }

    public boolean isEmptyCase() {
        return this.isNowEmpty;
    }

    @Override
    public boolean characterIsBack() {
        return this.didYouComeBack;
    }

//    @Override
//    public Optional<Object> getContent() {
//        return Optional.of(this.ennemi);
//    }
}
