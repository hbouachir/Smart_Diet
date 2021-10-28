/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.rendezvous;
import services.rendezvousService;

/**
 *
 * @author Nour
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       rendezvous r = new rendezvous("01/01/2021","11");
       rendezvousService RS = new rendezvousService();
       RS.ajouterRendezvous(r); 
        System.out.println(RS.afficherRdv().toString());
       //RS.afficherRdv();
       RS.Delete("dateRdv");
       RS.UpdateRendezvous(r);
       }
    }
   
