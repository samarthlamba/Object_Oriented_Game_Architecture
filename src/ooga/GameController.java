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
  private Consumer<Game> gameSetter;
  private String pathToCurrentGame;
  private Game currentGame;

  public GameController(Stage stage, Timeline timeline, Consumer<Game> gameSetter) {
    this.stage = stage;
    this.timeline = timeline;
    this.gameSetter = gameSetter;
    this.gameFactory = new GameFactory();
  }

  public Game getGame() {
    return currentGame;
  }

  public void setScene(Scene scene) {
    this.stage.setScene(scene);
  }

  public void launchGame(String fileName) {
    pathToCurrentGame = fileName;
    currentGame = gameFactory.makeCorrectGame(pathToCurrentGame);
    gameSetter.accept(currentGame);
    timeline.play();
  }

  public void restartGame() {
    launchGame(pathToCurrentGame);
  }

  public void pauseTimeline() {
    timeline.pause();
  }

  public void playTimeline() {
    timeline.play();
  }

  public String getGameName() {
    return pathToCurrentGame;
  }
}
