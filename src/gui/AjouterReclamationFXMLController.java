/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ReclamationServices;

/**
 * FXML Controller class
 *
 * @author Thrall
 */
public class AjouterReclamationFXMLController implements Initializable {

    @FXML
    private TextField tfIdClient;
    @FXML
    private TextField tfDescriptionRec;
    @FXML
    private TextField tfTypeRec;
    @FXML
    private Button buttSubmit;
    @FXML
    private Button retourReclamation;
    @FXML
    private Label controleIdClient;
    @FXML
    private Label ControleDescription;
    @FXML
    private Label controleTypeRec;
    
    private ReclamationServices rec;
    @FXML
    private Label succAjoutRec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rec = new ReclamationServices();
    }    

    @FXML
    private void submit(ActionEvent event) {
        
        if (tfIdClient.getText().isEmpty())
            controleIdClient.setText("Id client est vide!");
            else if (tfDescriptionRec.getText().isEmpty())
             ControleDescription.setText("Description est vide!");
            else if (tfTypeRec.getText().isEmpty())
             controleTypeRec.setText("Type de Reclamation est vide!");
                    
            else { 
                Reclamation r = new Reclamation();
                r.setIdPersonne(Integer.parseInt(tfIdClient.getText()));
                r.setDescription(tfDescriptionRec.getText());
                r.setTypeReclamation(Integer.parseInt(tfTypeRec.getText()));
                
                rec.ajouterReclamation(r);
                succAjoutRec.setText("Reclamation ajoutée avec succée!");
        
    }
    }

    @FXML
    private void versHomeRec(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeReclamationFXML.fxml"));
            Parent parent=loader.load();
            HomeReclamationFXMLController alc= loader.getController();
            
            
            buttSubmit.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(AjouterReclamationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    


    
}
