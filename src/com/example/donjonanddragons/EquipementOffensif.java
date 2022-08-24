package com.example.donjonanddragons;

public class EquipementOffensif {
    private String type;
    private int level;
    private String name;

    public EquipementOffensif(){

    }
    public EquipementOffensif(String name){
        if (name.equals("warrior")) {
            this.type = "Arme";
            this.level = 5;
            this.name = "une epee";
        }
        else if(name.equals("magician")) {
            this.type = "Sort";
            this.level = 7;
            this.name = "une boule de feu";
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
