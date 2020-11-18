package ooga.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.function.Consumer;

public class MetroidMenuScreen  extends GameMenuScreen{
  private static final String METROID = "Metroid";

  public MetroidMenuScreen(Consumer<String> launchGame, Consumer<String> randomLevel) {
    super(METROID,launchGame,randomLevel);
    Media media = new Media(new File("src/resources/metroidSong.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
  }
}
