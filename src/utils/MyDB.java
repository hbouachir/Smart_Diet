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
 * @author HAMZA
 */
public class MyDB {
    
    private final String url="jdbc:mysql://localhost:3306/smart_diet_db?sessionVariables=FOREIGN_KEY_CHECKS=0";
    private final String user="root";
    private final String pwd="";
    private Connection Connection;
    private static MyDB instance;

    private MyDB() {
        try {
            Connection = DriverManager.getConnection(url, user, pwd);
            System.out.println("connexion établie");
                    } catch (SQLException ex) {
            System.out.println("erruer de connexion à la base de donnée");        }
    }
    
    public static MyDB getInstance(){
        if (instance==null){
            instance = new MyDB();
                    }
        return instance;
        
    }

    public Connection getConnection() {
        return Connection;
    }
    
    
}