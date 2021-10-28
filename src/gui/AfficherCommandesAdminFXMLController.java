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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import utils.MyDB;


import entites.lignecommande;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import service.commandeService;
import service.lignecommandeService;


/**
 * FXML Controller class
 *
 * @author HAMZA
 */

public class AfficherCommandesAdminFXMLController implements Initializable {

    @FXML
    private Button commande;
    @FXML
    private Button Statistique;
    private TableView<lignecommande> tableCommandeLigne;
    @FXML
    private TableColumn<lignecommande, Integer> codeProduit;
    @FXML
    private TableColumn<lignecommande, Integer> quantite;
    @FXML
    private TableColumn<lignecommande, Float> Prix;
    private Label idUserLabel;
    @FXML
    private Label idPrixLabel;
    @FXML
    private TableColumn<lignecommande, Integer> idLigne;
    @FXML
    private TableView<lignecommande> tableLigneCommande;
    @FXML
    private TextField tfcherche;
    @FXML
    private TableColumn<lignecommande, Integer> idCommande;

    /**
     * Initializes the controller class.
     */
         Connection Connection;
          

    private float b;
        private int a,c,d,e;
     private int index=-1;
    @FXML
    private TextField tfCodeProduit;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfCommande;
    @FXML
    private AnchorPane anchoreLigne;
    @FXML
    private AnchorPane anchoreLigne1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lignecommandeService cs = new lignecommandeService();
            
            
            float prixTotal =cs.calcul();
            setIdPrixLabel(String.valueOf(prixTotal));
            
            //int x=Integer.parseInt(idUserLabel.getText());
            
            codeProduit.setCellValueFactory(new PropertyValueFactory<>("codeProduit"));
            quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
            
            idLigne.setCellValueFactory(new PropertyValueFactory<>("idLigne"));
            idCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
            
            tableLigneCommande.setItems(cs.findAll());
            
//                                   
//            String req="SELECT SUM(prix) AS tot from lignecommande";
//
//
//            
//      
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCommandesAdminFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
    }    

    @FXML
    private void commandePress(ActionEvent event) throws IOException {
        
        AnchorPane pane=FXMLLoader.load(getClass().getResource("commandesAdminFXML.fxml"));
        anchoreLigne.getChildren().setAll(pane);
//         lignecommandeService lc = new lignecommandeService();
//     
//
//    codeProduit.setCellValueFactory(new PropertyValueFactory<>("codeProduit"));
//     quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
//     Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//     idLigne.setCellValueFactory(new PropertyValueFactory<>("idLigne"));
//
//        tableCommandeLigne.setItems(lc.findAll());
    }

    @FXML
    private void StatistiquePress(ActionEvent event) {
    }

    public void setIdUserLabel(String idUserLabel) {
        this.idUserLabel.setText(idUserLabel);
    }

//    public void setTableCommandeLigne(TableView<lignecommande> tableCommandeLigne) {
//        this.tableCommandeLigne = tableCommandeLigne;
//    }

    @FXML
    private void afficherLigne(ActionEvent event) {
            lignecommandeService cs = new lignecommandeService();
     

    idLigne.setCellValueFactory(new PropertyValueFactory<>("idLigne"));
    codeProduit.setCellValueFactory(new PropertyValueFactory<>("code Produit"));
     quantite.setCellValueFactory(new PropertyValueFactory<>("Quantité"));
          Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));


        tableLigneCommande.setItems(cs.findAll());
    }

    @FXML
    private void onSelectLigne(MouseEvent event) {
         index = tableLigneCommande.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }


        a=idLigne.getCellData(index);
        b=Prix.getCellData(index);
        c=idCommande.getCellData(index);
        d=codeProduit.getCellData(index);
        e=quantite.getCellData(index);
        tfCodeProduit.setText(String.valueOf(d));
        tfQuantite.setText(String.valueOf(e));
    }

    @FXML
    private void onSupprimerLigneCommande(ActionEvent event) throws SQLException {
        lignecommandeService cs = new lignecommandeService();
             cs.delete(a);
             tableLigneCommande.setItems(cs.findAll());
            
             float prixTotal =cs.calcul();
            setIdPrixLabel(String.valueOf(prixTotal));
            
             commandeService css=new commandeService();
             commande co=new commande();
         co.setIdClient(css.findUserByIdCommande(c));
         System.out.println(co.getIdClient());
         float k=co.getMontantPanier()-b;
         co.setMontantPanier(k);
         css.update( co, c);
            
             a=0;
             b=0;
             c=0;
             d=0;
             e=0;
            
            
            
    }

        

    public void setIdPrixLabel(String idPrixLabel) throws SQLException {

        this.idPrixLabel.setText(idPrixLabel);
    }

    @FXML
    private void onRetourCommande(ActionEvent event) throws SQLException, IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("afficheAdmin.fxml"));
        anchoreLigne1.getChildren().setAll(pane);
    }

    @FXML
    private void onModifierLigne(ActionEvent event) throws SQLException {
        lignecommandeService cs=new lignecommandeService();
        lignecommande com=new lignecommande(c,Integer.parseInt(tfCodeProduit.getText()),Integer.parseInt(tfQuantite.getText()),cs.PrixPro(d)*Integer.parseInt(tfQuantite.getText()));
        System.out.println(com.getCodeProduit());
         System.out.println(com.getIdCommande());
        System.out.println(com.getQuantite());
        System.out.println(com.getIdLigne());
        System.out.println(com.getPrix());
        System.out.println(cs.PrixPro(d)*Integer.parseInt(tfQuantite.getText()));
       
        cs.update(com, a);
        tableLigneCommande.setItems(cs.findAll());
    }

    @FXML
    private void OnAjouterLigne(ActionEvent event) throws SQLException {
        lignecommandeService lcs=new lignecommandeService();

        int s,d,f,g;
        s=Integer.parseInt(tfCommande.getText());
        d=Integer.parseInt(tfCodeProduit.getText());
        f=Integer.parseInt(tfQuantite.getText());
        g=lcs.PrixPro(Integer.parseInt(tfCodeProduit.getText()))*f;
        
        System.out.println(s);
        System.out.println(d);
        System.out.println(f);

        System.out.println(g);
       lignecommande lc = new lignecommande(s,d , f, g);
                lcs.insert(lc);
                tableLigneCommande.setItems(lcs.findAll());
                float h=Float.parseFloat(idPrixLabel.getText())+g;
           
                System.out.println(h);
                setIdPrixLabel(String.valueOf(h));
         commande co= new commande();
         
         commandeService cs=new commandeService();
         co.setIdClient(cs.findUserByIdCommande(s));
         System.out.println(co.getIdClient());
         float w=cs.findMontantByIdCommande(s)+g;
         co.setMontantPanier(w);
         cs.update( co, s);
                }

    
    

    @FXML
    private void onChercheLigne(MouseEvent event) throws SQLException {
    lignecommandeService lcs=new lignecommandeService();
        String h=tfcherche.getText();
        System.out.println(h);
//        if (h==""){
//            idLigne.setCellValueFactory(new PropertyValueFactory<>("idLigne"));
//    Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//     idCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
//    codeProduit.setCellValueFactory(new PropertyValueFactory<>("codeProduit"));
//    quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
//        tableLigneCommande.setItems(lcs.findAll());
//                tableLigneCommande.refresh();
//      setIdPrixLabel(String.valueOf(lcs.calcul()));
//
//
//        }
        if(h!=""){
            
            idLigne.setCellValueFactory(new PropertyValueFactory<>("idLigne"));
    Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
     idCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
    codeProduit.setCellValueFactory(new PropertyValueFactory<>("codeProduit"));
    quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        tableLigneCommande.setItems(lcs.findByUserId(Integer.parseInt(h)));
        tableLigneCommande.refresh();
        
            setIdPrixLabel(String.valueOf(lcs.montantParCommande(Integer.parseInt(h))));
    }
  

    
    }
    
    public void exportPDFLigne() throws FileNotFoundException,DocumentException, SQLException {

 //FileNotFoundException, DocumentException 
              Document doc = new Document();
                FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF files (.pdf)", ".pdf"),
                new FileChooser.ExtensionFilter("HTML files (.html)", ".html")
        );
        File saveFile = fileChooser.showSaveDialog(tableLigneCommande.getScene().getWindow());

      //  file.save(saveFile.getAbsolutePath());

        PdfWriter.getInstance(doc,new FileOutputStream(saveFile.getAbsolutePath()));
        doc.open();
        System.out.println(doc.getHtmlStyleClass());
        lignecommandeService cas= new lignecommandeService();
      

        doc.add(new Paragraph("===================LISTE DES LIGNES COMMANDES=================="));
           doc.add(new Paragraph("Montant total des lignes de commande :"+cas.calcul()));
                   doc.add(new Paragraph("======================================================"));

           

         for ( lignecommande p :cas.findAll())
         {
        doc.add(new Paragraph("No Commande :"+p.getIdCommande()));
        doc.add(new Paragraph("Code Produit :"+p.getCodeProduit()));
        doc.add(new Paragraph("Quantité :"+p.getQuantite()));
        doc.add(new Paragraph("Prixx :"+p.getPrix()));

       
        doc.add(new Paragraph("=========================================="));
         }

        doc.close();

    }

    @FXML
    private void onExportPDF(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {
        exportPDFLigne();
    }
    
}
    
//
//AnchorPane pane=FXMLLoader.load(getClass().getResource("afficherCommandesAdminFXML.fxml"));
//        pane.getChildren().setAll(pane);