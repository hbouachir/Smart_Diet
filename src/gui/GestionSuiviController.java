/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Personne;
import entities.Suivi;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.suiviservice;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author cyrine belhssan
 */
public class GestionSuiviController implements Initializable {

    @FXML
    private TableColumn<Personne, Integer> col_id;
    @FXML
    private TableColumn<Personne, String> col_nom;
    @FXML
    private TableColumn<Personne, String> col_prenom;
    @FXML
    private TableColumn<Personne, String> col_date;
    @FXML
    private TableColumn<Personne, String> col_mail;
    @FXML
    private TableColumn<Personne, String> col_pw;
    @FXML
    private TableColumn<Personne, String> col_ad;
    @FXML
    private TableColumn<Personne,Integer > col_num;
    @FXML
    private TableColumn<Personne, String> col_civ;
    @FXML
    private TableColumn<Personne, Integer> col_Ref;
    @FXML
    private TableColumn<Personne, String> col_m;
     @FXML
    private TableColumn<Personne, String> col_p;
   
  /*  @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_prenom;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableColumn<?, ?> col_mail;
    @FXML
    private TableColumn<?, ?> col_pw;
    @FXML
    private TableColumn<?, ?> col_ad;
    @FXML
    private TableColumn<?, ?> col_Ref;
    @FXML
    private TableColumn<?, ?> col_num;
    @FXML
    private TableColumn<?, ?> col_civ;
    @FXML
    private TableColumn<?, ?> col_p;
    @FXML
    private TableColumn<?, ?> col_m;*/
    @FXML
    private TableColumn<Suivi, Integer> col_ids;
    @FXML
    private TableColumn<Suivi, Integer> col_idu;
    @FXML
    private TableColumn<Suivi, Float> col_t;
    @FXML
    private TableColumn<Suivi, Float> col_po;
    @FXML
    private TableColumn<Suivi, Float> col_g;
    @FXML
    private TableColumn<Suivi, String> col_pd;
    @FXML
    private TableColumn<Suivi, String> col_r;
    @FXML
    private TableColumn<Suivi, String> col_di;
    @FXML
    private TableColumn<Suivi, String> col_n;
    @FXML
    private TableColumn<Suivi, String> col_ds;
    @FXML
    private TableView<Object> table_suivi;
     private suiviservice us;
    ObservableList<Object> listP;
   // ObservableList<> listC;
    Connection conn= null;
    ResultSet rs = null;
    Statement pst =null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          us = new suiviservice();
      col_id.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nom.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_date.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_mail.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pw.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_ad.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_Ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_num.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civ.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
               col_p.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_m.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
                
                   col_ids.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idSuivi"));
       col_idu.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idUser"));
        col_ds.setCellValueFactory(new PropertyValueFactory<Suivi,String>("dateSuivi"));
         col_t.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("taille"));
          col_po.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("poid"));
          col_g.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("glycemie"));
           col_pd.setCellValueFactory(new PropertyValueFactory<Suivi,String>("petitDej"));
            col_r.setCellValueFactory(new PropertyValueFactory<Suivi,String>("repas"));
             col_di.setCellValueFactory(new PropertyValueFactory<Suivi,String>("diner"));
              col_n.setCellValueFactory(new PropertyValueFactory<Suivi,String>("notes"));
              
  //  String S= "client";
    listP = us.findAllSC();
    table_suivi.setItems(listP);
    }    

    @FXML
    private void exitGS(ActionEvent event) {
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
    

