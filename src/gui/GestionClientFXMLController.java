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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.suiviservice;
import service.userservice;
   
/**
 * FXML Controller class
 *
 * @author cyrine belhssan
 */
public class GestionClientFXMLController implements Initializable {

   @FXML
    private TableView<Personne> table_client;

   
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
    private TableColumn<Personne, String> col_mC;
    @FXML
    private AnchorPane pane_gerer;

    
   
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
    private TextField tf_adC;
    @FXML
    private TextField tf_civC;
    @FXML
    private TextField tf_numC;
    @FXML
    private TextField tf_Ref;
    
    /////////////////////
     @FXML
    private TableView<Suivi> table_s;
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
  private suiviservice ss;
  
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
    ObservableList<Suivi> listS;
    //////////////
    /**
     * Initializes the controller class.
     */
    private userservice us;
  //  ObservableList<Personne> listP;
     ObservableList<Personne> listC;
       ObservableList<Personne> listCM;
      int index=-1;
    Connection conn= null;
    ResultSet rs = null;
    Statement pst =null;
    @FXML
    private AnchorPane pane_suivi;
    @FXML
    private Label labelUser;
    @FXML
    private AnchorPane pane_ajouS;
    @FXML
    private AnchorPane pane_mods;
    @FXML
    private TextField tf_n;
    @FXML
    private TextField tf_di;
    @FXML
    private TextField tf_po;
    @FXML
    private TextField tf_t;
    @FXML
    private TextField tf_r;
    @FXML
    private TextField tf_g;
    @FXML
    private TextField tf_pd;
    @FXML
    private TableColumn<Suivi, Integer> com_ids;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
     public void UpTableCF() {
        us = new userservice();
    //col_idC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_dateC.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_mailC.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pwC.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_adC.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_Ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_numC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civC.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
            //   col_pC.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_mC.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
    
   listC = us. findClientParNFXML();
 //    listC = (ObservableList<Personne>) p;
    table_client.setItems(listC);
   // table_client.setItems(p);
  // table_client.setUserData(p);
    }
       public void UpTableCFM() {
        us = new userservice();
    //col_idC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_dateC.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_mailC.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pwC.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_adC.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
            col_Ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_numC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civC.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
            //   col_pC.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
                col_mC.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
    
   listCM = us.findAllFX() ;
 //    listC = (ObservableList<Personne>) p;
    table_client.setItems(listCM);
  
    }
   @FXML
       private void getData(MouseEvent event) {
          index =table_client.getSelectionModel().getSelectedIndex();
    if (index<= -1){
        return;
    }
   //  tf_idC.setText(col_idC.getCellData(index).toString());
    tf_nom.setText(col_nomC.getCellData(index).toString());
       tf_prenom.setText(col_prenomC.getCellData(index).toString());
           tf_dateC.setText(col_dateC.getCellData(index).toString());
          tf_mailC.setText(col_mailC.getCellData(index).toString());
             tf_pwC.setText(col_pwC.getCellData(index).toString());
                tf_adC.setText(col_adC.getCellData(index).toString());
                   tf_numC.setText(col_numC.getCellData(index).toString());
                 //     tf_pC.setText(col_pC.getCellData(index).toString());
                    
                     //  dataNClient.setDayCellFactory(col_dateC.getCellData(index));
                          tf_Ref.setText(col_Ref.getCellData(index).toString());
                            tf_civC.setText(col_civC.getCellData(index).toString());
                              tf_mC.setText(col_mC.getCellData(index).toString());
    }
    @FXML
    public void GererCompte(){
        UpTableCF();
        pane_gerer.setVisible(true);
        pane_suivi.setVisible(false);
      //   pane_singnup.setVisible(false);
        
    }

    @FXML
    private void UpClient(ActionEvent event) {
        // tf_dateC.getText(),
         Personne p =new Personne(tf_nom.getText(),tf_prenom.getText(),tf_dateC.getText(), tf_mailC.getText(),tf_pwC.getText(),tf_adC.getText(),Integer.parseInt(tf_Ref.getText()),Integer.parseInt(tf_numC.getText()),tf_civC.getText(),tf_mC.getText());
         us = new userservice();
       us.upDatePersonne(p);
       UpTableCFM();
   
    }
   //////////////////////////////////////////////////////
   
             public void UpTableS() {
        ss = new suiviservice();
     com_ids.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idSuivi"));
    //   col_idu.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idUser"));
        col_ds.setCellValueFactory(new PropertyValueFactory<Suivi,String>("dateSuivi"));
         col_t.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("taille"));
          col_p.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("poid"));
          col_g.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("glycemie"));
           col_pd.setCellValueFactory(new PropertyValueFactory<Suivi,String>("petitDej"));
            col_r.setCellValueFactory(new PropertyValueFactory<Suivi,String>("repas"));
             col_d.setCellValueFactory(new PropertyValueFactory<Suivi,String>("diner"));
              col_n.setCellValueFactory(new PropertyValueFactory<Suivi,String>("notes"));
              
              listS = ss.findParIdFXT();
           // listS = us.findParIdFXT();
    table_s.setItems(listS);
    }
    
   @FXML
              public void GererSuivi(){
                  labelUser.setText("Nom:    "+userservice.usercon.getNom()+"      Prenom :    "+userservice.usercon.getPrenom()+"");
        UpTableS();
        pane_gerer.setVisible(false);
        pane_suivi.setVisible(true);
      //   pane_singnup.setVisible(false);
        
    }
   
   @FXML
              private void SuppS(ActionEvent event) {
          int idd = Integer.parseInt(com_ids.getCellData(index).toString());
       ss = new suiviservice();
      ss.deletePersonne(idd);
      pane_mods.setVisible(false);
         UpTableS();
        pane_suivi.setVisible(true);
     
    }
   @FXML
                 private void getDataS(MouseEvent event) {
          index =table_s.getSelectionModel().getSelectedIndex();
    if (index<= -1){
        return;
    }
   // tf_idC.setText(col_ids.getCellData(index).toString());
   // tf_nom.setText(col_idu.getCellData(index).toString());
      tf_t.setText(col_t.getCellData(index).toString());
          tf_po.setText(col_p.getCellData(index).toString());
             tf_g.setText(col_g.getCellData(index).toString());
                tf_pd.setText(col_pd.getCellData(index).toString());
                   tf_r.setText(col_r.getCellData(index).toString());
                    //  tf_pC.setText(col_r.getCellData(index).toString());
                        tf_di.setText(col_d.getCellData(index).toString());
                          tf_n.setText(col_n.getCellData(index).toString());
                     pane_mods.setVisible(true);
        pane_suivi.setVisible(false);        
    }

//////////////////
    @FXML
    private void exitCilent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
             LoginController gu = loader.getController();
             //gu.UpTable();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
             
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bAjouS(ActionEvent event) {
          pane_ajouS.setVisible(true);
        pane_suivi.setVisible(false);
    }

    @FXML
    private void ajouterSu(ActionEvent event) {
        Suivi p =new Suivi( Float.parseFloat(t.getText()),Float.parseFloat( po.getText()),Float.parseFloat(g.getText()),pd.getText(),r.getText(),di.getText(),n.getText());
     ss = new suiviservice();
       ss.insertSuivi(p);
       pane_ajouS.setVisible(false);
        UpTableS();
        pane_suivi.setVisible(true);
    }

    @FXML
    private void bmodS(ActionEvent event) {
    }

    @FXML
    private void modifierSu(ActionEvent event) {
         ss = new suiviservice();
        int idd = Integer.parseInt(com_ids.getCellData(index).toString());
         Suivi s =new Suivi(col_ds.getCellData(index).toString(),Float.parseFloat(tf_t.getText()),Float.parseFloat( tf_po.getText()),Float.parseFloat(tf_g.getText()),tf_pd.getText(),tf_r.getText(),tf_di.getText(),tf_n.getText());
        // ss = new suiviservice();
      ss.upDateSs(s,idd);

        pane_mods.setVisible(false);
         UpTableS();
        pane_suivi.setVisible(true);
    }
}
