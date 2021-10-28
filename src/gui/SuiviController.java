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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.suiviservice;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author cyrine belhssan
 */
public class SuiviController implements Initializable {

    @FXML
    private TableView<Suivi> table_s;
    @FXML
    private TableColumn<Suivi, Integer> col_ids;
    @FXML
    private TableColumn<Suivi, Integer> col_idu;
    @FXML
    private TableColumn<Suivi, Float> col_t;
    @FXML
    private TableColumn<Suivi, Float> col_p;
    @FXML
    private TableColumn<Suivi, Float> col_g;
    @FXML
    private TableColumn<Suivi, String> col_pd;
    @FXML
    private TableColumn<Suivi, String> col_r;
   
    @FXML
    private TableColumn<Suivi, String> col_n;
    @FXML
    private TableColumn<Suivi, String> col_ds;
    @FXML
    private TableColumn<Suivi, String> col_d;
  private suiviservice us;
    ObservableList<Suivi> listS;
      ObservableList<Suivi> listR;
   // ObservableList<> listC;
    Connection conn= null;
    ResultSet rs = null;
    Statement pst =null;
    int index =-1;
    @FXML
    private TextField rechsid;
    @FXML
    private TextField tf_mailC;
    @FXML
    private TextField tf_dateC;
    @FXML
    private TextField tf_pC;
    @FXML
    private TextField tf_numC;
    @FXML
    private TextField tf_adC;
    @FXML
    private TextField tf_pwC;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_Ref;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_idC;
    @FXML
    private Button b_ms;
    @FXML
    private Label labelUser;
    int d =-1;
    /**
     * Initializes the controller class.
     */
      public void UpTableS() {
        us = new suiviservice();
     col_ids.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idSuivi"));
       col_idu.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idUser"));
        col_ds.setCellValueFactory(new PropertyValueFactory<Suivi,String>("dateSuivi"));
         col_t.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("taille"));
          col_p.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("poid"));
          col_g.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("glycemie"));
           col_pd.setCellValueFactory(new PropertyValueFactory<Suivi,String>("petitDej"));
            col_r.setCellValueFactory(new PropertyValueFactory<Suivi,String>("repas"));
             col_d.setCellValueFactory(new PropertyValueFactory<Suivi,String>("diner"));
              col_n.setCellValueFactory(new PropertyValueFactory<Suivi,String>("notes"));
              
              listS = us.findAllFX();
           // listS = us.findParIdFXT();
    table_s.setItems(listS);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       /*  us = new suiviservice();
        col_ids.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idSuivi"));
       col_idu.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idUser"));
        col_ds.setCellValueFactory(new PropertyValueFactory<Suivi,String>("dateSuivi"));
         col_t.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("taille"));
          col_p.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("poid"));
          col_g.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("glycemie"));
           col_pd.setCellValueFactory(new PropertyValueFactory<Suivi,String>("petitDej"));
            col_r.setCellValueFactory(new PropertyValueFactory<Suivi,String>("repas"));
             col_d.setCellValueFactory(new PropertyValueFactory<Suivi,String>("diner"));
              col_n.setCellValueFactory(new PropertyValueFactory<Suivi,String>("notes"));
              
              listP = us.findAllFX();
    table_s.setItems(listP);*/
        UpTableS() ;
         labelUser.setText("Nom:    "+userservice.usercon.getNom()+"      Prenom :    "+userservice.usercon.getPrenom()+"");
    }    

    @FXML
    private void rechercheparidU(KeyEvent event) {
         us = new suiviservice();
     col_ids.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idSuivi"));
       col_idu.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idUser"));
        col_ds.setCellValueFactory(new PropertyValueFactory<Suivi,String>("dateSuivi"));
         col_t.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("taille"));
          col_p.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("poid"));
          col_g.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("glycemie"));
           col_pd.setCellValueFactory(new PropertyValueFactory<Suivi,String>("petitDej"));
            col_r.setCellValueFactory(new PropertyValueFactory<Suivi,String>("repas"));
             col_d.setCellValueFactory(new PropertyValueFactory<Suivi,String>("diner"));
              col_n.setCellValueFactory(new PropertyValueFactory<Suivi,String>("notes"));
    int d = Integer.parseInt(rechsid.getText());
    listR= us.findParIdFX(d);
    table_s.setItems(listR);  
    //rechsid.getText().equalsIgnoreCase("") 
    if(rechsid.getText().equalsIgnoreCase("")){
        UpTableS();
    }
    }

    @FXML
    private void SuppS(ActionEvent event) {
         int d=  Integer.parseInt(col_ids.getCellData(index).toString());
       us = new suiviservice();
      us.deletePersonne(d);
      UpTableS();
    }

    @FXML
    private void getDataS(MouseEvent event) {
          index =table_s.getSelectionModel().getSelectedIndex();
    if (index<= -1){
        return;
    }
    tf_idC.setText(col_ids.getCellData(index).toString());
    tf_nom.setText(col_idu.getCellData(index).toString());
       tf_prenom.setText(col_ds.getCellData(index).toString());
          tf_mailC.setText(col_t.getCellData(index).toString());
             tf_pwC.setText(col_p.getCellData(index).toString());
                tf_adC.setText(col_g.getCellData(index).toString());
                   tf_numC.setText(col_pd.getCellData(index).toString());
                      tf_pC.setText(col_r.getCellData(index).toString());
                        tf_dateC.setText(col_d.getCellData(index).toString());
                          tf_Ref.setText(col_n.getCellData(index).toString());
                            
    }

    @FXML
    private void modS(ActionEvent event) {
    //   Suivi p =new Suivi(Integer.parseInt(tf_idC.getText()),Integer.parseInt(tf_nom.getText()),tf_prenom.getText(),Float.parseFloat( tf_mailC.getText()),Float.parseFloat( tf_pwC.getText()),Float.parseFloat(tf_adC.getText()),tf_numC.getText(),tf_pC.getText(),tf_dateC.getText(),tf_Ref.getText());
         us = new suiviservice();
   //    us. upDateS(p);
       UpTableS();
    }

    @FXML
    private void ajouS(ActionEvent event) {
       
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterSuivi.fxml"));
            Parent root = loader.load();
             AjouterSuiviController gu = loader.getController();
              
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void exitS(ActionEvent event) {
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
    
    
    

