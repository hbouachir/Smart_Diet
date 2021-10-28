/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Suivi;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.suiviservice;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author cyrine belhssan
 */
public class AjouterSuiviController implements Initializable {

    @FXML
    private TextField n;
    @FXML
    private TextField di;
    @FXML
    private TextField r;
    @FXML
    private TextField pd;
    @FXML
    private TextField g;
    @FXML
    private TextField t;
    @FXML
    private TextField po;
private suiviservice us;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouuS(ActionEvent event) {
       us = new suiviservice();
     //   Suivi p =new Suivi( Integer.parseInt(ref.getText()),dates.getText(),Float.parseFloat(t.getText()),Float.parseFloat( po.getText()),Float.parseFloat(g.getText()),pd.getText(),r.getText(),di.getText(),n.getText());
    
      // us.insertSuiviFX(p);
       Suivi p =new Suivi( Float.parseFloat(t.getText()),Float.parseFloat( po.getText()),Float.parseFloat(g.getText()),pd.getText(),r.getText(),di.getText(),n.getText());
    
       us.insertSuivi(p);
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("suivi.fxml"));
            Parent root = loader.load();
             SuiviController gu = loader.getController();
              gu.UpTableS();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void exitajouS(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("suivi.fxml"));
            Parent root = loader.load();
             SuiviController gu = loader.getController();
             gu.UpTableS();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    }
    

