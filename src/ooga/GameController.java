package ooga;

import java.util.function.Consumer;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.engine.games.Game;
import ooga.loader.FactoryException;
import ooga.loader.GameFactory;

/**
 * Object created by the Driver and passed to display to control specific actions of the Driver.
 */
public class GameController {

  private static final String DEFAULT_ID = "g1,l1";
  private final Stage stage;
  private final Timeline timeline;
  private final GameFactory gameFactory;
  private Consumer<Game> gameSetter;
  private String pathToCurrentGame;
  private Game currentGame;
  private String randomGameName;
  private String id;
  private String theme = "default";
  private boolean active = false;

  public GameController(Stage stage, Timeline timeline, Consumer<Game> gameSetter) {
    this.stage = stage;
    this.timeline = timeline;
    this.gameSetter = gameSetter;
    this.gameFactory = new GameFactory();
  }

  /**
   * Used to set the display to the current game being run by driver
   * @return the Game being run by the driver
   */
  public Game getGame() {
    return currentGame;
  }

  /**
   * Used to set the scene of the primary stage
   * @param scene the scene from Display class to show on screen
   */
  public void setScene(Scene scene) {
    this.stage.setScene(scene);
  }

  /**
   * Launches a game from a specific file. Starts timeline and sets the driver to update it.
   * @param fileName the name of the file to launch game from
   * @throws FactoryException if game with this name cannot be launched
   */
  public void launchGame(String fileName) throws FactoryException {
    pathToCurrentGame = fileName;
    currentGame = gameFactory.makeCorrectGame(pathToCurrentGame);
    gameSetter.accept(currentGame);
    timeline.play();
    active = true;
  }

  /**
   * Generates a random game for the given game and launches it
   * @param gameName the name of the game to launch
   * @throws FactoryException if the GameFactory fails to make Random Game
   */
  public void makeRandomGame(String gameName) throws FactoryException {
    randomGameName = gameName;
    currentGame = gameFactory.makeRandomGame(gameName);
    gameSetter.accept(currentGame);
    timeline.play();
  }

  /**
   * Used to start a new instance of the current game or, if random, make a new random game.
   * @throws FactoryException if the game cannot be restarted
   */
  public void restartGame() throws FactoryException {
    if(pathToCurrentGame == null) {
      makeRandomGame(randomGameName);
    }
    else{
      launchGame(pathToCurrentGame);
    }
  }

  /**
   * Stops the Driver from updating the level
   */
  public void pauseTimeline() {
    timeline.pause();
  }

  /**
   * Resumes the driver from updating level
   */
  public void playTimeline() {
    timeline.play();
  }

  /**
   * Used to find the name of file that generated this game or, if random, return random
   * @return string associated with this game level, or random
   */
  public String getGameName() {
    if(pathToCurrentGame == null) {
      return "random";
    }
    return pathToCurrentGame;
  }

  public void setId(String gameLevelComboChosen) {
    id = gameLevelComboChosen;
  }

  public String getId() {
    if(id == null) {
      return DEFAULT_ID;
    }
    return id;
  }

  public Stage getStage() {
    return stage;
  }

  public void setTheme(String givenTheme) {
    theme = givenTheme;
  }

  public String getTheme() {
    String ret = theme;
    return ret;
  }

  public boolean isActive() {
    return active;
  }
}
