package ooga;

import java.util.function.Consumer;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.engine.games.Game;
import ooga.loader.GameFactory;

public class GameController {

  private final Stage stage;
  private final Timeline timeline;
  private final GameFactory gameFactory;
  private String currentGameTitle;
  private Consumer<Game> gameSetter;

  public GameController(Stage stage, Timeline timeline, Consumer<Game> gameSetter) {
    this.stage = stage;
    this.timeline = timeline;
    this.gameSetter = gameSetter;
    this.gameFactory = new GameFactory();
  }

  public void setScene(Scene scene) {
    this.stage.setScene(scene);
  }

  public void launchGameMenu(String gameLabel) {
    currentGameTitle = gameLabel;
  }

  public void launchGame(String filePath) {
    Game game = gameFactory.makeCorrectGame(filePath);
    gameSetter.accept(game);
    timeline.play();
  }


  private void restartGame() {
    game = gameFactory.makeCorrectGame(pathToCurrentGame);
    display.setGameDisplay(game);
    timeline.play();
  }

  public void pauseTimeline() {
    timeline.pause();
  }

  public void playTimeline() {
    timeline.play();
  }

}
