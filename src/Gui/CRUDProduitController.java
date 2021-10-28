/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Produits.entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ProduitService.CRUDProduit;
import Produits.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class CRUDProduitController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfquantité;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfstatut;
    @FXML
    private Button btnHomeTransition;
    @FXML
    private Button btnAddProduit;
    @FXML
    private Button btnEditProduit;
    @FXML
    private Button btnDeleteProduit;
    @FXML
    private TableView<Produit> tabProduit;
    @FXML
    private TableColumn<Produit,Integer> colcodeProduit;
    @FXML
    private TableColumn<Produit,String> colnom;
    @FXML
    private TableColumn<Produit,String> coldescription;
    @FXML
    private TableColumn<Produit,String> colquantité;
    @FXML
    private TableColumn<Produit,String> colprix;
    @FXML
    private TableColumn<Produit,String> colstatut;
     @FXML
    private TableColumn<Produit,String> colcategorie;
    ObservableList<Produit> listA;
    ObservableList<Produit> dataList; 
//    ObservableList<PieChart.Data> piechartdata;
    int index = -1;
    @FXML
    private TextField tfcodeProduit;
    @FXML
    private TextField tfRecherche;
    @FXML
    private TextField tfcategorie;

//   ObservableList<Produit> list = FXCollections.observableArrayList();
   
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tfcodeProduit.setText(null);
        tfnom.setText(null);
        tfdescription.setText(null);
        tfquantité.setText(null);
        tfprix.setText(null);
        tfstatut.setText(null);
        tfcategorie.setText(null);
        
        majTable();
        recherche_Produit();
    }    

    @FXML
    private void HomeTransition(ActionEvent event) { 
         Stage stage = (Stage) btnHomeTransition.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void AddProduit(ActionEvent event) { 
        
         
        String pnom = tfnom.getText();
        String pdescription = tfdescription.getText();
        String pquantité = tfquantité.getText();
        String pprix = tfprix.getText();
        String pstatut = tfstatut.getText();
        String pcategorie = tfcategorie.getText();
        
//        String pid_categories = tfid_categories.getText(); 
////        int pid_categories = Integer.parseInt(pid_categories); 
       
        Produit p = new Produit(pnom, pdescription, pquantité, pprix, pstatut,pcategorie);
        CRUDProduit pm = new CRUDProduit();
        pm.ajouterProduits(p);
        recherche_Produit();
        majTable();
    }

    @FXML
    private void EditProduit(ActionEvent event) {
        String qcodeProduit = tfcodeProduit.getText();
        String qnom = tfnom.getText();
        String qdescription = tfdescription.getText();
        String qqauntité = tfquantité.getText();
        String qprix = tfprix.getText();
        String qstatut = tfstatut.getText();
        String qcategorie = tfcategorie.getText();
        
      

int codeProduit = Integer.parseInt(qcodeProduit);


        Produit P = new Produit(codeProduit,qnom,qdescription,qqauntité,qprix,qstatut,qcategorie);
        CRUDProduit cds = new CRUDProduit();
        cds.UpdateProduit(P);
        recherche_Produit();
        majTable(); 
     
        
     
    }

    @FXML
    private void DeleteProduit(ActionEvent event) {
        String qcodeProduit = tfcodeProduit.getText();
        int codeProduit = Integer.parseInt(qcodeProduit);
         CRUDProduit cda = new CRUDProduit();
        cda.DeleteProd(codeProduit);
        recherche_Produit();
        majTable();
        
    }
    
    public void majTable() {

        colcodeProduit.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("codeProduit"));
        colnom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        colquantité.setCellValueFactory(new PropertyValueFactory<Produit, String>("quantité"));
        colprix.setCellValueFactory(new PropertyValueFactory<Produit, String>("prix"));
        colstatut.setCellValueFactory(new PropertyValueFactory<Produit, String>("statut"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));

        CRUDProduit cda = new CRUDProduit();
        listA = cda.afficherProduit();
        tabProduit.setItems(listA);
    }
   
 @FXML
    private void getSelectedProduit(MouseEvent event) {
        index = tabProduit.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        tfcodeProduit.setText(colcodeProduit.getCellData(index).toString());
        tfnom.setText(colnom.getCellData(index));
        tfdescription.setText(coldescription.getCellData(index));
        tfquantité.setText(colquantité.getCellData(index));
        tfprix.setText(colprix.getCellData(index).toString());
        tfstatut.setText(colstatut.getCellData(index).toString());
        tfcategorie.setText(colcategorie.getCellData(index).toString());
    }
    
     
    
    
} 

