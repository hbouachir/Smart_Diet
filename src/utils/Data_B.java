/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cyrine belhssan
 */
//zeroDateTimeBehavior=convertToNull
public class Data_B {
    String url="jdbc:mysql://localhost:3308/smart_diet_app?sessionVariables=FOREIGN_KEY_CHECKS=0";
     String user="root"; 
        String pwd="";
        Connection conn;
       static Data_B instance;
       
       private Data_B(){
        try {
            conn =DriverManager.getConnection(url, user, pwd);
            System.out.println("connexion établi");
           
           
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
       }

    public static Data_B getInstance() {
if(instance==null){
    instance = new Data_B();
}
return instance;
    }
    public Connection getConnection(){
        //JOptionPane.showMessageDialog(null, "connecté...");
        return conn;
    } 
}
