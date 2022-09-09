package com.example.donjonanddragons;

import com.example.donjonanddragons.inter.ConnectionDBBInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUse implements ConnectionDBBInterface {

    public Connection connectToDBB(){
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjons_and_dragons", "yannche", "alerteauxgogols");
            return cn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int takeIdFromBDD(){
        ArrayList<Integer> IdList = new ArrayList<>();
        try {
            Statement st = connectToDBB().createStatement();
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
            Statement st = connectToDBB().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM hero");
            while (rs.next()) {
                System.out.println("Votre personnage : " + rs.getString("Id") + " de type : " + rs.getString("Type") +
                        " qui a comme nom : " + rs.getString("Nom") + " d'un niveau de vie de : " + rs.getString("NiveauVie")
                        + " et un niveau de force de : " + rs.getString("NiveauForce") + " avec une arme qui est : " + rs.getString("Weapon")
                        + " et une défense qui est : " + rs.getString("Bouclier") + " a la position : " + rs.getInt("Position"));
                IdList.add(Integer.valueOf(rs.getString("Id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return IdList;
    }


}
