/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import entite.rendezvous;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import static java.util.Collections.list;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import utils.MyDB;

import entite.rendezvous;
import utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;



/**
 *
 * @author Nour
 */
public class rendezvousService {
        private Connection connection;
        private PreparedStatement ste;

    public rendezvousService(){
        connection = MyDB.getInstance().getConnection();
    }

//   
//    public void ajouterRdv(rendezvous r) {
//
//        String req = "INSERT INTO `rendezvous`(`dateRdv`, `heure`) "
//            +"VALUES (?,?)";
//    try{ 
//    PreparedStatement pt = connection.prepareStatement(req);
//    //clé primaire
//    pt.setString(1, null);
//    //clé étrangeaire
//    pt.setString(2,Integer.toString( r.getidPtient()));
//    pt.setString(3, r.getdateRdv());
//    pt.setString(4, r.getheure());

//
//    pt.executeUpdate();
//    } catch (SQLException ex) {
//    Logger.getLogger(rendezvousService.class.getName()).log(Level.SEVERE, null , ex );
//    }}

    public void ajouterRendezvous(rendezvous r){
        String req ="insert into rendezvous (idRdv, idPatient ,dateRdv,heure)"+"values (?,?,?,?)";
        try {
            ste = connection.prepareStatement(req);
            ste.setString(1,Integer.toString( r.getidRdv()));
            ste.setString(2,Integer.toString( r.getidPtient()));
            ste.setString(3,r.getDateRdv());
            ste.setString(4,r.getHeure());
            ste.executeUpdate();
            System.out.println("rendez-vous ajoutée");
            
        } catch (SQLException ex) {
            
        }
        
    }

    
     public ObservableList<rendezvous> afficherRdv(){
    ObservableList<rendezvous> list = FXCollections.observableArrayList();
   try {
             String req = "SELECT * FROM `rendezvous`";
             
             Statement st=connection.createStatement();
             ResultSet rs=st.executeQuery(req);
//             ste = connection.prepareStatement(req);
//             ResultSet rs = ste.executeQuery();
             while (rs.next()) {   
              //listR.add(new rendezvous(rs.getInt("idRdv"), rs.getInt("idPatient"),rs.getString("dateRdv"),rs.getString("heure")));
                 rendezvous rdv = new rendezvous(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
                 list.add(rdv);
             
                 
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.out.println(list.toString());
    return  list ;
    
}
   
    public boolean Delete(String dateRdv){
                 String req = "DELETE FROM rendezvous WHERE dateRdv=?";
	PreparedStatement statement;
	      try 
	      {           
	  statement = connection.prepareStatement(req);
	statement.setString(1,dateRdv);
	statement.executeUpdate();
	System.out.println("Deleted succefully");
	} catch (SQLException ex) {
	         
	         return false;
	      }
	      return false;
	    }


     public boolean UpdateRendezvous(rendezvous r){
	String req = "UPDATE rendezvous SET idPatient=? ,dateRdv=?,heure=?  WHERE idRdv=?";
		
		PreparedStatement statement;
		        try  
		        {
		statement = connection.prepareStatement(req);
                statement.setInt(1,r.getidRdv());
                statement.setInt(2,r.getidPtient());
                statement.setString(3,r.getDateRdv());
		statement.setString(4,r.getHeure());
                
                
		 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println(r.getDateRdv()+ "  updated succefully");
		    return true;
		}
		 } catch (SQLException ex) {
		           
		           return false;
		        }
		        return false;
		      }
   
}
