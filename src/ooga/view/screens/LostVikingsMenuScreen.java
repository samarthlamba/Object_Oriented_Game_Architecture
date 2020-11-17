package ooga.view.screens;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import ooga.view.screens.GameMenuScreen;

import java.io.File;
import java.util.function.Consumer;

public class LostVikingsMenuScreen extends GameMenuScreen {

  public LostVikingsMenuScreen(Consumer e) {
    super(e);
    Media media = new Media(new File("src/resources/lostVikingSong.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
  }
}
