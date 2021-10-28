/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Livraison;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.LivraisonServices;
import utils.Mailing;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Thrall
 */
public class HomeLivraisonFXMLController implements Initializable {

    @FXML
    private Button buttonAjout;
    @FXML
    private Button retourHome;
    @FXML
    private TableView<Livraison> tableLivraison;
    @FXML
    private TableColumn<Livraison, Integer> idLivreurCol;
    @FXML
    private TableColumn<Livraison, Integer> idPaymentCol;
    @FXML
    private TableColumn<Livraison, Integer> etatPaymentCol;
    @FXML
    private Button buttSupp;
    @FXML
    private Button buttModif;
    @FXML
    private Button buttMail;
    @FXML
    private TableColumn<Livraison, String> dateLivraisonCol;
    @FXML
    private Button buttrecherche;
    @FXML
    private RadioButton radioLivree;
    @FXML
    private RadioButton radioNonLivree;
    @FXML
    private ToggleGroup livreenonlivree;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyConnection mc= MyConnection.getInstance();
        List<Livraison> all;
        LivraisonServices crud = new LivraisonServices();
        all= crud.afficherLivraison();
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList(all);
        idLivreurCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idLivreur"));
        idPaymentCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idPayment"));
        etatPaymentCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("etatLivraison"));
        dateLivraisonCol.setCellValueFactory(new PropertyValueFactory<Livraison,String>("dateLivraison"));
        tableLivraison.setItems(livraisons);
    }    

    @FXML
    private void versAjouter(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterLivraisonFXML.fxml"));
            Parent parent=loader.load();
            AjouterLivraisonFXMLController alc= loader.getController();
            
            
            retourHome.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(HomeLivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void versHome(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeFXML.fxml"));
            Parent parent=loader.load();
            HomeFXMLController alc= loader.getController();
            
            
            retourHome.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(HomeLivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void selectedLivraison(MouseEvent event) {
        
        Livraison l = tableLivraison.getSelectionModel().getSelectedItem();
        //System.out.println(l);
        //LivraisonServices liv = new LivraisonServices();
        //liv.supprimerLivraison(l);
        
       
    }


    @FXML
    private void supprimerLivraison(ActionEvent event) {
        Livraison l = tableLivraison.getSelectionModel().getSelectedItem();
        LivraisonServices crud = new LivraisonServices();
        crud.supprimerLivraison(l);
        
        MyConnection mc= MyConnection.getInstance();
        List<Livraison> all;
        all= crud.afficherLivraison();
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList(all);
        idLivreurCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idLivreur"));
        idPaymentCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idPayment"));
        etatPaymentCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("etatLivraison"));
        dateLivraisonCol.setCellValueFactory(new PropertyValueFactory<Livraison,String>("dateLivraison"));
        tableLivraison.setItems(livraisons);   
    }

    @FXML
    private void modifierLivraison(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierLivraisonFXML.fxml"));
            Parent parent=loader.load();
            ModifierLivraisonFXMLController alc= loader.getController();
            
            
            retourHome.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(HomeLivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void envoyerMail(ActionEvent event) {
        
        Mailing.send("oussama.lahouel@esprit.tn","esprit2031039","thralxl25@gmail.com","a","aa");
        
    }

    @FXML
    private void versMailing(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MailFXML.fxml"));
            Parent parent=loader.load();
            MailFXMLController alc= loader.getController();
            
            
            retourHome.getScene().setRoot(parent);
        } catch (IOException ex) {
            Logger.getLogger(HomeLivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void rechercherLivraison(ActionEvent event) throws SQLException {
        
        String priv = ((RadioButton) livreenonlivree.getSelectedToggle()).getText();
        System.out.println(priv);
        int enumpriv;
        enumpriv = -1;
        if (null != priv)switch (priv) {
            case "Livree":
                enumpriv = 0;
                break;
            case "Non-livree":
                enumpriv = 1;
                break;
            
        }
        
        if (enumpriv==0){
        LivraisonServices crud = new LivraisonServices();    
        MyConnection mc= MyConnection.getInstance();
        List<Livraison> all;
        all= crud.afficherLivraisonByEtat(1);
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList(all);
        idLivreurCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idLivreur"));
        idPaymentCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idPayment"));
        etatPaymentCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("etatLivraison"));
        dateLivraisonCol.setCellValueFactory(new PropertyValueFactory<Livraison,String>("dateLivraison"));
        tableLivraison.setItems(livraisons);
            
        }
        else if (enumpriv==1){
            LivraisonServices crud = new LivraisonServices();    
        MyConnection mc= MyConnection.getInstance();
        List<Livraison> all;
        all= crud.afficherLivraisonByEtat(0);
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList(all);
        idLivreurCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idLivreur"));
        idPaymentCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("idPayment"));
        etatPaymentCol.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("etatLivraison"));
        dateLivraisonCol.setCellValueFactory(new PropertyValueFactory<Livraison,String>("dateLivraison"));
        tableLivraison.setItems(livraisons);
        }
        
        
        
        
    }

    @FXML
    private void livraisonLivree(ActionEvent event) {
    }

    @FXML
    private void livraisonNonLivree(ActionEvent event) {
    }
    
}
