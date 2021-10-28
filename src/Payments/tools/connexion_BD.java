
package Payments.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexion_BD {
     Connection con;
     static connexion_BD instance;
  
    public connexion_BD(){
    	final String user="root";
    	final String pass=""; //sessionVariables=FOREIGN_KEY_CHECKS=0&u
    	final String url = "jdbc:mysql://localhost/smart_bd?&sessionVariables=FOREIGN_KEY_CHECKS=0&useTimezone=true&serverTimezone=UTC"; //?autoReconnect=true&useSSL=false
          
         
      try{
          con=DriverManager.getConnection(url,user,pass);
            //con = DriverManager.getConnection("jdbc:mysql://IP:3306/TABLENAME?autoReconnect=true","user", "pass");
            
          System.out.println("succes vous etes connecter");
          
          
      }catch(SQLException e){
          System.out.println("erreur de connexion a la base");
          System.err.println(e);
      }
    }
   
    public static connexion_BD getInstance (){
if(instance ==null){
instance= new connexion_BD();
} return instance;
}
    public Connection getconnection(){
return con ;
}
    
}
    

    

