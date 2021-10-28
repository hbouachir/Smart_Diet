/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import entite.rendezvous;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import services.rendezvousService;


import entite.rendezvous;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.rendezvousService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Nour
 */
public class AjouterRdvFXMLController implements Initializable {

  
   
    @FXML
    private TextField tfheure;

    @FXML
    private TextField tfidate;
   
    int index = -1;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModifier;
    @FXML
    private TableView<rendezvous> rdvTable;
    @FXML
    private TableColumn<rendezvous, String> dateRdv;
    @FXML
    private TableColumn<rendezvous, String> heureRdv;
//    private rendezvousService rd;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println("hello");
        rendezvousService rd = new rendezvousService();
       // rd = new rendezvousService();
           
        dateRdv.setCellValueFactory(new PropertyValueFactory<>("DateRdv"));
        heureRdv.setCellValueFactory(new PropertyValueFactory<>("Heure"));
          
        rdvTable.getItems().addAll(rd.afficherRdv()); 
    }    
   
    
@FXML
    private void ajouterRdv(ActionEvent event) { 
         String date = tfidate.getText();
        String heure = tfheure.getText();
        rendezvous r = new rendezvous(date, heure);
        rendezvousService rd = new rendezvousService();
        rd.ajouterRendezvous(r);
      Ajtable();
        
        
    }
        
//        if (tfidr.getText().isEmpty()){
//            System.out.println("3abi champs");
//        }
//         else if  (tfidp.getText().isEmpty()){
//                 System.out.println("Zid 3abi");
//                 }
//       if  (tfidate.getText().isEmpty()){
//                 System.out.println("Zid 3abi");
//                }
//           else if  (tfheure.getText().isEmpty()){
//                 System.out.println("hethi ekher wahda");
//                 
//                 }  else {
//               
//                       rendezvous R = new rendezvous();
////                       R.setidRdv(Integer.parseInt(tfidr.getText()));
////                       R.setidPatient(Integer.parseInt(tfidp.getText()));
//                       R.setdateRdv(tfidate.getText());
//                       R.setheure(tfheure.getText());
//
//                       rs.ajouterRdv(R);
//
//           }
       

@FXML
    private void suppRdv(ActionEvent event) {
         String dateRdv = tfidate.getText();
        //int dateRdv = Integer.parseInt(date);
         rendezvousService rd = new rendezvousService();
        rd.Delete("dateRdv");
        Ajtable();
    }

    @FXML
    private void modifierRdv(ActionEvent event) {
        
         String date = tfidate.getText();
        String heure= tfheure.getText();
        rendezvous r = new rendezvous(date, heure);
        rendezvousService rd = new rendezvousService();
        rd.UpdateRendezvous(r);
        Ajtable(); 

    }
   
    
    
    public void Ajtable() {        

        

    }
        private void getSelectedRendezvous(MouseEvent event) {
        index = rdvTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            
            return;
        }
        tfidate.setText(dateRdv.getCellData(index));
        tfheure.setText(heureRdv.getCellData(index));
        
    }


    private void onAffichageRdv(ActionEvent event) {
        Ajtable();
    }

//    private void AfficherRendezVous(ActionEvent event) {
//      ObservableList<rendezvous> list =  rd.afficherRdv();
//      System.out.println(list.toString());
//    }
    }
    
