package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.equipements.armes.attaque.BaseArme;
import com.example.donjonanddragons.equipements.armes.attaque.EquipementOffensif;
import com.example.donjonanddragons.equipements.armes.attaque.FireBall;
import com.example.donjonanddragons.equipements.armes.defense.Bouclier;
import com.example.donjonanddragons.equipements.armes.defense.EquipementDefensif;
import com.example.donjonanddragons.equipements.armes.defense.Philtre;

public class Magician extends CharacterPlayer {
    public Magician(){
        super(15,6,"Magician","Magician", new BaseArme(),new Philtre(),1);
    }

    public Magician(int power, int life, String name, String type, EquipementOffensif equipementOffensif, EquipementDefensif equipementDefensif,int position){
        super(power,life,name,type,equipementOffensif,equipementDefensif,position);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
