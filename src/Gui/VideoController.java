/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

    

public class VideoController implements Initializable {

    @FXML
    private MediaView media;
    private static final String MEDIA_URL = "/image/video.mp4";
    private MediaPlayer mediaPlayer;

   
    

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    System.out.println(url.toString());
        String MEDIA_URL = null;
        System.out.println(this.getClass().getResource(MEDIA_URL).toExternalForm());
        mediaPlayer = new MediaPlayer( new Media(this.getClass().getResource(MEDIA_URL).toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        media.setMediaPlayer(mediaPlayer);
        
    }    
    
}
