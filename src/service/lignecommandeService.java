/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entites.commande;
import entites.lignecommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author HAMZA
 */
public class lignecommandeService implements Service<lignecommande>{
    Connection Connection;
    
   
    public lignecommandeService(){
        
            Connection =MyDB.getInstance().getConnection();
         
    }
    
    @Override
    public void insert(lignecommande object) {
 try {
            String req;

            
             req ="INSERT INTO `lignecommande`(`idCommande`,`codeProduit`,`quantite`,`prix`) VALUES (?,?,?,?)";
           
            PreparedStatement pt= Connection.prepareStatement(req);
            pt.setInt(1, object.getIdCommande());
            pt.setInt(2, object.getCodeProduit());
            pt.setInt(3, object.getQuantite());
//            pt.setFloat(4, object.getPrix());
            pt.setFloat(4,object.getPrix() );
//object.getQuantite()*PrixPro(object.getCodeProduit())

            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(commandeService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void update(lignecommande object, int id) {
try {
            String req;
            req ="UPDATE `lignecommande` SET `quantite`="+object.getQuantite()+",`prix`="+object.getPrix()+" WHERE `idLigne`="+id+"";
            Statement st= Connection.createStatement();
            st.executeUpdate(req);
           
           
        } catch (SQLException ex) {
            Logger.getLogger(commandeService.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    @Override
    public void delete(int id) {
try {
            String req;
            req ="DELETE FROM `lignecommande` WHERE `idLigne`='"+id+"'";
            Statement st= Connection.createStatement();
            st.executeUpdate(req);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(commandeService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

     public ObservableList<lignecommande>  findAll() {
            ObservableList<lignecommande> listL=FXCollections.observableArrayList();
            String req;
            req ="SELECT * FROM `lignecommande` ";
         try {
             Statement st= Connection.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next()){
                 lignecommande l = new lignecommande();
                 l.setIdLigne(rs.getInt(1));
                 l.setIdCommande(rs.getInt(2));
                 l.setCodeProduit(rs.getInt(3));
                 l.setQuantite(rs.getInt(4));
                 l.setPrix(rs.getFloat(5));

                 listL.add(l);
             }
         } catch (SQLException ex) {
             Logger.getLogger(commandeService.class.getName()).log(Level.SEVERE, null, ex);
         }
            
            
            return listL;    }

    @Override
    public ObservableList<lignecommande> findByUserId(int id) {
ObservableList<lignecommande> listL=FXCollections.observableArrayList();
            String req;
            req ="SELECT * FROM `lignecommande` where idCommande="+id+"";
         try {
             Statement st= Connection.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next()){
                 lignecommande l = new lignecommande();
                 l.setIdLigne(rs.getInt(1));
                 l.setIdCommande(rs.getInt(2));
                 l.setCodeProduit(rs.getInt(3));
                 l.setQuantite(rs.getInt(4));
                 l.setPrix(rs.getFloat(5));

                 listL.add(l);
             }
         } catch (SQLException ex) {
             Logger.getLogger(commandeService.class.getName()).log(Level.SEVERE, null, ex);
         }
            
            
            return listL;        }
    
    
    public float montantParCommande(int id) throws SQLException{
        
       
               String req2 ="SELECT SUM(prix) as montantParC FROM `lignecommande` where idCommande="+id+"";
               Statement st= Connection.createStatement();
             ResultSet rs1=st.executeQuery(req2);
             System.out.println(rs1.first());
             
             
        return rs1.getFloat("montantParC");
    
    
    
    
    }
         public float calcul() throws SQLException{
             String req1 ="SELECT SUM(prix) as tot from `lignecommande`";
             Statement st= Connection.createStatement();
             ResultSet rs=st.executeQuery(req1);
             System.out.println(rs.first());
             
             
        return rs.getFloat("tot");
         
         
         }
         public int PrixPro(int id) throws SQLException{
             String req1 ="SELECT prix from `produit` where codeProduit="+id+"";
             Statement st= Connection.createStatement();
             ResultSet rs=st.executeQuery(req1);
             System.out.println(rs.first());
             
             
        return rs.getInt("prix");
         
         
         }
    
}
