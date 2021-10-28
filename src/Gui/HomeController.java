
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class HomeController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button btnFacture;
    @FXML
    private Button btnPayment;
    @FXML
    private Circle circle;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        circle.setStroke(Color.SEAGREEN);
        Image im = new Image ("/image/logo.jpg", false);
        circle.setFill(new ImagePattern(im));
        circle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
    
    } 
    
    @FXML
    private void CRUDPaymentTransition(ActionEvent event) { 
         try {
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDPayment.fxml"));
            Parent root = (Parent) loader.load();
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
     @FXML
    private void CRUDFacturesTransition(ActionEvent event) { 
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDFacture.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gestion des factures");
            Scene sceneAg = new Scene(root);           
            stage.setScene(sceneAg);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void play(MouseDragEvent event) {
                 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("video.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("pub");
            Scene sceneAg = new Scene(root);           
            stage.setScene(sceneAg);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void play(ActionEvent event) {
    }
    }
    

    

