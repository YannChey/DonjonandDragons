package com.example.donjonanddragons.equipements;

public abstract class EquipementDefensif {
    private String type;
    private int level;
    private String name;

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


