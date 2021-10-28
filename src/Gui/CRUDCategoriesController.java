/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import ProduitService.CRUDCategories;

import Produits.entities.Categories;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class CRUDCategoriesController implements Initializable {

   
    @FXML
    private TextField tfcnom;
    @FXML
    private TextField tfcdescription;
    @FXML
    private TextField tfcstatut;
    @FXML
    private Button btnHomeTransition;
    @FXML
    private Button btnAddCategories;
    @FXML
    private Button btnEditCategories;
    @FXML
    private Button btnDeleteCategories;
    @FXML
    private TableView<Categories> tabCategories;
    @FXML
    private TableColumn<Categories,Integer> colid;
    @FXML
    private TableColumn<Categories,String> colnom;
    @FXML
    private TableColumn<Categories,String> coldescription;
    @FXML
    private TableColumn<Categories,String> colstatut;
    ObservableList<Categories> dataList;
    ObservableList<Categories> listB;
    
    int index = -1;
    @FXML
    private TextField tfcid;
    @FXML
    private TextField tfcRecherche;
    
    

    /**
     * Initializes the controller class.
     */
     void recherche_Categories() {
        colid.setCellValueFactory(new PropertyValueFactory<Categories, Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Categories,String>("nom"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Categories,String>("description"));
        colstatut.setCellValueFactory(new PropertyValueFactory<Categories,String>("statut"));

        CRUDCategories CT = new CRUDCategories();
        dataList = CT.afficherCategories();
        tabCategories.setItems(dataList);

        FilteredList<Categories> filteredData = new FilteredList<>(dataList, b -> true);
        tfcRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Categories -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (Categories.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Categories.getStatut().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Categories> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabCategories.comparatorProperty());
        tabCategories.setItems(sortedData);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfcid.setText(null);
        tfcnom.setText(null);
        tfcdescription.setText(null);
        tfcstatut.setText(null);
        majTable();
        recherche_Categories();
    }    

    @FXML
    private void HomeTransition(ActionEvent event) { 
         Stage stage = (Stage) btnHomeTransition.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void AddCategories(ActionEvent event) {
        
        String Cnom = tfcnom.getText();
        String Cdescription = tfcdescription.getText();
        String Cstatut = tfcstatut.getText();
        Categories c = new Categories(Cnom,Cdescription,Cstatut);
        CRUDCategories Ct = new CRUDCategories();
        Ct.ajouterCategories(c);
        recherche_Categories();
        majTable();
      
    }

    @FXML
    private void EditCategories(ActionEvent event) {
        String qid = tfcid.getText();
        String qnom = tfcnom.getText();
        String qdescription = tfcdescription.getText();
        String qstatut = tfcstatut.getText();
        
      

int id = Integer.parseInt(qid);

        Categories C = new Categories(id,qnom,qdescription,qstatut);
        CRUDCategories cds = new CRUDCategories();
        cds.UpdateCategories(C);
        recherche_Categories();
        majTable(); 
    }

    @FXML
    private void DeleteCategories(ActionEvent event) {
       String qid = tfcid.getText();
        int id = Integer.parseInt(qid);
        CRUDCategories cda = new CRUDCategories();
        cda.DeleteCategories(id);
        recherche_Categories();
        majTable();
        
        
    }
    
    
    
    public void majTable() {

        colid.setCellValueFactory(new PropertyValueFactory<Categories, Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Categories,String>("nom"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Categories,String>("description"));
        colstatut.setCellValueFactory(new PropertyValueFactory<Categories,String>("statut"));

        CRUDCategories cda = new CRUDCategories();
        listB = cda.afficherCategories();
        tabCategories.setItems(listB);
    }
    
    
    @FXML
    private void getSelectedCategories(MouseEvent event) {
        index = tabCategories.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        tfcid.setText(colid.getCellData(index).toString());
        tfcnom.setText(colnom.getCellData(index));
        tfcdescription.setText(coldescription.getCellData(index));
        tfcstatut.setText(colstatut.getCellData(index).toString());
    }
    
    
    
    
}
