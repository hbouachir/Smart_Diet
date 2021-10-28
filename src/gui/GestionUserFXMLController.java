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
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.suiviservice;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author cyrine belhssan
 */
public class GestionUserFXMLController implements Initializable {

    @FXML
    private AnchorPane pane_gu;
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
 private TextField tf_recherche;
    
    @FXML
     private TextField tf_mail;

    private TextField tf_mal;

    @FXML
    private TextField tf_nom;


    @FXML
    private TextField tf_prenom;

    @FXML
    private TextField tf_pw;



    private userservice us;
    ObservableList<Personne> listP;
     ObservableList<Personne> listR;
    Connection conn= null;
    ResultSet rs = null;
    Statement pst =null;
    private TextField tf_p;
    @FXML
    private TextField tf_ad;
    @FXML
    private TextField tf_num;
    private Label l_message;
    @FXML
    private Button b_gc;

    ObservableList<Personne> listU;
    
    int index =1;
    @FXML
    private AnchorPane pane_ajouUser;
    @FXML
    private TextField tf_m;
    @FXML
    private TextField tf_civ;
    @FXML
    private TextField tf_ref;
    @FXML
    private ComboBox type_ajouU;
    @FXML
    private TextField tf_dN;
    @FXML
    private DatePicker dateN;
    @FXML
    private AnchorPane pane_modUA;
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
    private TextField tf_nom2;
    @FXML
    private TextField tf_dN2;
    @FXML
    private TextField tf_prenom2;
    @FXML
    private TextField tf_mail2;
    @FXML
    private TextField tf_pw2;
    @FXML
    private TextField tf_ad2;
    
    @FXML
    private TextField tf_num2;
    @FXML
    private TextField tf_civ2;
   
    @FXML
    private TextField tf_rolM2;
    @FXML
    private AnchorPane pane_modUANC;
    @FXML
    private AnchorPane pane_ajouUser1;
    @FXML
    private TextField tf_nom3;
    @FXML
    private TextField tf_dN3;
    @FXML
    private TextField tf_prenom3;
    @FXML
    private TextField tf_mail3;
    @FXML
    private TextField tf_pw3;
    @FXML
    private TextField tf_ad3;
    @FXML
    private TextField tf_num3;
    @FXML
    private TextField tf_civ3;
    @FXML
    private ComboBox type_ajouNC;
    @FXML
    private DatePicker dateN1;
    @FXML
    private AnchorPane pane_suiviuser;
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
     ObservableList<Suivi> listSU;
       ObservableList<Suivi> listRS;
    
  private suiviservice ss;
    @FXML
    private TableColumn<Suivi, Integer> col_idUser;
    @FXML
    private TextField rechSSuser;
  /////
   public void UpTableS() {
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
              
              listSU = ss.findAllFX();
           // listS = us.findParIdFXT();
    table_s.setItems(listSU);
    }
  /////
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
    
    listU = us.findAllFXAdmin();
    table_users.setItems(listU);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         type_ajouU.getItems().addAll("client");
         type_ajouNC.getItems().addAll("admin","nutritionniste","coache","medecin");
    }    

    @FXML
    private void ajouterUser(ActionEvent event) {
        pane_gu.setVisible(false);
        pane_ajouUser.setVisible(true);
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
    private void consulterUser(ActionEvent event) {
           pane_suiviuser.setVisible(false);
          UpTable();
        pane_gu.setVisible(true);
      //  pane_suiviuser.setVisible(false);
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
    String n = tf_recherche.getText();
    listR = us.findPersonneParNFX(n);
    table_users.setItems(listR);  
    if(  tf_recherche.getText().equalsIgnoreCase("")){
        UpTable();
    }
    }

    @FXML
    private void ajouterUserValide(ActionEvent event) {
      //  us = new userservice();
    /*    String role= type_ajouU.getValue().toString();
        System.out.println("type;"+role+"");*/
    //tf_dN.getText()
       // LocalDate bdate = dateN.getValue();
         Personne p1 =new Personne(tf_nom.getText(), tf_prenom.getText(),dateN.getValue().toString(), tf_mail.getText(),tf_pw.getText(),tf_ad.getText(),Integer.parseInt(tf_ref.getText()),Integer.parseInt(tf_num.getText()),tf_civ.getText(),type_ajouU.getValue().toString(),tf_m.getText());
         us = new userservice();
       us.insertUser(p1);
      
       pane_ajouUser.setVisible(false);
         
       UpTable();
        pane_gu.setVisible(true);
        // JOptionPane.showMessageDialog(null, "Client  "+p1.getPrenom()+"         "+p1.getNom()+" ajouter!!!!!");
    
     // l_message.setText("user ajouter...");
    }

    @FXML
    private void retourAfficheUser(ActionEvent event) {
        pane_ajouUser.setVisible(false);
         UpTable();
        pane_gu.setVisible(true);
    }

    @FXML
    private void ModUserValide(ActionEvent event) {
         us = new userservice();
         int idd = Integer.parseInt(col_id.getCellData(index).toString());
     Personne p =new Personne(tf_nom1.getText(), tf_prenom1.getText(),tf_dN1.getText(), tf_mail1.getText(),tf_pw1.getText(),tf_ad1.getText(),Integer.parseInt(tf_ref1.getText()),Integer.parseInt(tf_num1.getText()),tf_civ1.getText(),tf_rolM.getText(),tf_m1.getText());
  
        // us = new userservice();
       us.upDateu(p,idd);
       pane_modUA.setVisible(false);
         UpTable();
        pane_gu.setVisible(true);
     
    }

    @FXML
    private void supUserValide(ActionEvent event) {
         int idd = Integer.parseInt(col_id.getCellData(index).toString());
       us = new userservice();
      us.deletePersonne(idd);
      pane_modUA.setVisible(false);
         UpTable();
        pane_gu.setVisible(true);
    }

    @FXML
    private void getDataUA(MouseEvent event) {
            index =table_users.getSelectionModel().getSelectedIndex();
    if (index<= -1){
        return;
    }else if(col_rol.getCellData(index).toString().equalsIgnoreCase("client")){
    // tf_.setText(col_id.getCellData(index).toString());
    tf_nom1.setText(col_nom.getCellData(index).toString());
       tf_prenom1.setText(col_prenom.getCellData(index).toString());
        tf_dN1.setText(col_date.getCellData(index).toString());
          tf_mail1.setText(col_email.getCellData(index).toString());
             tf_pw1.setText(col_pw.getCellData(index).toString());
                tf_ad1.setText(col_addresse.getCellData(index).toString());
                 tf_ref1.setText(col_ref.getCellData(index).toString());
                   tf_num1.setText(col_num.getCellData(index).toString());
                 //  tf_dN1.setText(col_date.getCellData(index).toString());
                 //    tf_ref1.setText(col_ref.getCellData(index).toString());
                       tf_civ1.setText(col_civ.getCellData(index).toString());
                         tf_m1.setText(col_maladie.getCellData(index).toString());
                   
                      tf_rolM.setText(col_rol.getCellData(index).toString());
                        pane_modUA.setVisible(true);
        pane_gu.setVisible(false);
    }else //if((col_rol.getCellData(index).toString().equalsIgnoreCase("nutrisionniste"))||(col_rol.getCellData(index).toString().equalsIgnoreCase("coache"))){
    // tf_.setText(col_id.getCellData(index).toString());
    {   tf_nom2.setText(col_nom.getCellData(index).toString());
       tf_prenom2.setText(col_prenom.getCellData(index).toString());
       tf_dN2.setText(col_date.getCellData(index).toString());
          tf_mail2.setText(col_email.getCellData(index).toString());
             tf_pw2.setText(col_pw.getCellData(index).toString());
                tf_ad2.setText(col_addresse.getCellData(index).toString());
                 //tf_ref1.setText(col_ref.getCellData(index).toString());
                   tf_num2.setText(col_num.getCellData(index).toString());
                 //  tf_dN1.setText(col_date.getCellData(index).toString());
                 //    tf_ref1.setText(col_ref.getCellData(index).toString());
                       tf_civ2.setText(col_civ.getCellData(index).toString());
                        // tf_m1.setText(col_maladie.getCellData(index).toString());
                   
                      tf_rolM2.setText(col_rol.getCellData(index).toString());
                        pane_modUANC.setVisible(true);
        pane_gu.setVisible(false);
    }
    
    }

    @FXML
    private void retourModUser(ActionEvent event) {
         pane_modUA.setVisible(false);
         UpTable();
        pane_gu.setVisible(true);
    }

    @FXML
    private void ModNutValide(ActionEvent event) {
         us = new userservice();
         int idd = Integer.parseInt(col_id.getCellData(index).toString());
     Personne p =new Personne(tf_nom2.getText(), tf_prenom2.getText(),tf_dN2.getText(), tf_mail2.getText(),tf_pw2.getText(),tf_ad2.getText(),Integer.parseInt(tf_num2.getText()),tf_civ2.getText(),tf_rolM2.getText());
  
        // us = new userservice();
       us.upDatenc(p,idd);
       pane_modUANC.setVisible(false);
         UpTable();
        pane_gu.setVisible(true);
    }

    @FXML
    private void retourModNut(ActionEvent event) {
        pane_modUANC.setVisible(false);
         UpTable();
        pane_gu.setVisible(true);
    }

    @FXML
    private void supNutValide(ActionEvent event) {
         int idd = Integer.parseInt(col_id.getCellData(index).toString());
       us = new userservice();
      us.deletePersonne(idd);
      pane_modUANC.setVisible(false);
         UpTable();
        pane_gu.setVisible(true);
    }

    @FXML
    private void ajouNC(ActionEvent event) {
        pane_ajouUser1.setVisible(true);
       
        pane_gu.setVisible(false);
    }

    @FXML
    private void ajouterNCValide(ActionEvent event) {
         Personne p1 =new Personne(tf_nom3.getText(), tf_prenom3.getText(),dateN1.getValue().toString(), tf_mail3.getText(),tf_pw3.getText(),tf_ad3.getText(),Integer.parseInt(tf_num3.getText()),tf_civ3.getText(),type_ajouNC.getValue().toString());
         us = new userservice();
       us.insertAdmin(p1);
       // JOptionPane.showMessageDialog(null, "user  "+p1.getPrenom()+"         "+p1.getNom()+" ajouter!!!!!");
       pane_ajouUser1.setVisible(false);
         
       UpTable();
        pane_gu.setVisible(true);
        // JOptionPane.showMessageDialog(null, "user  "+p1.getPrenom()+"         "+p1.getNom()+" ajouter!!!!!");
    }

    @FXML
    private void retourajouNCUser(ActionEvent event) {
         pane_ajouUser1.setVisible(false);
         UpTable();
        pane_gu.setVisible(true);
    }

    @FXML
    private void consulterSuser(ActionEvent event) {
          pane_gu.setVisible(false);
        UpTableS();
        pane_suiviuser.setVisible(true);
         
      
    }

    @FXML
    private void getDataS(MouseEvent event) {
        index =table_s.getSelectionModel().getSelectedIndex();
    if (index<= -1){
        return;
    }
   // tf_idC.setText(col_ids.getCellData(index).toString());
   // tf_nom.setText(col_idu.getCellData(index).toString());
      /*Float t= col_t.getCellData(index).toString();
          tf_po.setText(col_p.getCellData(index).toString());
             tf_g.setText(col_g.getCellData(index).toString());
                tf_pd.setText(col_pd.getCellData(index).toString());
                   tf_r.setText(col_r.getCellData(index).toString());
                    //  tf_pC.setText(col_r.getCellData(index).toString());
                        tf_di.setText(col_d.getCellData(index).toString());
                          tf_n.setText(col_n.getCellData(index).toString());
                     pane_mods.setVisible(true);
        pane_suivi.setVisible(false);   */   
    }

    @FXML
    private void supuserS(ActionEvent event) {
            int idd = Integer.parseInt(com_ids.getCellData(index).toString());
       ss = new suiviservice();
      ss.deletePersonne(idd);
     
         UpTableS();
        
    }

    @FXML
    private void retourSuser(ActionEvent event) {
        pane_suiviuser.setVisible(false);
       //   UpTableS();
       // pane_gu.setVisible(true);
    }

    @FXML
    private void rechSuser(KeyEvent event) {
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
    int d = Integer.parseInt(rechSSuser.getText());
    listRS= ss.findParIdFX(d);
    table_s.setItems(listRS);  
    //rechsid.getText().equalsIgnoreCase("") 
    if(rechSSuser.getText().equalsIgnoreCase("")){
        UpTableS();
    }
    }
    
}
