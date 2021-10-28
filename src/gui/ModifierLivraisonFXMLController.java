/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Livraison;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.LivraisonServices;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Thrall
 */
public class ModifierLivraisonFXMLController implements Initializable {
    private LivraisonServices sl = new LivraisonServices();
    
    @FXML
    private TextField tfModIdLiv;
    @FXML
    private TextField tfModIdPay;
    @FXML
    private TextField tfModEtat;
    @FXML
    private Button retourAjouter;
    @FXML
    private Button buttModifer;
    @FXML
    private Button buttActualiser;
    @FXML
    private TableView<Livraison> tableModification;
    @FXML
    private TableColumn<Livraison, Integer> modIdLivreurCol;
    @FXML
    private TableColumn<Livraison, Integer> modIdPaymentCol;
    @FXML
    private TableColumn<Livraison, Integer> modEtatLivraisonCol;
    @FXML
    private TableColumn<Livraison, String> modDateLivraisonCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void versAjouter(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeLivraisonFXML.fxml"));
            Parent parent=loader.load();
            HomeLivraisonFXMLController alc= loader.getController();
            
            
            tfModIdLiv.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(ModifierLivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void ActualiserTable(ActionEvent event) {
        
        MyConnection mc= MyConnection.getInstance();
        List<Livraison> all;
        LivraisonServices crud = new LivraisonServices();
        all= crud.afficherLivraison();
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList(all);
        modIdLivreurCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idLivreur"));
        modIdPaymentCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idPayment"));
        modEtatLivraisonCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("etatLivraison"));
        modDateLivraisonCol.setCellValueFactory(new PropertyValueFactory<Livraison,String>("dateLivraison"));
        tableModification.setItems(livraisons);
        
    }

    @FXML
    private void selectedLivraisonModif(MouseEvent event) {
        
        Livraison l = tableModification.getSelectionModel().getSelectedItem();
        tfModIdLiv.setText(Integer.toString(l.getIdLivreur()));
        tfModIdPay.setText(Integer.toString(l.getIdPayment()));
        tfModEtat.setText(Integer.toString(l.getEtatLivraison()));
        
    }

    @FXML
    private void modifierLivraisons(ActionEvent event) {
        Livraison l = new Livraison();
                l.setIdLivreur(Integer.parseInt(tfModIdLiv.getText()));
                l.setIdPayment(Integer.parseInt(tfModIdPay.getText()));
                l.setEtatLivraison(Integer.parseInt(tfModEtat.getText()));
                
                sl.modifierLivraison(l);
                
                MyConnection mc= MyConnection.getInstance();
        List<Livraison> all;
        LivraisonServices crud = new LivraisonServices();
        all= crud.afficherLivraison();
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList(all);
        modIdLivreurCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idLivreur"));
        modIdPaymentCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idPayment"));
        modEtatLivraisonCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("etatLivraison"));
        modDateLivraisonCol.setCellValueFactory(new PropertyValueFactory<Livraison,String>("dateLivraison"));
        tableModification.setItems(livraisons);
    }
    
}
