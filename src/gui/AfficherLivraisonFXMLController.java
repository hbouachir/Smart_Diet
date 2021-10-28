/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherLivraisonFXMLController implements Initializable {

    @FXML
    private Label tfListLivraison;
    @FXML
    private Button butt3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setTfListLivraison(String text) {
        this.tfListLivraison.setText(text);
    }

    @FXML
    private void retourvers1(ActionEvent event) {
            
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterLivraisonFXML.fxml"));
            Parent parent=loader.load();
            AjouterLivraisonFXMLController alc= loader.getController();
            
            
            tfListLivraison.getScene().setRoot(parent);
                                                     
        } catch (IOException ex) {
            Logger.getLogger(AjouterLivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
    
}
