package com.example.donjonanddragons;

public class EquipementDefensif {
    private String type;
    private int level;
    private String name;

    public EquipementDefensif(){

    }
    public EquipementDefensif(String name){
        if (name.equals("warrior")) {
            this.type = "Defense";
            this.level = 8;
            this.name = "un bouclier";
        }
        else if(name.equals("magician")) {
            this.type = "Defense";
            this.level = 8;
            this.name = "un philtre";
        }
    }

    public String toString(){
        return "Votre " + type + " sera " + name + " d'un niveau de : " + level;
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


