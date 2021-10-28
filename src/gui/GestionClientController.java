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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author cyrine belhssan
 */
public class GestionClientController implements Initializable {
    
     @FXML
    private TableView<Personne> table_client;

    @FXML
    private TableColumn<Personne, Integer> col_idC;
    @FXML
    private TableColumn<Personne, String> col_nomC;
    @FXML
    private TableColumn<Personne, String> col_prenomC;
    @FXML
    private TableColumn<Personne, String> col_dateC;
    @FXML
    private TableColumn<Personne, String> col_mailC;
    @FXML
    private TableColumn<Personne, String> col_pwC;
    @FXML
    private TableColumn<Personne, String> col_adC;
    @FXML
    private TableColumn<Personne, Integer> col_Ref;
    @FXML
    private TableColumn<Personne, Integer> col_numC;

    @FXML
    private TableColumn<Personne, String> col_civC;
    @FXML
    private TableColumn<Personne, String> col_pC;
    @FXML
    private TableColumn<Personne, String> col_mC;
       @FXML
    private TextField tf_reC;
 private userservice us;
  //  ObservableList<Personne> listP;
     ObservableList<Personne> listC;
    Connection conn= null;
    ResultSet rs = null;
    Statement pst =null;
    @FXML
    private Button b_ajouC;
    @FXML
    private TextField tf_pC;
    @FXML
    private TextField tf_pwC;
    @FXML
    private TextField tf_mC;
    @FXML
    private TextField tf_mailC;
    @FXML
    private TextField tf_dateC;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_idC;
    @FXML
    private TextField tf_adC;
    @FXML
    private TextField tf_civC;
    @FXML
    private TextField tf_numC;
    @FXML
    private TextField tf_Ref;
    int index =-1;
    /**
     * Initializes the controller class.
     */
      public void UpTableC() {
        us = new userservice();
    col_idC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_dateC.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_mailC.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pwC.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_adC.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_Ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_numC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civC.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
               col_pC.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_mC.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
    
    listC = us.findClientParNFX("client");
    table_client.setItems(listC);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         us = new userservice();
      col_idC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_dateC.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_mailC.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pwC.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_adC.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_Ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_numC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civC.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
               col_pC.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_mC.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
  //  String S= "client";
    listC = us.findClientParNFX("client");
    table_client.setItems(listC);
    }    

    @FXML
    private void rechercheC(KeyEvent event) {
          us = new userservice();
         col_idC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_dateC.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_mailC.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pwC.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_adC.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_Ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_numC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civC.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
               col_pC.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_mC.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
    String n = tf_reC.getText();
    listC = us.findPersonneParNFX(n);
    table_client.setItems(listC);  
    if(  tf_reC.getText().equalsIgnoreCase("")){
        UpTableC();
    }
    
    }

    @FXML
    private void deleteC(ActionEvent event) {
        int d=  Integer.parseInt(col_idC.getCellData(index).toString());
       us = new userservice();
      us.deletePersonne(d);
      UpTableC();
    }

    @FXML
    private void AjouC(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterClient.fxml"));
            Parent root = loader.load();
             AjouterClientController gu = loader.getController();
             // gu.UpTableC();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void UpDateC(ActionEvent event) {
        Personne p =new Personne(Integer.parseInt(tf_idC.getText()),tf_nom.getText(),tf_prenom.getText(), tf_dateC.getText(), tf_mailC.getText(),tf_pwC.getText(),tf_adC.getText(),Integer.parseInt(tf_Ref.getText()),Integer.parseInt(tf_numC.getText()),tf_civC.getText(),tf_pC.getText(),tf_mC.getText());
         us = new userservice();
       us.upDatePersonne(p);
       UpTableC();
       // JOptionPane.showMessageDialog(null, "client"+Integer.parseInt(tf_idC.getText())+"modifier...");
    //  l_message.setText("user modifier...");
    }

    @FXML
    private void getData(MouseEvent event) {
          index =table_client.getSelectionModel().getSelectedIndex();
    if (index<= -1){
        return;
    }
     tf_idC.setText(col_idC.getCellData(index).toString());
    tf_nom.setText(col_nomC.getCellData(index).toString());
       tf_prenom.setText(col_prenomC.getCellData(index).toString());
          tf_mailC.setText(col_mailC.getCellData(index).toString());
             tf_pwC.setText(col_pwC.getCellData(index).toString());
                tf_adC.setText(col_adC.getCellData(index).toString());
                   tf_numC.setText(col_numC.getCellData(index).toString());
                      tf_pC.setText(col_pC.getCellData(index).toString());
                        tf_dateC.setText(col_dateC.getCellData(index).toString());
                          tf_Ref.setText(col_Ref.getCellData(index).toString());
                            tf_civC.setText(col_civC.getCellData(index).toString());
                              tf_mC.setText(col_mC.getCellData(index).toString());
    }

    @FXML
    private void exitC(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionUser.fxml"));
            Parent root = loader.load();
             GestionUserController gu = loader.getController();
             gu.UpTable();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
