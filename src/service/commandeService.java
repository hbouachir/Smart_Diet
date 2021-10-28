/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entites.commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import utils.MyDB;

/**
 *
 * @author HAMZA
 */
public class commandeService implements Service<commande>{
     Connection Connection;
    public Button findAll;
    
   
    public commandeService(){
        
            Connection =MyDB.getInstance().getConnection();
         
    }
    
    @Override
    public void insert(commande object) {
        try {
            String req;
//            req ="INSERT INTO `commande`(`idClient`,`montantPanier`) VALUES ('"+object.getIdClient()+"','"+object.getMontantPanier()+"')";
//            Statement st= Connection.createStatement();
//            
//            st.executeUpdate(req);
            
             req ="INSERT INTO `commande`(`idClient`,`montantPanier`) VALUES (?,?)";
           
            PreparedStatement pt= Connection.prepareStatement(req);
            pt.setInt(1, object.getIdClient());
            pt.setInt(2, object.getMontantPanier());
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(commandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(commande object,int id) {
try {
            String req;
            req ="UPDATE `commande` SET `montantPanier`="+object.getMontantPanier()+" WHERE `idCommande`="+id+"";
            Statement st= Connection.createStatement();
            st.executeUpdate(req);
           
           
        } catch (SQLException ex) {
            Logger.getLogger(commandeService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void delete(int id) {
try {
            String req;
            req ="DELETE FROM `commande` WHERE `idCommande`='"+id+"'";
            Statement st= Connection.createStatement();
            st.executeUpdate(req);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(commandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<commande>  findAll() {
            ObservableList<commande> listC=FXCollections.observableArrayList();
            String req;
            req ="SELECT * FROM `commande` ";
         try {
             Statement st= Connection.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next()){
                 commande c = new commande();
                 c.setIdCommande(rs.getInt(1));
                 c.setIdClient(rs.getInt(2));
                 c.setMontantPanier(rs.getInt(3));
                 listC.add(c);
             }
         } catch (SQLException ex) {
             Logger.getLogger(commandeService.class.getName()).log(Level.SEVERE, null, ex);
         }
            
            
            return listC;
    }

    @Override
    public ObservableList<commande> findByUserId(int id) {
ObservableList<commande> listC=FXCollections.observableArrayList();
            String req;
            req ="SELECT * FROM `commande` WHERE idClient="+id+"";
         try {
             Statement st= Connection.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next()){
                 commande c = new commande();
                 c.setIdCommande(rs.getInt(1));
                 c.setIdClient(rs.getInt(2));
                 c.setMontantPanier(rs.getInt(3));
                 listC.add(c);
             }
         } catch (SQLException ex) {
             Logger.getLogger(commandeService.class.getName()).log(Level.SEVERE, null, ex);
         }
            
            
            return listC;    }

 


   
    
}
