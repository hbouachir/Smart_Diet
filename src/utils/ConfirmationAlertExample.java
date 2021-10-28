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

import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfirmationAlertExample extends Application {

   public Label label;

   public void showConfirmation() {

      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Delete File");
      alert.setHeaderText("Are you sure want to move this file to the Recycle Bin?");
      alert.setContentText("C:/MyFile.txt");

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         this.label.setText("No selection!");
      } else if (option.get() == ButtonType.OK) {
         this.label.setText("File deleted!");
      } else if (option.get() == ButtonType.CANCEL) {
         this.label.setText("Cancelled!");
      } else {
         this.label.setText("-");
      }
   }

   @Override
   public void start(Stage stage) {

      VBox root = new VBox();
      root.setPadding(new Insets(10));
      root.setSpacing(10);

      this.label = new Label();

      Button button = new Button("Click here to delete file");

      button.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            showConfirmation();
         }
      });

      root.getChildren().addAll(button, label);

      Scene scene = new Scene(root, 450, 250);
      stage.setTitle("JavaFX Confirmation Alert (o7planning.org)");
      stage.setScene(scene);

      stage.show();

   }}