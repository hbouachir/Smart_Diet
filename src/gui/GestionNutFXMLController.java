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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
public class GestionNutFXMLController implements Initializable {

    @FXML
    private Button b_gc;
    @FXML
    private AnchorPane pane_gn;
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
    
    @FXML
    private TextField tf_rech;
    @FXML
    private AnchorPane pane_ajouClientNu;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_mail;
    @FXML
    private TextField tf_pw;
    @FXML
    private TextField tf_ad;
    @FXML
    private TextField tf_m;
    @FXML
    private TextField tf_num;
    @FXML
    private TextField tf_civ;
    @FXML
    private TextField tf_ref;
    @FXML
    private ComboBox  type_ajouNu;
    @FXML
    private DatePicker dateN;
    @FXML
    private TextField tf_nom1;
    @FXML
    private TextField tf_dN1;
    @FXML
    private TextField tf_prenom1;
    @FXML
    private TextField tf_mail1;
    @FXML
    private TextField tf_pw1;
    @FXML
    private TextField tf_ad1;
    @FXML
    private TextField tf_m1;
    @FXML
    private TextField tf_num1;
    @FXML
    private TextField tf_civ1;
    @FXML
    private TextField tf_ref1;
    @FXML
    private TextField tf_rolM;
    @FXML
    private AnchorPane pane_suiviNu;
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
    @FXML
    private TableColumn<Suivi, Integer> com_ids;
    @FXML
    private TableColumn<Suivi, Integer> col_idUser;
    private TextField rechSSuser;
    @FXML
    private AnchorPane pane_ProfilNu;
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
    private TableColumn<Personne, Integer> col_numC;

    @FXML
    private TableColumn<Personne, String> col_civC;
    
   
    @FXML
    private TextField tf_prenom2;
    @FXML
    private TextField tf_adC;
    @FXML
    private TextField tf_pwC;
    @FXML
    private TextField tf_numC;
    @FXML
    private TextField tf_civC;
    @FXML
    private TextField tf_mailC;
    @FXML
    private TextField tf_dateC;
    @FXML
    private TextField tf_nom2;
    
     ObservableList<Suivi> listSN;
     ObservableList<Suivi> listRS;
   ObservableList<Personne>   listRNU;
     private suiviservice ss;
      private userservice us;
    ObservableList<Personne> listCNU;
     ObservableList<Personne> listNu;
    Connection conn= null;
    ResultSet rs = null;
    Statement pst =null;
int index =-1;
    @FXML
    private AnchorPane pane_modNu;
    @FXML
    private TextField rechSSNu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         type_ajouNu.getItems().addAll("client");
    }   
     public void UpTable1() {
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
    
    listCNU = us.findPersonneParNFXNu("client");
    table_users.setItems(listCNU);
    }
       public void UpTable2() {
        us = new userservice();
    //col_idC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
       col_nomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
        col_prenomC.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
         col_dateC.setCellValueFactory(new PropertyValueFactory<Personne,String>("dateNaissance"));
          col_mailC.setCellValueFactory(new PropertyValueFactory<Personne,String>("mail"));
          col_pwC.setCellValueFactory(new PropertyValueFactory<Personne,String>("password"));
           col_adC.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
         //   col_Ref.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("referenceClient"));
             col_numC.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("numero"));
              col_civC.setCellValueFactory(new PropertyValueFactory<Personne,String>("civilite"));
            //   col_pC.setCellValueFactory(new PropertyValueFactory<Personne,String>("privilege"));
            //    col_mC.setCellValueFactory(new PropertyValueFactory<Personne,String>("maladie"));
    
   listNu = us. findAllFX();
 //    listC = (ObservableList<Personne>) p;
    table_client.setItems(listNu);
   // table_client.setItems(p);
  // table_client.setUserData(p);
    }
       public void UpTable3() {
        ss = new suiviservice();
     com_ids.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idSuivi"));
     
        col_ds.setCellValueFactory(new PropertyValueFactory<Suivi,String>("dateSuivi"));
         col_t.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("taille"));
          col_p.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("poid"));
          col_g.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("glycemie"));
           col_pd.setCellValueFactory(new PropertyValueFactory<Suivi,String>("petitDej"));
            col_r.setCellValueFactory(new PropertyValueFactory<Suivi,String>("repas"));
             col_d.setCellValueFactory(new PropertyValueFactory<Suivi,String>("diner"));
              col_n.setCellValueFactory(new PropertyValueFactory<Suivi,String>("notes"));
               col_idUser.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idUser"));
              
              listSN = ss.findAllFX();
           // listS = us.findParIdFXT();
    table_s.setItems(listSN);}
    @FXML
    private void consulterProfilNu(ActionEvent event) {
        UpTable2();
        pane_ProfilNu.setVisible(true);
        pane_gn.setVisible(false);
        pane_suiviNu.setVisible(false);
        
    }

    @FXML
    private void consulterSNu(ActionEvent event) {
          UpTable3();
        pane_ProfilNu.setVisible(false);
        pane_gn.setVisible(false);
        pane_suiviNu.setVisible(true);
    }

    @FXML
    private void ConsulterClientNu(ActionEvent event) {
        
        
         UpTable1();
        pane_gn.setVisible(true);
         pane_ProfilNu.setVisible(false);
        pane_suiviNu.setVisible(false);
    }

    @FXML
    private void getDataNu(MouseEvent event) {
         index =table_users.getSelectionModel().getSelectedIndex();
    if (index<= -1){
        return;
    }
    // tf_.setText(col_id.getCellData(index).toString());
    tf_nom1.setText(col_nom.getCellData(index).toString());
       tf_prenom1.setText(col_prenom.getCellData(index).toString());
        tf_dN1.setText(col_date.getCellData(index).toString());
          tf_mail1.setText(col_email.getCellData(index).toString());
             tf_pw1.setText(col_pw.getCellData(index).toString());
                tf_ad1.setText(col_addresse.getCellData(index).toString());
                // tf_ref1.setText(col_ref.getCellData(index).toString());
                   tf_num1.setText(col_num.getCellData(index).toString());
                 //  tf_dN1.setText(col_date.getCellData(index).toString());
                 //    tf_ref1.setText(col_ref.getCellData(index).toString());
                       tf_civ1.setText(col_civ.getCellData(index).toString());
                       //  tf_m1.setText(col_maladie.getCellData(index).toString());
                   
                      tf_rolM.setText(col_rol.getCellData(index).toString());
                      pane_modNu.setVisible(true);
        pane_gn.setVisible(false);
    
    }

    @FXML
    private void rechUser(KeyEvent event) {
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
    int n = Integer.parseInt(tf_rech.getText());
    listRNU = us.findClientParRefNu(n);
    table_users.setItems(listRNU);  
   
    }

    @FXML
    private void ajouterClient(ActionEvent event) {
         pane_ajouClientNu.setVisible(true);
        pane_gn.setVisible(false);
    }

    @FXML
    private void retourgcN(ActionEvent event) {
         //pane_ajouClientNu.setVisible(true);
        pane_gn.setVisible(false);
    }

    @FXML
    private void exitAdmin(ActionEvent event) {
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
    private void ajouterClientNuValide(ActionEvent event) {
          Personne p1 =new Personne(tf_nom.getText(), tf_prenom.getText(),dateN.getValue().toString(), tf_mail.getText(),tf_pw.getText(),tf_ad.getText(),Integer.parseInt(tf_ref.getText()),Integer.parseInt(tf_num.getText()),tf_civ.getText(),type_ajouNu.getValue().toString(),tf_m.getText());
         us = new userservice();
       us.insertUser(p1);
      
       pane_ajouClientNu.setVisible(false);
         
       UpTable1();
        pane_gn.setVisible(true);
       
    }

    @FXML
    private void retourajouCNu(ActionEvent event) {
        pane_ajouClientNu.setVisible(false);
         
       UpTable1();
        pane_gn.setVisible(true);
    }

    @FXML
    private void ModClientNuValide(ActionEvent event) {
          us = new userservice();
         int idd = Integer.parseInt(col_id.getCellData(index).toString());
     Personne p =new Personne(tf_nom1.getText(), tf_prenom1.getText(),tf_dN1.getText(), tf_mail1.getText(),tf_pw1.getText(),tf_ad1.getText(),Integer.parseInt(tf_ref1.getText()),Integer.parseInt(tf_num1.getText()),tf_civ1.getText(),tf_rolM.getText(),tf_m1.getText());
  
        // us = new userservice();
       us.upDateu(p,idd);
       pane_modNu.setVisible(false);
         UpTable1();
        pane_gn.setVisible(true);
    }

    @FXML
    private void retourModClientNu(ActionEvent event) {
        pane_modNu.setVisible(false);
         UpTable1();
        pane_gn.setVisible(true);
    }

    @FXML
    private void supClientNuValide(ActionEvent event) {
         int idd = Integer.parseInt(col_id.getCellData(index).toString());
       us = new userservice();
      us.deletePersonne(idd);
      pane_modNu.setVisible(false);
         UpTable1();
        pane_gn.setVisible(true);
    }

    @FXML
    private void getDataS(MouseEvent event) {
    }

    @FXML
    private void supSNu(ActionEvent event) {
         int idd = Integer.parseInt(com_ids.getCellData(index).toString());
       ss = new suiviservice();
      ss.deletePersonne(idd);
     
         UpTable3();
    
    }

    @FXML
    private void retourSNu(ActionEvent event) {
         pane_suiviNu.setVisible(false);
        
        
    }

    @FXML
    private void rechSNu(KeyEvent event) {
           ss = new suiviservice();
     com_ids.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idSuivi"));
      
        col_ds.setCellValueFactory(new PropertyValueFactory<Suivi,String>("dateSuivi"));
         col_t.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("taille"));
          col_p.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("poid"));
          col_g.setCellValueFactory(new PropertyValueFactory<Suivi,Float>("glycemie"));
           col_pd.setCellValueFactory(new PropertyValueFactory<Suivi,String>("petitDej"));
            col_r.setCellValueFactory(new PropertyValueFactory<Suivi,String>("repas"));
             col_d.setCellValueFactory(new PropertyValueFactory<Suivi,String>("diner"));
              col_n.setCellValueFactory(new PropertyValueFactory<Suivi,String>("notes"));
               col_idUser.setCellValueFactory(new PropertyValueFactory<Suivi,Integer>("idUser"));
    int d = Integer.parseInt(rechSSNu.getText());
    listRS= ss.findParIdFX(d);
    table_s.setItems(listRS);  
    //rechsid.getText().equalsIgnoreCase("") 
    if(rechSSNu.getText().equalsIgnoreCase("")){
        UpTable3();
    }
        
    }

    @FXML
    private void getData(MouseEvent event) {
            index =table_client.getSelectionModel().getSelectedIndex();
    if (index<= -1){
        return;
    }
   //  tf_idC.setText(col_idC.getCellData(index).toString());
    tf_nom2.setText(col_nomC.getCellData(index).toString());
       tf_prenom2.setText(col_prenomC.getCellData(index).toString());
           tf_dateC.setText(col_dateC.getCellData(index).toString());
          tf_mailC.setText(col_mailC.getCellData(index).toString());
             tf_pwC.setText(col_pwC.getCellData(index).toString());
                tf_adC.setText(col_adC.getCellData(index).toString());
                   tf_numC.setText(col_numC.getCellData(index).toString());
                 //     tf_pC.setText(col_pC.getCellData(index).toString());
                    
                     //  dataNClient.setDayCellFactory(col_dateC.getCellData(index));
                       //   tf_Ref.setText(col_Ref.getCellData(index).toString());
                            tf_civC.setText(col_civC.getCellData(index).toString());
                         //     tf_mC.setText(col_mC.getCellData(index).toString());
    }

    @FXML
    private void UpNu(ActionEvent event) {
      
       //  Personne p1 =new Personne(tf_nom2.getText(), tf_prenom2.getText(),tf_dateC.getText(), tf_mailC.getText(),tf_pwC.getText(),tf_adC.getText(),Integer.parseInt(tf_numC.getText()),tf_civC.getText());
        Personne p1 =new Personne (tf_nom2.getText(), tf_prenom2.getText(),tf_dateC.getText(), tf_mailC.getText(),tf_pwC.getText(),tf_adC.getText(),Integer.parseInt(tf_numC.getText()),tf_civC.getText());
        us = new userservice();
        //int idd = userservice.usercon.getId();
         us.upDatNuC(p1);
    
         UpTable2();
     
    }

    @FXML
    private void retourProfilNu(ActionEvent event) {
         pane_ProfilNu.setVisible(false);
    }

    @FXML
    private void refrechSN(ActionEvent event) {
        UpTable3();
    }

    @FXML
    private void refCNu(ActionEvent event) {
         UpTable1();
    }
    
}
