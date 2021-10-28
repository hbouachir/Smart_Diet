/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entites.lignecommande;
import java.sql.SQLException;
import service.lignecommandeService;
import utils.MyDB;

/**
 *
 * @author HAMZA
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
         MyDB.getInstance();
     
        lignecommandeService cs=new lignecommandeService() ;
        int a=10*cs.PrixPro(1);
        System.out.println(a);
             lignecommande c=new lignecommande(4,1,10,a);
        cs.insert(c);
    }
    
}
