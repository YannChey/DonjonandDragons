package com.example.donjonanddragons.equipements.armes.attaque;

public abstract class EquipementOffensif {
    private String type;
    private int level;
    private String name;

    protected EquipementOffensif(String type, int level, String name) {
        this.type = type;
        this.level = level;
        this.name = name;
    }

    public String toString(){
        return "Votre " + type + " est " + name + " d'un niveau de : " + level;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public int getLevel(){
        return level;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setLevel(Integer level){
        this.level = level;
    }

}
