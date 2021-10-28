/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Livraison;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.LivraisonServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterLivraisonFXMLController implements Initializable {

    @FXML
    private Button butt1;
    @FXML
    private TextField tfIdLivreur;
    @FXML
    private TextField tfIdPayment;
    @FXML
    private TextField tfEtatLivraison;
    
    private LivraisonServices sl;
    @FXML
    private Button retourAjout;
    @FXML
    private Label controleIdLivreur;
    @FXML
    private Label controleIdPayment;
    @FXML
    private Label controleEtatLivraison;
    @FXML
    private Label succAjout;
    private Label tfDateLivraison;
    @FXML
    private TextField tfDateLiv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sl = new LivraisonServices();
    }    

    @FXML
    private void ajouterLivraisonFX(ActionEvent event) {
    
        if (tfIdLivreur.getText().isEmpty())
            controleIdLivreur.setText("Id livreur est vide!");
            else if (tfIdPayment.getText().isEmpty())
             controleIdPayment.setText("Id payment est vide!");
            else if (tfEtatLivraison.getText().isEmpty())
             controleEtatLivraison.setText("etat livraison est vide!");
            
            
                    
            else { 
                Livraison l = new Livraison();
                l.setIdLivreur(Integer.parseInt(tfIdLivreur.getText()));
                l.setIdPayment(Integer.parseInt(tfIdPayment.getText()));
                l.setEtatLivraison(Integer.parseInt(tfEtatLivraison.getText()));                
                l.setDateLivraison(tfDateLiv.getText());
                
                sl.ajouterLivraison(l);
                succAjout.setText("Livraison ajoutée avec succée!");
                
            }
            }
    
    /*private void afficherLivraisonFX(ActionEvent event) {
        List<Livraison> list= sl.afficherLivraison();
        
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherLivraisonFXML.fxml"));
            Parent parent=loader.load();
            AfficherLivraisonFXMLController alc= loader.getController();
            alc.setTfListLivraison(list.toString());
            
            tfIdLivreur.getScene().setRoot(parent);
            
          
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(AjouterLivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            Scene scene = new Scene(parent);
            Stage st1 = new Stage();
            st1.setScene(scene);
            st1.show(); 
        
        
    }*/

    @FXML
    private void versHomeLiv(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeLivraisonFXML.fxml"));
            Parent parent=loader.load();
            HomeLivraisonFXMLController alc= loader.getController();
            
            
            butt1.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(AjouterLivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
