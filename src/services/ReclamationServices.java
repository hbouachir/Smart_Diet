/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author user
 */
public class ReclamationServices {
    private Connection cnx;
    private PreparedStatement ste;
    
    public ReclamationServices(){
        cnx = MyConnection.getInstance().getConnection();  
    }
    
    public void ajouterReclamation (Reclamation r){
        String req ="INSERT INTO reclamation (`idPersonne`,`description`,`typeReclamation`)"+"values (?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1, r.getIdPersonne());
            ste.setString(2, r.getDescription());
            ste.setInt(3,r.getTypeReclamation());
            ste.executeUpdate();
            System.out.println("Reclamation ajoutée");
            
        } catch (SQLException ex) {
            
        }
    }
    
    public void supprimerReclamation(Reclamation r){
        
        String req ="DELETE FROM reclamation WHERE idPersonne=?";
        try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1, r.getIdPersonne());
            ste.executeUpdate();
            System.out.println("Reclamation supprimée");
            
        } catch (SQLException ex) {
            
        }        
    }
    
    public void modifierReclamation(Reclamation r){
        String req ="UPDATE reclamation set `description`=?,`typeReclamation`=?  WHERE `idPersonne`=?";
        try {
            ste = cnx.prepareStatement(req);
            
            ste.setString(1, r.getDescription());
            ste.setInt(2, r.getTypeReclamation()); 
            ste.setInt(3, r.getIdPersonne());
            ste.executeUpdate();
            System.out.println("Livraison modifié!");
            
        } catch (SQLException ex) {
            
        }
    }
    
    public List<Reclamation> afficherReclamation(){
        List<Reclamation> reclamations = new ArrayList<>();
        try {
            
            
            String sql = "select * from reclamation";
            
            ste = cnx.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setIdReclamation(rs.getInt("idReclamation"));
                r.setIdPersonne(rs.getInt("idPersonne"));
                r.setDescription(rs.getString("description"));
                r.setTypeReclamation(rs.getInt("typeReclamation"));
                reclamations.add(r);
                
                
                
                
            }   } catch (SQLException ex) {
            Logger.getLogger(ReclamationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reclamations;
    
}
}
