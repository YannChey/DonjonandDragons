package com.example.donjonanddragons;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                String BDD = "votreBaseDeDonnée";
//                String url = "jdbc:mysql://localhost:3306/" + BDD;
//                String user = "root";
//                String passwd = "root";
//
//                try {
//                    Class.forName("com.mysql.jdbc.Driver");
//                    Connection conn = DriverManager.getConnection(url, user, passwd);
//                    System.out.println("Connecter");
//                } catch (Exception e){
//                    e.printStackTrace();
//                    System.out.println("Erreur");
//                    System.exit(0);
//                }
//            }
//        });
        Game game = new Game();
        game.runGame();
    }
}
