
package PaymentService;

import Payments.entities.Payments;
import Payments.tools.connexion_BD;
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


public class CRUDPayments  {
   private PreparedStatement pt;
  //implements service <Payments>
    connexion_BD connbase = new connexion_BD();
    Connection con= connbase.getconnection();
public CRUDPayments(){
    	super();
       
}
public void insertpayement(Payments p) {
    String req = "INSERT INTO `payment`(`idPayment`,`idFacture`,`numeroCompte`, `password`, `dateExpiration`) "
            +"VALUES (?,?,?,?,?)";
   try{ 
    pt = con.prepareStatement(req);
   //clé primaire
  pt.setString(1, null);
  //clé étrangeaire
pt.setString(2,Integer.toString( p.getIdFacture()));
pt.setString(3, p.getNumeroCompte());
pt.setString(4, p.getPassword());
pt.setString(5, p.getDateExpiration());

pt.executeUpdate();
} catch (SQLException ex) {
Logger.getLogger(CRUDPayments.class.getName()).log(Level.SEVERE, null , ex );
}}
//affichage
   
    public ObservableList<Payments> findAll() {
         ObservableList<Payments> listA = FXCollections.observableArrayList();
      
        try {
          
               String req ="SELECT* FROM payment";
      
                Statement ste = con.createStatement();
           
                 ResultSet rs = ste.executeQuery(req);
             while (rs.next()) {  
         Payments pay =new Payments (rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
         listA.add(pay);
       
              
       }
         } catch (SQLException ex) {
            //Logger.getLogger(CRUDPayments.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
    
       
}
        System.out.println(listA.toString());
         return listA ; 
    }
    //delete
     // DELETE produit from data base
       
		public boolean DeletePayement(String numeroCompte){
	        String sql = "DELETE FROM payment WHERE numeroCompte=?";

	PreparedStatement statement;
	      try 
	      {
	          
	  statement = con.prepareStatement(sql);
	statement.setString(1,numeroCompte);

	statement.executeUpdate();

	System.out.println("Deleted succefully");
	} catch (SQLException ex) {
	         
	         return false;
	      }
	      return false;
	    }
           	   public boolean Update(Payments p){
		          String sql = "UPDATE payment SET idFacture=?,numeroCompte=?,password=?,dateExpiration=? WHERE idPayment=?";
//		
		PreparedStatement statement;
		        try  
		        {
		statement = con.prepareStatement(sql);
                statement.setString(1,Integer.toString( p.getIdFacture()));
		statement.setString(2,p.getNumeroCompte());
                statement.setString(3,p.getPassword());
                statement.setString(4,p.getDateExpiration());
            
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println(p.getNumeroCompte()+ "  updated succefully");
		    return true;
		}
		 } catch (SQLException ex) {
		           
		           return false;
		        }
		        return false;
		      }

    
    public void insert(Payments object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void delete(Payments object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void update(Payments object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
