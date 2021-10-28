/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProduitService;

import Produits.entities.Categories;
import Produits.entities.Produit;
import Produits.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Moez
 */
public class CRUDCategories {
    private Connection cnx;
    private PreparedStatement ste;

    public CRUDCategories() {
        cnx = MyConnection.getInstance().getConnection();
}
    
     public ObservableList<Categories> afficherCategories(){
   ObservableList<Categories> list = FXCollections.observableArrayList();
   
        try {
             String sql = "SELECT * FROM categories";
     
             ste = cnx.prepareStatement(sql);
             ResultSet rs = ste.executeQuery();
             while (rs.next()) {   
                 list.add(new Categories(rs.getInt("id"),rs.getString("nom"), rs.getString("description"),rs.getString("statut")));
//                Categories Cg = new Categories();
//                Cg.setId(rs.getInt("id"));
//                Cg.setNom(rs.getString("nom"));
//                Cg.setDescription(rs.getString("description"));
//                Cg.setStatut(rs.getString("statut"));
//                C.add(Cg);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    return  list;
    
}
    public void ajouterCategories(Categories C){
        String req ="insert into categories (nom,description,statut)"+"values (?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
//            ste.setInt(1,C.getId());
            ste.setString(1,C.getNom());
            ste.setString(2,C.getDescription());
            ste.setString(3,C.getStatut());
            ste.executeUpdate();
            System.out.println("Catégorie ajoutée");
            
        } catch (SQLException ex) {
            
        }
        
    }
    public boolean DeleteCategories(int id){
	        String sql = "DELETE FROM categories WHERE id=?";

	PreparedStatement statement;
	      try 
	      {
	          
	  statement = cnx.prepareStatement(sql);
	statement.setInt(1,id);

	statement.executeUpdate();

	System.out.println( "Deleted succefully");
	} catch (SQLException ex) {
	         
	         return false;
	      }
	      return false;
	    }
    
    public boolean UpdateCategories(Categories C){
		          String sql = "UPDATE categories SET nom=?,description=?,statut=?WHERE id=?";
		 
		PreparedStatement statement;
		        try  
		        {
		statement = cnx.prepareStatement(sql);
                statement.setString(1,C.getNom());
		statement.setString(2,C.getDescription());
                statement.setString(3,C.getStatut());
                statement.setInt(4,C.getId());
                
		 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println(C.getNom()+ "  updated succefully");
		    return true;
		}
		 } catch (SQLException ex) {
		           
		           return false;
		        }
		        return false;
		      }
    
}
