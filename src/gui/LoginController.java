/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Personne;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author cyrine belhssan
 */
public class LoginController implements Initializable {

    @FXML
    private TextField login_mail;
    @FXML
    private TextField login_pw;
private userservice us;
    @FXML
    private ComboBox type;
    @FXML
    private AnchorPane pane_login;
    @FXML
    private AnchorPane pane_singnup;
    @FXML
    private ComboBox type_insert;
    @FXML
    private TextField nom_insert;
    @FXML
    private TextField prenom_insert;
    @FXML
    private TextField email_insert;
    @FXML
    private TextField pw_insert;
    @FXML
    private Label l;
    @FXML
    private DatePicker dateNClient;
    /**
     * Initializes the controller class.
     */
    @FXML
    public void LoginpaneShow(){
        pane_login.setVisible(true);
         pane_singnup.setVisible(false);
        
    }
    @FXML
    public void SingpaneShow(){
        pane_login.setVisible(false);
         pane_singnup.setVisible(true);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.getItems().addAll("admin","client","nutritionniste","coache","medecin");
        type_insert.getItems().addAll("admin","client");
    }    

    @FXML
    private void loginin(ActionEvent event) throws SQLException {
          us = new userservice();
       String role=   type.getValue().toString();
      
        try{    
            
            if(login_mail.getText().equals("")){
            JOptionPane.showMessageDialog(null, "remplir email!!!!!");
            }else if(login_pw.getText().equals("")){
            JOptionPane.showMessageDialog(null, "remplirr pw!!!!!");
            }else if(type.getValue().toString().equals("")){
            JOptionPane.showMessageDialog(null, "remplirr roleee!!!!!");
            } else if(login_mail.getText().equals("")&&login_pw.getText().equals("")&&type.getValue().toString().equals(""))
            {  JOptionPane.showMessageDialog(null, "remplirr tous!!!!!");}
            
      if( ( us.FindUser(login_mail.getText(), login_pw.getText(),type.getValue().toString())==1) && (role=="client") )
    {
       // if(type.getValue().toString().equalsIgnoreCase("client")){
            
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionClientFXML.fxml"));
            Parent root = loader.load();
             GestionClientFXMLController gu = loader.getController();
             // gu.UpTable();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        }else if ( ( us.FindUser(login_mail.getText(), login_pw.getText(),type.getValue().toString())==1) && (role=="admin")){
        
        
      
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionUserFXML.fxml"));
            Parent root = loader.load();
             GestionUserFXMLController gu = loader.getController();
             // gu.UpTable();
             Scene scene = new Scene(root);
             Stage st1 =new Stage();
             st1.setScene(scene);
             st1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if ( ( us.FindUser(login_mail.getText(), login_pw.getText(),type.getValue().toString())==1) && (role=="nutritionniste")){
        
        
      
         try {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("gestionNutFXML.fxml"));
            Parent root1 = loader1.load();
             GestionNutFXMLController gu = loader1.getController();
             // gu.UpTable();
             Scene scene1 = new Scene(root1);
             Stage st2 =new Stage();
             st2.setScene(scene1);
             st2.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionNutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
  else
          JOptionPane.showMessageDialog(null, "erreur, le nom d'utilisateur ou mot de passe est incorrecte");
      
}catch(Exception e){
     JOptionPane.showMessageDialog(null, "erreur, verifier les donnes");
}
    }
  //)&&(prenom_insert.getText().equals(""))&&(type_insert.getValue().toString().equals(""))&&( email_insert.getText().equals(""))&&(pw_insert.getText().equals(""))
    @FXML
    public void singup(ActionEvent event){
        if(nom_insert.getText().equals(""))
        {
            l.setText("champ nom vide!!!");
           //JOptionPane.showMessageDialog(null, "remplirr tous!!!!!");  
        }else if (prenom_insert.getText().equals(""))
        {
            l.setText("champ prenom vide!!!");
           //JOptionPane.showMessageDialog(null, "remplirr tous!!!!!");  
        }
        else if (email_insert.getText().equals(""))
        {
            l.setText("champ email vide!!!");
           //JOptionPane.showMessageDialog(null, "remplirr tous!!!!!");  
        }else if (pw_insert.getText().equals(""))
        {
            l.setText("champ password vide!!!");
           //JOptionPane.showMessageDialog(null, "remplirr tous!!!!!");  
        }else if (dateNClient.getValue()==null)
        {
            l.setText("champ dateeee vide!!!");
           //JOptionPane.showMessageDialog(null, "remplirr tous!!!!!");  
        }
        
        else{
         // {  JOptionPane.showMessageDialog(null, "remplirr tous!!!!!");}
        Personne p =new Personne(nom_insert.getText(),prenom_insert.getText(),dateNClient.getValue().toString(), email_insert.getText(),pw_insert.getText(),type_insert.getValue().toString());
         us = new userservice();
       us.insertUser(p);
       JOptionPane.showMessageDialog(null, "user  "+p.getPrenom()+" ajouter!!!!!");
        LoginpaneShow();
        }
    }
}
