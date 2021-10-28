/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Personne;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author cyrine belhssan
 */
public class AjouterClientController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_m;
    @FXML
    private TextField tf_p;
    @FXML
    private TextField tf_pw;
    @FXML
    private TextField tf_civ;
    @FXML
    private TextField tf_num;
    @FXML
    private TextField tf_Ref;
    @FXML
    private TextField tf_mail;
    @FXML
    private TextField tf_date;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf__ad;
    @FXML
    private Button b_valideC;
 private userservice us;
  //  ObservableList<Personne> listP;
   //  ObservableList<Personne> listC;
    Connection conn= null;
    ResultSet rs = null;
    Statement pst =null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insertC(ActionEvent event) {
        Personne p =new Personne(tf_nom.getText(),tf_prenom.getText(), tf_date.getText(), tf_mail.getText(),tf_pw.getText(),tf__ad.getText(),Integer.parseInt(tf_Ref.getText()),Integer.parseInt(tf_num.getText()),tf_civ.getText(),tf_p.getText(),tf_m.getText());
         us = new userservice();
       us.insertUser(p);
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionClient.fxml"));
            Parent root = loader.load();
             GestionClientController gu = loader.getController();
              gu.UpTableC();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void exitajouC(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionClient.fxml"));
            Parent root = loader.load();
             GestionClientController gu = loader.getController();
             gu.UpTableC();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    }
    

