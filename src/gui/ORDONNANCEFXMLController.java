/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Consultation;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class ORDONNANCEFXMLController implements Initializable {

    @FXML
    private Button btnOrdonnance;
    @FXML
    private TableColumn<Consultation, Integer> id;
    @FXML
    private TableColumn<Consultation, Integer> idrdv;
    @FXML
    private TableColumn<Consultation, String> ord;
    @FXML
    private TableView<Consultation> tbrConst;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ImprimerOrdonnance(ActionEvent event) {
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ordonnance.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Liste des ordonnances");
            Scene sceneAg = new Scene(root);           
            stage.setScene(sceneAg);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ORDONNANCEFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
    
