/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import PaymentService.CRUDPayments;
import Payments.entities.Payments;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author wejde
 */
public class AffichagePayementController implements Initializable {

    @FXML
    private Label labelaffiche;
    int index = -1;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Label lab3;
    @FXML
    private AnchorPane tabb;
    @FXML
    private Text textt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     void setlabelaffiche(String  text) {
         //this.lab1.setText(text);
           //this.lab2.setText(text);
             //this.lab3.setText(text);
               
     }}
  

