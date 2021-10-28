
package Gui;


import PaymentService.CRUDPayments;
import Payments.entities.Payments;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class CRUDPaymentController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button payments;
    @FXML
    private Button facture;
    @FXML
    private TextField tfRecherche;
     @FXML
    private TextField tfid1;
      @FXML
    private TextField tfid2;
    @FXML
    private TextField tfnumero;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfdate;
    @FXML
    private Button btnAddPayement;
    
    @FXML
    private Button EditPayement;
    @FXML
    private TableView<Payments> tabPayement;
    @FXML
    private TableColumn<Payments, Integer> colid1;
        @FXML
    private TableColumn<Payments, Integer> colid2;
    @FXML
    private TableColumn<Payments, String> colnumero;
    @FXML
    private TableColumn<Payments, String> colpassword;
    @FXML
    private ImageView img;
    @FXML
    private TableColumn<Payments, String> coldate;
    ObservableList<Payments> listA;
        ObservableList<Payments> dataList; 
    @FXML
    private Button btnHomeTransition;
    @FXML
    private Button btnDeletePayement;
  
      int index = -1;
    @FXML
    private Circle circle;
    @FXML
    private TableColumn<?, ?> id;
        @FXML
        
    private void Lbutton(ActionEvent event) { 
         try {
              //CRUDPayments sp = new CRUDPayments();
//              ArrayList<Payments> list = (ArrayList<Payments>) sp.findAll();
//         System.out.println(list.toString());
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichagePayement.fxml"));
            
            Parent root = (Parent) loader.load();
//            AffichagePayementController apc = loader.getController();
//            apc.setlabelaffiche(list.toString());
            Stage stage = new Stage();
            stage.setTitle("Gestion des payments");
            Scene sceneAg = new Scene(root);           
            stage.setScene(sceneAg);
            stage.show();
            new animatefx.animation.RollIn(root).play();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    } 
       

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
         
        new animatefx.animation.Pulse(tfnumero);
        new animatefx.animation.BounceIn(tfpassword);
        new animatefx.animation.Flash(pane);
           new animatefx.animation.Shake(tfdate);
       
         tfnumero.setText(null);
      
        CRUDPayments pay = new CRUDPayments();
     //  colid1.setCellValueFactory(new PropertyValueFactory<>("IdPayment"));
       // colid2.setCellValueFactory(new PropertyValueFactory<>("IdFacture"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("NumeroCompte"));
        colnumero.setCellValueFactory(new PropertyValueFactory<>("Password"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("DateExpiration"));
        tabPayement.getItems().addAll(pay.findAll());
        
          recherche_Payement();
        majTable();
    }    
     public void majTable() {
//colid1.setCellValueFactory(new PropertyValueFactory<>("IdPayment"));
  //   colid2.setCellValueFactory(new PropertyValueFactory<>("IdFacture"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("NumeroCompte"));
        colnumero.setCellValueFactory(new PropertyValueFactory<>("Password"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("DateExpiration"));

     CRUDPayments cda = new CRUDPayments();
        listA = cda.findAll();
        tabPayement.setItems(listA);
    }

    @FXML
    private void AddPayement(ActionEvent event) {
        
        
       //  String pid1 = tfid1.getText();
        //String pid2= tfid2.getText();
         String pnumero = tfnumero.getText();
        String ppassword = tfpassword.getText();
        String  pdate = tfdate.getText();
       
        Payments p = new Payments(pnumero, ppassword, pdate);
        
        CRUDPayments pm = new CRUDPayments();
        pm.insertpayement(p);
          recherche_Payement();
        majTable();
    }
    
     
    @FXML
    private void HomeTransition(ActionEvent event) { 
         Stage stage = (Stage) btnHomeTransition.getScene().getWindow();
        stage.close();
    }
    //affichage
    private void findAll(MouseEvent event) {
        index = tabPayement.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        
  //tfid1.setText(colid1.getCellData(index).toString());
       // tfid2.setText(colid2.getCellData(index).toString());
     
        tfnumero.setText(colpassword.getCellData(index));
        tfpassword.setText(colnumero.getCellData(index));
        tfdate.setText(coldate.getCellData(index));
          tabPayement.refresh();
      
    }


    @FXML
    private void DeletePayment(ActionEvent event) {
        
        
         String numeroCompte = tfnumero.getText();
         CRUDPayments pay = new CRUDPayments();
        pay.DeletePayement("numeroCompte");
     
        majTable();
          recherche_Payement();
    }
   

    @FXML
    private void EditPayment(ActionEvent event) {
         //String qid1 = tfid1.getText();
        //String  qid2 = tfid2.getText();
          String qnumero = tfnumero.getText();
        String qpassword = tfpassword.getText();
        String  qdate = tfdate.getText();
//int id1 = Integer.parseInt(qid1);
//int id2 = Integer.parseInt(qid2);
        Payments P = new Payments(qnumero,qpassword,qdate);
        CRUDPayments cds = new CRUDPayments();
        cds.Update(P);
         recherche_Payement();
        majTable(); 
 
    }
     void recherche_Payement() {
      // colid1.setCellValueFactory(new PropertyValueFactory<>("IdPayment"));
     //colid2.setCellValueFactory(new PropertyValueFactory<>("IdFacture"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("NumeroCompte"));
        colnumero.setCellValueFactory(new PropertyValueFactory<>("Password"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("DateExpiration"));

        CRUDPayments CT = new CRUDPayments();
        dataList = CT.findAll();
        tabPayement.setItems(dataList);

        FilteredList<Payments> filteredData = new FilteredList<>(dataList, b -> true);
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Payments -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (Payments.getNumeroCompte().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Payments.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Payments> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabPayement.comparatorProperty());
        tabPayement.setItems(sortedData);
    }
    
}
