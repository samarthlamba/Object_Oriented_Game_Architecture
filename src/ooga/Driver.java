package ooga;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.engine.games.Game;
import ooga.engine.games.GamePlay;
import ooga.loader.GameFactory;
import ooga.view.Display;
import ooga.view.MainMenuScreen;
import ooga.view.Screen;

import java.util.ResourceBundle;

public class Driver extends Application {
  protected Game myGame;
  protected MainMenuScreen myMenu;

  private static final int STEP_SPEED = 100;
  private static final ResourceBundle LEVEL_FILE_LOCATIONS = ResourceBundle.getBundle("LevelFileLocations");
  private boolean createTimeline;
  private KeyFrame displayFrame;
  private Timeline simulate;
  private Game game;
  private Display display;
  private GameFactory gameFactory;
  private String gameTitle;

  @Override
  public void start(Stage initialStage) throws Exception {
    display = new Display(initialStage);
    gameFactory = new GameFactory();
    initialStage.show();
    display.setMainMenuScreen(this::launchGameMenu);
  }

  private void launchGameMenu(String gameLabel) {
    gameTitle = gameLabel;
    display.setGameMenuScreen(gameTitle, this::launchGame);
  }

  private void launchGame(String gameLevel) {
//      display.test();
    String filePath = LEVEL_FILE_LOCATIONS.getString(gameTitle+","+gameLevel);
//    System.out.print(filePath);
    game = gameFactory.makeCorrectGame(filePath);
    display.setGameDisplay(game);
    startTimeline();
  }

  private void startTimeline() {
      displayFrame = new KeyFrame(Duration.millis(STEP_SPEED), e -> step());
      simulate = new Timeline();
      simulate.setCycleCount(Timeline.INDEFINITE);
      simulate.getKeyFrames().add(displayFrame);
      simulate.play();
  }

  private void step() {
    System.out.println("stepping in progress");
    game.updateMoveables();
    display.updateDisplay();
  }

  public Screen getGameMenu() {
    return display.getGameMenu(this::launchGame);
  }

  public GamePlay getGame() {
    return game;
  }
}


