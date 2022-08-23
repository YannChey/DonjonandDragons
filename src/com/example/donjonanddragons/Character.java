package com.example.donjonanddragons;

public class Character {
    private int power;
    private int life;
    private String name;
    private EquipementOffensif attackObject;
    private EquipementDefensif defendObject;

    public Character(){

    }
        public Character(String name){
//            this.attackObject = new EquipementOffensif(name);
//            this.defendObject = new EquipementDefensif(name);
//            if (name.equals("warrior")){
//                this.name = "Warrior";
//                this.life = 10;
//                this.power = 10;
//            }
//            else if(name.equals("magician")){
//                this.name = "Magician";
//                this.life = 6;
//                this.power = 15;
//            }
        }

        public String toString(){
            return "Vous avez choisi le personnage : " + name + ". Votre arme sera donc " + attackObject.getName() + ". Et votre defense sera " + defendObject.getName() + ". Votre vie est de: " + life + ". Et votre puissance est de: " + power;
        }


        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }

        public int getPower(){
            return power;
        }

        public void setPower(int power){
            this.power=power;
        }

        public int getLife(){
            return life;
        }

        public void setLife(int life){
            this.life=life;
        }

        public EquipementOffensif getAttackObject(){
            return this.attackObject;
        }

        public EquipementDefensif getDefendObject(){
            return this.defendObject;
        }

    public void setAttackObject(EquipementOffensif attack){
        this.attackObject = attack;
    }

    public void setDefendObject(EquipementDefensif defend){
        this.defendObject = defend;
    }

}
