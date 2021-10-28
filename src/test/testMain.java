/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Personne;
import entities.Suivi;
import java.util.ArrayList;
import service.userservice;
import utils.Data_B;
import java.util.Date;
import java.util.List;
import service.suiviservice;
/**
 *
 * @author cyrine belhssan
 */
public class testMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            Data_B.getInstance();
        
//Suivi p = new Suivi( 1.77f, 44.5f, 1.90f,"dddiuyt...", "pasta", "pizza", "mercii");
     suiviservice ps = new suiviservice();
     userservice us = new userservice();
  //   us.FindUser("admin@gmail.com", "admin");
     int d = userservice.usercon.getId();
        System.err.println(ps.findParId(3).toString());

    }
    
}
