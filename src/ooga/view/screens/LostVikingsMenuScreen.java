package ooga.view.screens;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import ooga.GameController;
import ooga.view.screens.GameMenuScreen;

import java.io.File;
import java.util.function.Consumer;

public class LostVikingsMenuScreen extends GameMenuScreen {

  public LostVikingsMenuScreen(Consumer e, Consumer r, GameController g) {
    super("Vikings",e,r,g);
    Media media = new Media(new File("src/resources/lostVikingSong.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
  }

  @Override
  public Image setGameTitle() {
    Image gameTitle = new Image("/ooga/view/resources/images/vikingsTitle.png",IMAGE_WIDTH-50,IMAGE_HEIGHT-50,true,true);
    return gameTitle;
  }
}
