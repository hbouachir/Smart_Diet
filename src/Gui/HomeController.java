/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ih3b
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnProduit;
    @FXML
    private Button btnCategorires; 
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   


    @FXML
    private void CRUDProduitTransition(ActionEvent event) { 
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDProduit.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gestion des Produits");
            Scene sceneAg = new Scene(root);           
            stage.setScene(sceneAg);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     @FXML
    private void CRUDCategoriesTransition(ActionEvent event) { 
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDCategories.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gestion des Catégories");
            Scene sceneAg = new Scene(root);           
            stage.setScene(sceneAg);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
