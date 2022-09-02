package com.example.donjonanddragons.personnages;

import com.example.donjonanddragons.Combattants;
import com.example.donjonanddragons.equipements.armes.defense.EquipementDefensif;
import com.example.donjonanddragons.equipements.armes.attaque.EquipementOffensif;

import java.awt.*;
import java.sql.*;

public abstract class CharacterPlayer implements Combattants {
    private int power;
    private int life;
    private String name;
    int position;
    private EquipementOffensif attackObject;
    private EquipementDefensif defendObject;

    public CharacterPlayer(int power, int life, String name, EquipementOffensif attackObject, EquipementDefensif defendObject) {
        this.power = power;
        this.life = life;
        this.name = name;
        this.attackObject = attackObject;
        this.defendObject = defendObject;
        this.position=1;
    }

    public String toString() {
        return "Vous avez choisi le personnage : " + name  + ". Votre vie est de: " + life + ". Et votre puissance est de: " + power;
    }

    public void displayCharacterFromBDD(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                String BDD = "donjons_and_dragons";
                String url = "jdbc:mysql://localhost:3306/";
                String user = "yannche";
                String passwd = "alerteauxgogols";
                Connection cn=null;
                Statement st=null;
                ResultSet rs=null;

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    cn = DriverManager.getConnection(url, user, passwd);
                    st = cn.createStatement();
                    String sql = "SELECT * FROM donjons_and_dragons";
                    rs = st.executeQuery(sql);
                    while(rs.next()){
                        System.out.println(rs.getString("name"));
                    }
                    System.out.println("Connecter");
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Erreur");
                    System.exit(0);
                }finally{
                    try{
                        assert cn != null;
                        cn.close();
                        assert st != null;
                        st.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean fighter() {
        return true;
    }

    public int diceResult(){
        return (int) (Math.random() * 6) + 1;
    }

    public void updatePosition(int dices){
       this.position = this.position + dices;
    }

    public void updateNegativePosition(int dices){
        this.position = this.position - dices;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
