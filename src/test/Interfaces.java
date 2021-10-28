/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




import entites.commande;
import entites.lignecommande;
import service.commandeService;
import service.lignecommandeService;
import utils.MyDB;
import entites.commande;
import entites.lignecommande;
import service.commandeService;
import service.lignecommandeService;
import utils.MyDB;

/**
 *
 * @author HAMZA
 */
public class Interfaces extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/commandeAdminFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
            MyDB.getInstance();
        lignecommande c=new lignecommande(4,35,2,34);
        lignecommandeService cs=new lignecommandeService() ;
        cs.insert(c);
         System.out.println("added");
    }

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
             MyDB.getInstance();
        lignecommande c=new lignecommande(4,35,2,34);
        lignecommandeService cs=new lignecommandeService() ;
        cs.insert(c);
         System.out.println("added");
       
    
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HAMZA
 */
//public class MainTest {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//       MyDB.getInstance();
//        lignecommande c=new lignecommande(4,35,2,34);
//        lignecommandeService cs=new lignecommandeService() ;
//        cs.insert(c);
//        
//    }
//    
//}
