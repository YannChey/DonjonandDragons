package com.example.donjonanddragons;

import com.example.donjonanddragons.inter.ConnectionDBBInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUse implements ConnectionDBBInterface {
    public Statement connectToDBB(){
        Connection cn = null;
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjons_and_dragons", "yannche", "alerteauxgogols");
            Statement st = cn.createStatement();
            return st;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int takeIdFromBDD(){
        ArrayList<Integer> IdList = new ArrayList<>();
        try {
            Statement st = connectToDBB();
            ResultSet rs = st.executeQuery("SELECT `Id` FROM hero");
            while (rs.next()) {
                IdList.add(Integer.valueOf(rs.getString("Id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int lastIdx = IdList.size() -1;
        return IdList.get(lastIdx);
    }

    public List displayCharactersFromBDD() {
        ArrayList<Integer> IdList = new ArrayList<>();
        try {
            Statement st = connectToDBB();
            ResultSet rs = st.executeQuery("SELECT * FROM hero");
            while (rs.next()) {
                System.out.println("Votre personnage : " + rs.getString("Id") + " de type : " + rs.getString("Type") +
                        " qui a comme nom : " + rs.getString("Nom") + " d'un niveau de vie de : " + rs.getString("NiveauVie")
                        + " et un niveau de force de : " + rs.getString("NiveauForce") + " avec une arme qui est : " + rs.getString("Weapon")
                        + " et une défense qui est : " + rs.getString("Bouclier"));
                IdList.add(Integer.valueOf(rs.getString("Id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return IdList;
    }


}
