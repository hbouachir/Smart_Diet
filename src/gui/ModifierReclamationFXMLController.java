/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ReclamationServices;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Thrall
 */
public class ModifierReclamationFXMLController implements Initializable {

    @FXML
    private TextField tfModIdClient;
    @FXML
    private TextField tfModDescRec;
    @FXML
    private TextField tfModTypeRec;
    @FXML
    private Button buttModifier;
    @FXML
    private TableView<Reclamation> tableModifRec;
    @FXML
    private Button retourModifier;
    @FXML
    private Button actualiserTableModifRec;
    @FXML
    private TableColumn<Reclamation, Integer> modIdCol;
    @FXML
    private TableColumn<Reclamation, String> modDescCol;
    @FXML
    private TableColumn<Reclamation, Integer> modTypeCol;
    
    private ReclamationServices rec = new ReclamationServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void selectedReclamationModif(MouseEvent event) {
        
        Reclamation r = tableModifRec.getSelectionModel().getSelectedItem();
        tfModIdClient.setText(Integer.toString(r.getIdPersonne()));
        tfModDescRec.setText(r.getDescription());
        tfModTypeRec.setText(Integer.toString(r.getTypeReclamation()));
        
    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
        
        Reclamation r = new Reclamation();
                r.setIdPersonne(Integer.parseInt(tfModIdClient.getText()));
                r.setDescription(tfModDescRec.getText());
                r.setTypeReclamation(Integer.parseInt(tfModTypeRec.getText()));
                
                rec.modifierReclamation(r);
                
                MyConnection mc= MyConnection.getInstance();
        List<Reclamation> all;
        ReclamationServices crud = new ReclamationServices();
        all= crud.afficherReclamation();
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList(all);
        modIdCol.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("idPersonne"));
        modDescCol.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
        modTypeCol.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("typeReclamation"));
        tableModifRec.setItems(reclamations);
        
    }

    @FXML
    private void actualiserTableModif(ActionEvent event) {
        
        MyConnection mc= MyConnection.getInstance();
        List<Reclamation> all;
        ReclamationServices crud = new ReclamationServices();
        all= crud.afficherReclamation();
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList(all);
        modIdCol.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("idPersonne"));
        modDescCol.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
        modTypeCol.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("typeReclamation"));
        tableModifRec.setItems(reclamations);
        
    }
    
}
