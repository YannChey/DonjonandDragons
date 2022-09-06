package com.example.donjonanddragons.cases;

import com.example.donjonanddragons.CaisseInteractions;
import com.example.donjonanddragons.ConnectionDBBInterface;
import com.example.donjonanddragons.equipements.potions.Potion;
import com.example.donjonanddragons.personnages.CharacterPlayer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CaseCaisse extends Case {
    private final Potion potion;

    private final CaisseInteractions caisseInteractions;
    private final ConnectionDBBInterface connectionDBBInterface;
    boolean isNowEmpty;
    public CaseCaisse(Potion potion, CaisseInteractions caisseInteractions, ConnectionDBBInterface connectionDBBInterface){
//        this.potion = this.givePotion();
        this.potion = potion;
        this.caisseInteractions = caisseInteractions;
        this.connectionDBBInterface = connectionDBBInterface;
    }

    @Override
    public void aEvent() {
        System.out.println(this.potion);

    }

    @Override
    public void interaction(CharacterPlayer character) {

    }

    @Override
    public void interaction(CharacterPlayer character, int id) {
        this.isNowEmpty = false;
        if (character.getLife() < 15){
            character.setLife(Math.min(character.getLife() + this.potion.getLevel(), 15));
            updateLifeInBDD(character,id);
            this.caisseInteractions.yourLife(character.getLife());
            this.isNowEmpty = true;
        }else{
            this.caisseInteractions.displayYouCantTakeThisPotion();
        }
    }

    @Override
    public boolean consequences(ArrayList<Case> plateau, int position) {
        if(this.isNowEmpty){
            plateau.set(position - 1, new CaseVide());
        }
        return false;
    }

    public void updateLifeInBDD(CharacterPlayer characterPlayer, int id){
        try {
            Statement st= connectionDBBInterface.connectToDBB();
            st.executeUpdate("UPDATE Hero SET `NiveauVie` = '"+ characterPlayer.getLife() +"' WHERE ID = "+ id +"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Potion getPotion() {
            return potion;
    }
}
