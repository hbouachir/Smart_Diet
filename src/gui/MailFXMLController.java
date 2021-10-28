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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.Mailing;

/**
 * FXML Controller class
 *
 * @author Thrall
 */
public class MailFXMLController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button buttEnvoie;
    @FXML
    private TextField tfMail;
    @FXML
    private TextField tfObjet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerMail(ActionEvent event) {
        
        Mailing.send("oussama.lahouel@esprit.tn",tfPassword.getText() , tfEmail.getText(), tfObjet.getText(), tfMail.getText());
       
        
    }

    @FXML
    private void versHomeLivraison2(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeLivraisonFXML.fxml"));
            Parent parent=loader.load();
            HomeLivraisonFXMLController alc= loader.getController();
            
            
            tfEmail.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(MailFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
