package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Music {

    //variabile MediaPlayer
    private MediaPlayer mediaPlayer;

    public Music(String sound){                                     //costruttore Music
        Media music = new Media(new File(sound).toURI().toString());//creazione Media con la musica
        mediaPlayer = new MediaPlayer(music);                       //creazione player con relativo media
    }

    public void musicStartLoop(){                                   //metodo musicStartLoop
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);          //riproduremo la musica in loop
        mediaPlayer.setVolume(0.1);                                 //settiamo il volume
        mediaPlayer.play();                                         //facciamo partire la musica
    }

    public void musicStart(){                                       //metodo musicStart
        mediaPlayer.stop();                                         //stoppiamo la musica
        mediaPlayer.play();                                         //facciamo partire la musica
    }

}
