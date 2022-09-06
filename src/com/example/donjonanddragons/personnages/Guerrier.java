package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.armes.attaque.BaseArme;
import com.example.donjonanddragons.equipements.armes.attaque.EquipementOffensif;
import com.example.donjonanddragons.equipements.armes.defense.Bouclier;
import com.example.donjonanddragons.equipements.armes.attaque.Epee;
import com.example.donjonanddragons.equipements.armes.defense.EquipementDefensif;

public class Guerrier extends CharacterPlayer {
     public Guerrier(){
         super(10,10,"Warrior","Warrior",new BaseArme(),new Bouclier());
     }
     //TODO IL FAUT REGLER LE PROBLEME DE L'APPEL DE LA METHODE GETCHARACTERFROMDB
     public Guerrier(int power, int life, String name, String type, EquipementOffensif equipementOffensif, EquipementDefensif equipementDefensif){
         super(power,life,name,type,equipementOffensif,equipementDefensif);
     }

    @Override
    public String toString() {
        return super.toString();
    }
}
