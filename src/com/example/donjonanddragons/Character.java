package com.example.donjonanddragons;

public class Character {
    private int power;
    private int life;
    private String name;
    private EquipementOffensif attackObject;
    private EquipementDefensif defendObject;

    public Character() {

    }

    public Character(String name) {
//            this.attackObject = new EquipementOffensif(name);
//            this.defendObject = new EquipementDefensif(name);
//            if (name.equals("warrior")){
//                Guerrier guerrier1 = new Guerrier(name);
//            }
//            else if(name.equals("magician")){
//                Magician magician1 = new Magician(name);
//            }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public EquipementOffensif getAttackObject() {
        return this.attackObject;
    }

    public EquipementDefensif getDefendObject() {
        return this.defendObject;
    }

    public void setAttackObject(EquipementOffensif attack) {
        this.attackObject = attack;
    }

    public void setDefendObject(EquipementDefensif defend) {
        this.defendObject = defend;
    }

}
