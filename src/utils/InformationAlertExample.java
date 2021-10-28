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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InformationAlertExample extends Application {

	// Show a Information Alert with header Text
	private void showAlertWithHeaderText() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("Connect to the database successfully!");

		alert.showAndWait();
	}

	// Show a Information Alert with default header Text
	private void showAlertWithDefaultHeaderText() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Test Connection");

		// alert.setHeaderText("Results:");
		alert.setContentText("Connect to the database successfully!");

		alert.showAndWait();
	}

	// Show a Information Alert without Header Text
	private void showAlertWithoutHeaderText() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Test Connection");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("Connect to the database successfully!");

		alert.showAndWait();
	}

	@Override
	public void start(Stage stage) {

		VBox root = new VBox();
		root.setPadding(new Insets(10));
		root.setSpacing(10);

		Button button1 = new Button("Information Alert");
		Button button2 = new Button("Information Alert with default Header Text");
		Button button3 = new Button("Information Alert without Header Text");

		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showAlertWithHeaderText();
			}
		});

		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showAlertWithDefaultHeaderText();
			}
		});

		button3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showAlertWithoutHeaderText();
			}
		});

		root.getChildren().addAll(button1, button2, button3);

		Scene scene = new Scene(root, 450, 250);
		stage.setTitle("JavaFX Information Alert (o7planning.org)");
		stage.setScene(scene);

		stage.show();

	}}