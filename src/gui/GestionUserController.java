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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author cyrine belhssan
 */
public class GestionUserController implements Initializable {

    @FXML
    private TableView<Personne> table_users;
    @FXML
    private TableColumn<Personne, Integer> col_id;
    @FXML
    private TableColumn<Personne, String> col_nom;
    @FXML
    private TableColumn<Personne, String> col_prenom;
    @FXML
    private TableColumn<Personne, String> col_date;
    @FXML
    private TableColumn<Personne, String> col_email;
    @FXML
    private TableColumn<Personne, String> col_pw;
    @FXML
    private TableColumn<Personne, String> col_addresse;
    @FXML
    private TableColumn<Personne, Integer> col_ref;
    @FXML
    private TableColumn<Personne,Integer > col_num;
    @FXML
    private TableColumn<Personne, String> col_civ;
    @FXML
    private TableColumn<Personne, String> col_rol;
    @FXML
    private TableColumn<Personne, String> col_maladie;
    
 


    private TextField tf_mail;

    private TextField tf_mal;

    private TextField tf_nom;


    private TextField tf_prenom;

    private TextField tf_pw;



    private userservice us;
    ObservableList<Personne> listP;
     ObservableList<Personne> listR;
    Connection conn= null;
    ResultSet rs = null;
    Statement pst =null;
    private TextField tf_p;
    private TextField tf_ad;
    private TextField tf_num;
    private Label l_message;
    @FXML
    private Button b_ref;
    @FXML
    private TextField tf_recherche;
    
    int index =1;
    private TextField tf_id;
    @FXML
    private Button b_supp;
    @FXML
    private Button b_GC;
    @FXML
    private TextField n;
    @FXML
    private TextField pri;
    @FXML
    private TextField p;
    @FXML
    private TextField e;
    @FXML
    private TextField p1;
    /**
     * Initializes the controller class.
     */
    
     public void UpTable() {
        us = new userservice();
  //   label_date=   getText(us.showDate());
      //  us.showDateTime();
      col_id.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nom.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_date.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_email.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pw.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_addresse.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_num.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civ.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
               col_rol.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_maladie.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
    
    listP = us.findAllFx();
    table_users.setItems(listP);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 
     /*      us = new userservice();
      col_id.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nom.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_date.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_email.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pw.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_addresse.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_num.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civ.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
               col_rol.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_maladie.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
    
    listP = us.findAllFx();
    table_users.setItems(listP);*/
    UpTable();
    }    
 public void addUser (){
      Personne p =new Personne(tf_nom.getText(), tf_prenom.getText(), tf_mail.getText(),tf_pw.getText(),tf_ad.getText(),Integer.parseInt(tf_num.getText()),tf_p.getText());
        
       us.insertAutre(p);
       UpTable();
      l_message.setText("user ajouter...");
 }   

    @FXML
    public void refTable(ActionEvent event) {
        us = new userservice();
      col_id.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nom.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_date.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_email.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pw.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_addresse.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_num.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civ.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
               col_rol.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_maladie.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
    
    listP = us.findAllFx();
    table_users.setItems(listP);
    }

   /* private void recherche(ActionEvent event) {
          us = new userservice();
      col_id.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nom.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_date.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_email.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pw.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_addresse.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_num.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civ.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
               col_rol.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_maladie.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
    String n = tf_recherche.getText();
    listR = us.findPersonneParNFX(n);
    table_users.setItems(listR);  
    }*/

    @FXML
    private void recherche(KeyEvent event) {
         us = new userservice();
      col_id.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nom.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_date.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_email.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pw.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_addresse.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_num.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civ.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
               col_rol.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_maladie.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
    String n = tf_recherche.getText();
    listR = us.findPersonneParNFX(n);
    table_users.setItems(listR);  
    if(  tf_recherche.getText().equalsIgnoreCase("")){
        UpTable();
    }
    }

    private void Edit(ActionEvent event) {
         Personne p =new Personne(Integer.parseInt(tf_id.getText()),tf_nom.getText(), tf_prenom.getText(), tf_mail.getText(),tf_pw.getText(),tf_ad.getText(),Integer.parseInt(tf_num.getText()),tf_p.getText());
         us = new userservice();
     //  us.upDatePersonneT(p);
       UpTable();
      l_message.setText("user modifier...");
    }

 

    @FXML
    private void getData(MouseEvent event) {
         index =table_users.getSelectionModel().getSelectedIndex();
    if (index<= -1){
        return;
    }
     tf_id.setText(col_id.getCellData(index).toString());
    tf_nom.setText(col_nom.getCellData(index).toString());
       tf_prenom.setText(col_prenom.getCellData(index).toString());
          tf_mail.setText(col_email.getCellData(index).toString());
             tf_pw.setText(col_pw.getCellData(index).toString());
                tf_ad.setText(col_addresse.getCellData(index).toString());
                   tf_num.setText(col_num.getCellData(index).toString());
                      tf_p.setText(col_rol.getCellData(index).toString());
    }

    @FXML
    private void delete(ActionEvent event) {
        
      int d=  Integer.parseInt(col_id.getCellData(index).toString());
       us = new userservice();
      us.deletePersonne(d);
      UpTable();
      
    }

    @FXML
    private void gestionC(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionClient.fxml"));
            Parent root = loader.load();
             GestionClientController gu = loader.getController();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }

    @FXML
    private void gestionS(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionSuivi.fxml"));
            Parent root = loader.load();
             GestionSuiviController gu = loader.getController();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void suivi(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("suivi.fxml"));
            Parent root = loader.load();
             SuiviController gu = loader.getController();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterNCM(ActionEvent event) {
        Personne p2 =new Personne(n.getText(), p.getText(), e.getText(),p1.getText(),pri.getText());
        
       us.insertAdmin(p2);
       UpTable();
      //l_message.setText("user ajouter...");
    }
 
    
    
    
}
