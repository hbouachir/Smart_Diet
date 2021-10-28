/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProduitService;


import Produits.entities.Produit;
import Produits.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;

/**
 *
 * @author Moez
 */
public class CRUDProduit {
    private Connection cnx;
    private PreparedStatement ste;

    public CRUDProduit() {
        cnx = MyConnection.getInstance().getConnection();
}
    public void ajouterProduits(Produit p){
        String req ="insert into produit (nom,description,quantité,prix,statut,categorie)"+"values (?,?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
      
            ste.setString(1,p.getNom());
            ste.setString(2,p.getDescription());
            ste.setString(3,p.getQuantité());
            ste.setString(4,p.getPrix());
            ste.setString(5,p.getStatut());
            ste.setString(6,p.getCategorie());
//            ste.setInt(6,p.getId_categories());
            ste.executeUpdate();
            System.out.println("Produit ajoutée");
            
        } catch (SQLException ex) {
            
        }
        
    }
      public ObservableList<Produit> afficherProduit(){
    ObservableList<Produit> list = FXCollections.observableArrayList();
   
        try {
             String sql = "SELECT * FROM produit";
     
             ste = cnx.prepareStatement(sql);
             ResultSet rs = ste.executeQuery();
             while (rs.next()) {   
              list.add(new Produit(rs.getInt("codeProduit"),rs.getString("nom"), rs.getString("description"),rs.getString("quantité"),rs.getString("prix"),rs.getString("statut"),rs.getString("categorie")));
//                 Produit pr = new Produit();
//                pr.setCodeProduit(rs.getInt("codeProduit"));
//                pr.setNom(rs.getString("nom"));
//                pr.setDescription(rs.getString("description"));
//                pr.setQuantité(rs.getString("quantité"));
//                pr.setPrix(rs.getString("prix"));
//                pr.setStatut(rs.getString("statut"));
//                pr.setId_categories(rs.getInt("id_categories"));
//                p.add(pr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    return  list ;
    
}

       // DELETE produit from data base
       
		public boolean DeleteProd(int codeProduit){
	        String sql = "DELETE FROM produit WHERE codeProduit=?";

	PreparedStatement statement;
	      try 
	      {
	          
	  statement = cnx.prepareStatement(sql);
	statement.setInt(1,codeProduit);

	statement.executeUpdate();

	System.out.println("Deleted succefully");
	} catch (SQLException ex) {
	         
	         return false;
	      }
	      return false;
	    }
     //Update produit in Data base
		   public boolean UpdateProduit(Produit p){
		          String sql = "UPDATE produit SET nom=?,description=?,quantité=?,prix=?,statut=?,categorie=?  WHERE codeProduit=?";
//		
		PreparedStatement statement;
		        try  
		        {
		statement = cnx.prepareStatement(sql);
                statement.setString(1,p.getNom());
		statement.setString(2,p.getDescription());
                statement.setString(3,p.getQuantité());
                statement.setString(4,p.getPrix());
                statement.setString(5,p.getStatut());
                statement.setString(6,p.getCategorie());
//                statement.setInt(6,p.getId_categories());
                statement.setInt(7,p.getCodeProduit());
                
		 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println(p.getNom()+ "  updated succefully");
		    return true;
		}
		 } catch (SQLException ex) {
		           
		           return false;
		        }
		        return false;
		      }
		
}