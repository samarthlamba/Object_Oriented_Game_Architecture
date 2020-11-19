package ooga.view.screens;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import ooga.GameController;
import ooga.view.screens.GameMenuScreen;

import java.io.File;
import java.util.function.Consumer;

public class MetroidMenuScreen  extends GameMenuScreen {

  public MetroidMenuScreen(Consumer e, Consumer r, GameController g) {
    super("Metroid",e,r,g);
    Media media = new Media(new File("src/resources/metroidSong.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
  }

  @Override
  public Image setGameTitle() {
    Image gameTitle = new Image("/ooga/view/resources/images/metroidTitle.png",IMAGE_WIDTH,IMAGE_HEIGHT,true,true);
    return gameTitle;
  }
}
