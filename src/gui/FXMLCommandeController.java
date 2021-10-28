/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entites.commande;
import entites.lignecommande;
import gui.AfficherCommandesAdminFXMLController;
import gui.AfficherCommandesAdminFXMLController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.commandeService;
import service.lignecommandeService;
import utils.Alert_2;
import utils.ConfirmationAlertExample;
import utils.Twilio;

/**
 * FXML Controller class
 *
 * @author HAMZA
 */
public class FXMLCommandeController implements Initializable {

    @FXML
    private Button commande;
    @FXML
    private TextField rech;
    @FXML
    private TableView<commande> TableCommande;
    @FXML
    private TableColumn<commande, Integer> idCLlient;
    @FXML
    private TableColumn<commande,Integer> montantPanier;

    @FXML
    private TableColumn<commande,Integer> idCommande;

    /**
     * Initializes the controller class.
     */
    
        private int index = -1;
        
        private int a,b,c;
    @FXML
    private Button supper;
    @FXML
    private Button idCherche;
    @FXML
    private Button pdf;
    @FXML
    private Button mail;
    @FXML
    private TextField tfClient;
    @FXML
    private TextField tfMontant;
    @FXML
    private Button ligneCommande;
    private AnchorPane testAnchore;
    @FXML
    private AnchorPane anchoreCommande;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           commandeService cs = new commandeService();
     

    idCLlient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
    montantPanier.setCellValueFactory(new PropertyValueFactory<>("montantPanier"));
     idCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));

        TableCommande.setItems(cs.findAll());
    }    

    @FXML
    private void commandePress(ActionEvent event) {
               commandeService cs = new commandeService();
     

    idCLlient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
    montantPanier.setCellValueFactory(new PropertyValueFactory<>("montantPanier"));
     idCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));

        TableCommande.setItems(cs.findAll());
    }


   
    @FXML
    private void selectCommande(MouseEvent event) {
           index = TableCommande.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }


        a=idCLlient.getCellData(index);
        System.out.println(  montantPanier.getCellData(index));
        
        b=  montantPanier.getCellData(index);
        
        c=idCommande.getCellData(index);
        //tfClient.setText(String.valueOf(a));
        tfMontant.setText(String.valueOf(b));
    }


    @FXML
    private void onClickId(MouseEvent event) {
        String ch=rech.getText();
        int x=Integer.parseInt(ch);
        
        commandeService cs = new commandeService();
     if (ch.isEmpty()){
        
    idCLlient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
    montantPanier.setCellValueFactory(new PropertyValueFactory<>("montantPanier"));
     idCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));

        TableCommande.setItems(cs.findAll());
        TableCommande.refresh();
     } else {
         idCLlient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
    montantPanier.setCellValueFactory(new PropertyValueFactory<>("montantPanier"));
     idCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));

        TableCommande.setItems(cs.findByUserId(x));
        TableCommande.refresh();
     
     
     
     
     
     }
        
    }

    @FXML
    private void onClickVoirCommande(MouseEvent event) throws IOException {
        lignecommandeService lcs = new lignecommandeService();
    
        
        
        
        FXMLLoader Loader=new FXMLLoader(getClass().getResource("/afficheAdmin.fxml"));
        Parent parent=Loader.load();
    
//        AfficherCommandesAdminFXMLController acc=Loader.getController();
//        acc.setIdUserLabel(String.valueOf(a));
//        acc.tableCommandeLigne.setItems(lcs.findByUserId(c));
        
        Scene scene = new Scene(parent);
    
        Stage st1= new Stage();
        st1.setScene(scene);
        st1.show();
    
    }

    @FXML
    private void SupprimerCommande(MouseEvent event) {
           Alert_2.display("supression commande", "etes vous sure de supprimer la commande?");
         commandeService cs = new commandeService();
             cs.delete(c);
             TableCommande.setItems(cs.findAll());
             a=0;
             b=0;
             c=0;
              tfClient.setText("");
        tfMontant.setText("");
    }

    @FXML
    private void pdfExtract(MouseEvent event) throws FileNotFoundException, DocumentException {
        exportPDF();
    }
    

    @FXML
    private void mailing(MouseEvent event) {
        Twilio.send("hamza.bouachir@esprit.tn","******","bouachirhamza@gmail.com","commande validé","votre commande est validé");
        
    }

    public void exportPDF() throws FileNotFoundException,DocumentException {

 //FileNotFoundException, DocumentException 
              Document doc = new Document();
                FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF files (.pdf)", ".pdf"),
                new FileChooser.ExtensionFilter("HTML files (.html)", ".html")
        );
        File saveFile = fileChooser.showSaveDialog(TableCommande.getScene().getWindow());

      //  file.save(saveFile.getAbsolutePath());

        PdfWriter.getInstance(doc,new FileOutputStream(saveFile.getAbsolutePath()));
        doc.open();
        System.out.println(doc.getHtmlStyleClass());
        commandeService cas= new commandeService();
        doc.add(new Paragraph("===================LISTE DES COMMANDES=================="));

         for ( commande p :cas.findAll())
         {
        doc.add(new Paragraph("No client :"+p.getIdClient()));
        doc.add(new Paragraph("No commande :"+p.getIdCommande()));
        doc.add(new Paragraph("Montant panier  :"+p.getMontantPanier()));
       
        doc.add(new Paragraph("=========================================="));
         }

        doc.close();

    }




//    public void mailing(String adr) {
//
//        //authentication info
//        final String username = "test.esprit2021@gmail.com";
//        final String password = "test2021";
//        String fromEmail = "test.nom2020@gmail.com";
//
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(properties,new Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username,password);
//            }
//
//});
//        //Start our mail message
//        MimeMessage msg = new MimeMessage(session);
//        try {
//            msg.setFrom(new InternetAddress(fromEmail));
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(adr)); 
//            msg.setSubject("Commande");
//            msg.setText("Votre Commande est Passé avec Succées !");
//             
//            Transport.send(msg);
//            
//        }catch (MessagingException e) {
//            e.printStackTrace();
//        }
        // TODO Auto-generated catch block

    @FXML
    private void onAjouterCommande(ActionEvent event) {
        if (tfClient.getText().isEmpty() ) Alert_2.display("manque de donnée", "N° client manquant");
        else 
            if (tfMontant.getText()=="") Alert_2.display("manque de donnée", "N° client manquant");
            else{
        commandeService cs=new commandeService();
        int x=Integer.parseInt(tfClient.getText());
//        int y=Integer.parseInt(tfMontant.getText());
        commande c=new commande(x,0);
        cs.insert(c);
        TableCommande.setItems(cs.findAll());}
    }

    @FXML
    private void ligneCommandePress(ActionEvent event) throws IOException, SQLException   {
       
                    lignecommandeService cls=new  lignecommandeService();
//        ObservableList<lignecommande> listc=cls.findAll();

    AnchorPane pane=FXMLLoader.load(getClass().getResource("afficherCommandesAdminFXML.fxml"));
        anchoreCommande.getChildren().setAll(pane);






        
        

//FXMLLoader Loader = new FXMLLoader(getClass().getResource("afficherCommandesAdminFXML.fxml"));
//Parent root=(Parent) Loader.load();
//AfficherCommandesAdminFXMLController acc=Loader.getController();
//float prixTotal =cls.calcul();
//    acc.setIdPrixLabel(String.valueOf(prixTotal));
////          ResultSet rs=st.executeQuery(req);
////          float x=rs.getFloat("tot");
////          System.out.println(x);
////
////
////acc.setIdPrixLabel(rs.getString("tot"));
//Stage stage = new Stage();
//stage.setTitle("Gestion des ligne de commandes");
//Scene sceneL = new Scene(root);
//stage.setScene(sceneL);
//stage.show();



       
        
        
    }

    @FXML
    private void onModifierCommande(ActionEvent event) {
        commandeService cs=new commandeService();
        commande com=new commande(a, Integer.parseInt(tfMontant.getText()));
        cs.update(com, c);
        TableCommande.setItems(cs.findAll());
       
    }

    
    
    
 


    }
   
   
    
 
     
    