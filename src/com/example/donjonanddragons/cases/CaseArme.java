package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.equipements.armes.*;
import com.example.donjonanddragons.personnages.CharacterPlayer;
import com.example.donjonanddragons.personnages.Guerrier;
import com.example.donjonanddragons.personnages.Magician;

public class CaseArme extends Case{
    private EquipementOffensif weapon;
    public CaseArme(){
        this.weapon = this.weapon();
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
        System.out.println(this.weapon);
    }

    @Override
    public void interaction(CharacterPlayer character) {
        if (character instanceof Guerrier guerrier){
            if(this.weapon.getType().equals("Arme")&& !this.weapon.getType().equals(guerrier.getAttackObject().getType())){
                guerrier.setAttackObject(this.weapon);
            }else if(this.weapon.getType().equals(guerrier.getAttackObject().getType())){
                System.out.println("Vous etes deja equipe de cette arme");
            }
            else{
                System.out.println("Vous ne pouvez pas equiper cette arme car elle ne fait pas parti de votre categorie");
            }
        }else if (character instanceof Magician magician){
            if(this.weapon.getType().equals("Sort")){
                magician.setAttackObject(this.weapon);
            }else{
                System.out.println("Vous ne pouvez pas equiper cette arme car elle ne fait pas parti de votre categorie");
            }
        }
    }
}
