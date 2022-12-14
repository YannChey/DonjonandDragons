package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.inter.ArmeInteractions;
import com.example.donjonanddragons.inter.ConnectionDBBInterface;
import com.example.donjonanddragons.equipements.armes.attaque.*;
import com.example.donjonanddragons.personnages.CharacterPlayer;
import com.example.donjonanddragons.personnages.Guerrier;
import com.example.donjonanddragons.personnages.Magician;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CaseArme extends Case {
    private EquipementOffensif weapon;
    boolean isNowEmpty;
    private final ConnectionDBBInterface connectionDBBInterface;
    private final ArmeInteractions armeInteractions;
    public CaseArme(EquipementOffensif arme, ArmeInteractions armeInteractions, ConnectionDBBInterface connectionDBBInterface){
//        this.weapon = this.weapon();
        this.weapon = arme;
        this.armeInteractions = armeInteractions;
        this.connectionDBBInterface = connectionDBBInterface;
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
    public void interaction(CharacterPlayer character, int id) {
        this.isNowEmpty = false;
        if (character instanceof Guerrier guerrier){
            if(this.weapon.getType().equals("Arme")&& !this.weapon.getName().equals(guerrier.getAttackObject().getName())){
                String choice = armeInteractions.displayChooseIfYouWantThisWeapon();
                if(choice.equals("oui")){
                    setWeaponOnCharacter(guerrier,id);
                }else{
                    armeInteractions.displayDontTakeThisWeapon();
                }
            }else if(this.weapon.getName().equals(guerrier.getAttackObject().getName())){
                displayWeaponAlreadyWeared(guerrier);
            }
            else{
                armeInteractions.displayYouCantTakeThisWeapon();
            }
        }else if (character instanceof Magician magician){
            if(this.weapon.getType().equals("Sort")&& !this.weapon.getName().equals(magician.getAttackObject().getName())){
                String choice = armeInteractions.displayChooseIfYouWantThisWeapon();
                if(choice.equals("oui")){
                    setWeaponOnCharacter(magician,id);
                }else{
                    armeInteractions.displayDontTakeThisWeapon();
                }
            }else if(this.weapon.getName().equals(magician.getAttackObject().getName())){
                displayWeaponAlreadyWeared(magician);
            }
            else{
                armeInteractions.displayYouCantTakeThisWeapon();
            }

            //TODO FINIR DE REFACTO CASEARME
        }
    }

    @Override
    public boolean consequences(ArrayList<Case> plateau, int position) {
        if(this.isNowEmpty){
            plateau.set(position - 1, new CaseVide());
        }
        return false;
    }

    public void updateWeaponInBDD(int id){
        try {
            PreparedStatement stmt = connectionDBBInterface.connectToDBB().prepareStatement("UPDATE Hero SET `Weapon` = ? WHERE Id = ?");
            stmt.setString(1,this.weapon.getName());
            stmt.setInt(2,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Vous portez d??sormais : " + this.weapon.getName());
    }

    public void setWeaponOnCharacter(CharacterPlayer characterPlayer, int id){
        characterPlayer.setAttackObject(this.weapon);
        updateWeaponInBDD(id);
        this.isNowEmpty = true;
    }
    public void displayWeaponAlreadyWeared(CharacterPlayer characterPlayer){
        armeInteractions.displayYouAreAlreadyEquiped(this.weapon.getName(),characterPlayer.getAttackObject().getType());
    }


}
