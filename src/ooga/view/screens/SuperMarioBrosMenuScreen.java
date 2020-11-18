package ooga.view.screens;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import ooga.Main;
import ooga.view.screens.GameMenuScreen;

import java.io.File;
import java.util.function.Consumer;

public class SuperMarioBrosMenuScreen extends GameMenuScreen {

    public SuperMarioBrosMenuScreen(Consumer e) {
        super(e);
//        System.out.println(Main.class.getResource("MarioLevel1.csv"));
        Media media = new Media(new File("src/resources/superMarioBroSong.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    @Override
    public Image setGameTitle() {
        Image gameTitle = new Image("/ooga/view/resources/images/marioTitle.png",IMAGE_WIDTH,IMAGE_HEIGHT,true,true);
        return gameTitle;
    }
}
