
package PaymentService;

import Payments.entities.Facture;
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

public class CRUDFacture {
    connexion_BD connbase = new connexion_BD();
    Connection con= connbase.getconnection();
public CRUDFacture(){
    	super();
       
}
//ajouterrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr----------------------
public void insertFacture(Facture p) throws SQLException{
       String req = "INSERT INTO `facture`(`idFacture`,`idCommande`, `etatPayment`, `dateFacture`, `name`) "
            +"VALUES (?,?,?,?,?)";
   try{ 
   PreparedStatement pt = con.prepareStatement(req);
  pt.setString(1, null);
pt.setString(2,Integer.toString( p.getIdCommande()));
pt.setString(3, p.getEtatPayment());
pt.setString(4, p.getDateFacture());
pt.setString(5, p.getName());


pt.executeUpdate();
} catch (SQLException ex) {
Logger.getLogger(CRUDFacture.class.getName()).log(Level.SEVERE, null , ex );
}}

//affichageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    public ObservableList<Facture> findAll() {
         ObservableList<Facture> listA = FXCollections.observableArrayList();
      
        try {
               String sql ="SELECT* FROM facture";  
        PreparedStatement ste = con.prepareStatement(sql);
             ResultSet rs = ste.executeQuery();
             while (rs.next()) {  
         
              listA.add(new Facture (rs.getInt("idFacture"),rs.getInt("idCommande"),rs.getString("etatPayment"),rs.getString("dateFacture"),rs.getString("Name")));
              Facture pt= new Facture();
                
  pt.setIdFacture(rs.getInt("idFacture"));
             pt.setIdCommande(rs.getInt("idCommande"));
            pt.setEtatPayment(rs.getString("etatPayment"));
             pt.setDateFacture(rs.getString("dateFacture"));
                 pt.setName(rs.getString("Name"));
              
       }
         } catch (SQLException ex) {
            Logger.getLogger(CRUDPayments.class.getName()).log(Level.SEVERE, null, ex);
       
}
         return listA ; 
    }
////deleteeeeeeeeeeeeeeeeeeeee
 

  public boolean DeleteFacture(String dateFacture){
	        String sql = "DELETE FROM facture WHERE dateFacture=?";

	PreparedStatement statement;
	      try 
	      {
	          
	  statement = con.prepareStatement(sql);
	statement.setString(4,dateFacture);

	statement.executeUpdate();

	System.out.println("Deleted succefully");
	} catch (SQLException ex) {
	         
	         return false;
	      }
	      return false;
	    }
}