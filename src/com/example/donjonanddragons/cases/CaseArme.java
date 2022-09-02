package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.equipements.armes.attaque.*;
import com.example.donjonanddragons.personnages.CharacterPlayer;
import com.example.donjonanddragons.personnages.Guerrier;
import com.example.donjonanddragons.personnages.Magician;

import java.util.ArrayList;

public class CaseArme extends Case {
    private EquipementOffensif weapon;
    boolean isNowEmpty;
    public CaseArme(EquipementOffensif arme){
//        this.weapon = this.weapon();
        this.weapon = arme;
    }

    public EquipementOffensif weapon(){
        int throughDices;
        throughDices = (int) (Math.random() * 16) + 1;
        if (throughDices <= 5){
            this.weapon = new Massue();
        }else if(throughDices <= 9){

            this.weapon = new Epee();
        }else if(throughDices <= 14){
            this.weapon = new Eclair();
        }else{
            this.weapon = new FireBall();
        }
        return weapon;
    }

    @Override
    public void aEvent() {
        System.out.println("Vous avez trouve une arme ! Celle-ci est : " + weapon.getName());
//        System.out.println(this.weapon);
    }

    @Override
    public void interaction(CharacterPlayer character) {
        this.isNowEmpty = false;
        if (character instanceof Guerrier guerrier){
            if(this.weapon.getType().equals("Arme")&& !this.weapon.getName().equals(guerrier.getAttackObject().getName())){
                guerrier.setAttackObject(this.weapon);
                this.isNowEmpty = true;
            }else if(this.weapon.getName().equals(guerrier.getAttackObject().getName())){
                System.out.println(this.weapon.getName());
                System.out.println(guerrier.getAttackObject().getType());
                System.out.println("Vous etes deja equipe de cette arme");
            }
            else{
                System.out.println("Vous ne pouvez pas equiper cette arme car elle ne fait pas parti de votre categorie");
            }
        }else if (character instanceof Magician magician){
            if(this.weapon.getType().equals("Sort")&& !this.weapon.getName().equals(magician.getAttackObject().getName())){
                magician.setAttackObject(this.weapon);
                this.isNowEmpty = true;
            }else if(this.weapon.getName().equals(magician.getAttackObject().getName())){
                System.out.println(this.weapon.getName());
                System.out.println(magician.getAttackObject().getType());
                System.out.println("Vous etes deja equipe de cette arme");
            }
            else{
                System.out.println("Vous ne pouvez pas equiper cette arme car elle ne fait pas parti de votre categorie");
            }
        }
    }

    @Override
    public boolean consequences(ArrayList<Case> plateau, int position) {
        if(this.isNowEmpty){
            plateau.set(position - 1, new CaseVide());
        }
        return false;
    }
}
