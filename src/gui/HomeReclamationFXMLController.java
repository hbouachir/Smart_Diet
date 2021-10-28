/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ReclamationServices;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Thrall
 */
public class HomeReclamationFXMLController implements Initializable {

    @FXML
    private Button buttonAjoutRec;
    @FXML
    private Button buttonSupprimerRec;
    @FXML
    private Button buttonModifierRec;
    @FXML
    private Button buttonAfficherRec;
    @FXML
    private Button retourHome1;
    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> IdClientCol;
    @FXML
    private TableColumn<Reclamation, String> DescCol;
    @FXML
    private TableColumn<Reclamation, Integer> typeCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void versAjouterRec(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterReclamationFXML.fxml"));
            Parent parent=loader.load();
            AjouterReclamationFXMLController alc= loader.getController();
            
            
            retourHome1.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(HomeReclamationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {
        
        Reclamation r = tableReclamation.getSelectionModel().getSelectedItem();
        ReclamationServices crud = new ReclamationServices();
        crud.supprimerReclamation(r);
        
        MyConnection mc= MyConnection.getInstance();
        List<Reclamation> all;
        all= crud.afficherReclamation();
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList(all);
        IdClientCol.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("idPersonne"));
        DescCol.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("typeReclamation"));
        tableReclamation.setItems(reclamations);
        
    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReclamationFXML.fxml"));
            Parent parent=loader.load();
            ModifierReclamationFXMLController alc= loader.getController();
            
            
            retourHome1.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(HomeReclamationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void afficherReclamation(ActionEvent event) {
        
        MyConnection mc= MyConnection.getInstance();
        List<Reclamation> all;
        ReclamationServices crud = new ReclamationServices();
        all= crud.afficherReclamation();
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList(all);
        IdClientCol.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("idPersonne"));
        DescCol.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("typeReclamation"));
        tableReclamation.setItems(reclamations);
        
    }

    @FXML
    private void versHome1(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeFXML.fxml"));
            Parent parent=loader.load();
            HomeFXMLController alc= loader.getController();
            
            
            retourHome1.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(HomeReclamationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
