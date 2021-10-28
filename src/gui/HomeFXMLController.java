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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Thrall
 */
public class HomeFXMLController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button commande;
    @FXML
    private Button Statistique;
    @FXML
    private Button buttLiv;
    @FXML
    private Button supper;
    @FXML
    private TextField rech;
    @FXML
    private TableView<?> TableCommande;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> email1;
    @FXML
    private TableColumn<?, ?> email11;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> adrese;
    @FXML
    private TableColumn<?, ?> ville;
    @FXML
    private TableColumn<?, ?> codepostal;
    @FXML
    private TableColumn<?, ?> numtel;
    @FXML
    private TableColumn<?, ?> dateliv;
    @FXML
    private TableColumn<?, ?> prixtot;
    @FXML
    private Button buttRec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void commandePress(ActionEvent event) {
    }

    @FXML
    private void StatistiquePress(ActionEvent event) {
    }

    @FXML
    private void versLivraison(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeLivraisonFXML.fxml"));
            Parent parent=loader.load();
            HomeLivraisonFXMLController alc= loader.getController();
            
            
            buttLiv.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void SupprimerCommande(ActionEvent event) {
    }

    @FXML
    private void versReclamation(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeReclamationFXML.fxml"));
            Parent parent=loader.load();
            HomeReclamationFXMLController alc= loader.getController();
            
            
            buttLiv.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
