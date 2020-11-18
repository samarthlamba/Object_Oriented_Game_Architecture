package ooga.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import ooga.Main;

import java.io.File;
import java.util.function.Consumer;

public class SuperMarioBrosMenuScreen extends GameMenuScreen{
    private static final String MARIO = "Mario";

    public SuperMarioBrosMenuScreen(Consumer<String> launchGame, Consumer<String> randomGame) {
        super(MARIO,launchGame,randomGame);
        Media media = new Media(new File("src/resources/superMarioBroSong.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }
}
