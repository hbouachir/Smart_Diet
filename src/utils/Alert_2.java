/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author HAMZA
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
 
public class Alert_2 {
 
    public static void display(String title,String message){
            Stage window= new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMaxWidth(250);
            Label label = new Label();
            label.setText(message);
            Button closeButton=new Button("oui");
           
            closeButton.setOnAction(e->window.close());
            VBox layout = new VBox(10);                    
            layout.getChildren().addAll(label,closeButton);
            
            Scene scene=new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
            
            
            
    }



}