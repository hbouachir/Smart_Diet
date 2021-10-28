/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nour
 */
public class MyDB {
    String url = "jdbc:mysql://localhost:3308/smartdiet?sessionVariables=FOREIGN_KEY_CHECKS=0"; //ajouter dans la base les FK
    String user = "root";
    String pwd = "";
   Connection connection;
    static MyDB instance;
    
    private MyDB(){
        try {
            connection = DriverManager.getConnection(url, user, pwd);
       System.out.println("connexion etablié");
        } catch (SQLException ex) {
          System.out.println("Problème de cnx");
          System.out.println(ex.getMessage());
        }
    }
    //methode qui va retourne l'objet instance
     public Connection getConnection(){
        return connection;
    }
   public static MyDB getInstance(){
       if (instance == null){
           instance = new MyDB();
       }
       return instance;
   } 
 
}
