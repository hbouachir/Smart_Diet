
package Gui;

import PaymentService.CRUDFacture;
import Payments.entities.Facture;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class CRUDFactureController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button btnFacture;
    @FXML
    private Button btnPayment;
    @FXML
    private TableView<Facture> tabFacture;
    @FXML
    private TableColumn<Facture, String> colname;
    @FXML
    private TableColumn<Facture, String> colEtat;
    @FXML
    private TableColumn<Facture, String> colDate;
    @FXML
    private Button btnAddCategories;
    @FXML
    private Button btnDeleteCategories;
    private TextField tfcetatPay;
    private TextField tfcname;
    
    
            
    @FXML
    private Button btnHomeTransition;
    private TableColumn<Facture, Integer> colid1;
    private TableColumn<Facture, Integer> colid2;
    @FXML
    private TextField tfetatPay;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tfcRecherche;
 int index = -1;
    private TextField tfid1;
    private TextField tfid2;
     ObservableList<Facture> listA;
    @FXML
    private AnchorPane titre; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //tfid1.setText(null);
          // tfid2.setText(null);
        tfetatPay.setText(null);
        tfdate.setText(null);
         tfname.setText(null);
       majTable();
    }    
        @FXML
    private void getSelectedFacture(MouseEvent event) {
        index = tabFacture.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        
 // tfid1.setText(colid1.getCellData(index).toString());
       // tfid2.setText(colid2.getCellData(index).toString());
     
        tfname.setText(colname.getCellData(index));
        tfetatPay.setText(colEtat.getCellData(index));
        tfdate.setText(colDate.getCellData(index));
      
    }
      @FXML
    private void HomeTransition(ActionEvent event) { 
         Stage stage = (Stage) btnHomeTransition.getScene().getWindow();
        stage.close();
    }




    @FXML
    private void AddFacture(ActionEvent event) throws SQLException {
       // String pid1 = tfid1.getText();
       // String pid2= tfid2.getText();
          String pnom = tfname.getText();
        String petat =  tfetatPay.getText();
        String pdate = tfdate.getText();
      
        Facture p = new Facture(pnom, petat, pdate);
        CRUDFacture pm = new CRUDFacture();
      
        pm.insertFacture(p);
       
        majTable();
      
    }

    @FXML
    private void DeleteFacture(ActionEvent event) {
           String pnom = tfname.getText();
    
       
         CRUDFacture cda = new CRUDFacture();
        String dateFacture = null;
        cda.DeleteFacture(dateFacture);
        majTable();
    }

  
    
    

    
    
public void majTable() {
//colid1.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("IdFacture"));
        //colid2.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("IdCommande"));
        colname.setCellValueFactory(new PropertyValueFactory<>("EtatPayment"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("DateFacture"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Name"));

       

        CRUDFacture cda = new CRUDFacture();
        listA = cda.findAll();
        tabFacture.setItems(listA);
    }



    

    
}

    
