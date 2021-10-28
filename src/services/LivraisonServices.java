
package services;

import entities.Livraison;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LivraisonServices {
    private Connection cnx;
    private PreparedStatement ste;

    public LivraisonServices() {
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void ajouterLivraison(Livraison l){
        String req ="INSERT INTO livraison (`idLivreur`,`idPayment`,`etatLivraison`,`dateLivraison`)"+"values (?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1, l.getIdLivreur());
            ste.setInt(2, l.getIdPayment());
            ste.setInt(3,l.getEtatLivraison());
            ste.setString(4,l.getDateLivraison());
            ste.executeUpdate();
            System.out.println("Livraison ajoutée");
            
        } catch (SQLException ex) {
            
        }
        
    }
    
    public void supprimerLivraison(Livraison l){
        String req ="DELETE FROM livraison WHERE idLivreur=?";
        try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1, l.getIdLivreur());
            ste.executeUpdate();
            System.out.println("Livraison supprimée");
            
        } catch (SQLException ex) {
            
        }       
    }
    
    public void modifierLivraison(Livraison l){
        
        String req ="UPDATE `livraison` SET `idPayment`=?,`etatLivraison`=? WHERE `idLivreur`=?";
        try {
            ste = cnx.prepareStatement(req);
            
            ste.setInt(1, l.getIdPayment());
            ste.setInt(2, l.getEtatLivraison());
            ste.setInt(3, l.getIdLivreur());
            ste.executeUpdate();
            System.out.println("Livraison modifié!");
            
        } catch (SQLException ex) {
            
        } 
    }
    
    public List<Livraison> afficherLivraison(){
    List<Livraison> livraisons = new ArrayList<>();
   
        try {
             String sql = "select * from livraison";
     
             ste = cnx.prepareStatement(sql);
             ResultSet rs = ste.executeQuery();
             while (rs.next()) {   
                 Livraison l = new Livraison();
                 l.setIdLivraison(rs.getInt("idLivraison"));
                 l.setIdLivreur(rs.getInt("IdLivreur"));
                 l.setIdPayment(rs.getInt("IdPayment"));
                 l.setEtatLivraison(rs.getInt("EtatLivraison"));
                 l.setDateLivraison(rs.getString("dateLivraison"));
                 livraisons.add(l);
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    return  livraisons;
    
}
    public List<Livraison> afficherLivraisonByEtat(int etat) throws SQLException{
        List<Livraison> livraisons = new ArrayList<>();
        String sql = "select * from livraison where etatLivraison="+etat+"";
     
             ste = cnx.prepareStatement(sql);
             ResultSet rs = ste.executeQuery();
             while (rs.next()) {   
                 Livraison l = new Livraison();
                 l.setIdLivraison(rs.getInt("idLivraison"));
                 l.setIdLivreur(rs.getInt("IdLivreur"));
                 l.setIdPayment(rs.getInt("IdPayment"));
                 l.setEtatLivraison(rs.getInt("EtatLivraison"));
                 
                 livraisons.add(l);
                
             }
            
        return livraisons;
    
}
}