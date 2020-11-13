package ooga.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.function.Consumer;

public class SuperMarioBrosMenuScreen extends GameMenuScreen{

    public SuperMarioBrosMenuScreen(Consumer e) {
        super(e);
        Media media = new Media(new File("src\\resources\\superMarioBroSong.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }
}
