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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.rendezvousService;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class TemplateController implements Initializable {

    @FXML
    private Button RDV;
    @FXML
    private Button cons;
    @FXML
    private AnchorPane rech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void insertRdv(ActionEvent event) throws IOException {
        rendezvousService rdvService = new rendezvousService();
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterRdvFXML.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = new Stage();
            stage.setTitle("Liste des RendezVous");
            Scene sceneAg = new Scene(root);           
            stage.setScene(sceneAg);
            stage.show();
 
    } 

    @FXML
    private void insertCons(ActionEvent event) {
                 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsultationFXML.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Liste des RendezVous");
            Scene sceneAg = new Scene(root);           
            stage.setScene(sceneAg);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    
}
