package com.example.donjonanddragons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    public Statement connectionDB(){
        Connection cn = null;
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjons_and_dragons", "yannche", "alerteauxgogols");
            Statement st = cn.createStatement();
            return st;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
