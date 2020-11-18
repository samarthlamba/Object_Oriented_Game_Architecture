package ooga.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.function.Consumer;

public class LostVikingsMenuScreen extends GameMenuScreen{
  private static final String VIKINGS = "Vikings";

  public LostVikingsMenuScreen(Consumer<String> launchGame, Consumer<String> randomLevel) {
    super(VIKINGS, launchGame,randomLevel);
    Media media = new Media(new File("src/resources/lostVikingSong.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
  }
}
