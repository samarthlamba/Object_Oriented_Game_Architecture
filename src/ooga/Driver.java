package ooga;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.engine.games.Game;
import ooga.engine.games.GamePlay;
import ooga.loader.GameFactory;
import ooga.view.Display;
import ooga.view.Screen;

import java.util.ResourceBundle;

public class Driver extends Application {
  protected Game myGame;

  private static final int STEP_SPEED = 100;
  private static final ResourceBundle LEVEL_FILE_LOCATIONS = ResourceBundle.getBundle("LevelFileLocations");
  private boolean createTimeline;
  private KeyFrame displayFrame;
  private Timeline timeline;
  private Game game;
  private Display display;
  private GameFactory gameFactory;
  private String gameTitle;

  @Override
  public void start(Stage initialStage) throws Exception {
    initializeTimeline();
    display = new Display(initialStage,new TimelineManager(timeline));
    gameFactory = new GameFactory();
    initialStage.show();
    display.setMainMenuScreen(this::launchGameMenu);
  }

  private void launchGameMenu(String gameLabel) {
    gameTitle = gameLabel;
    display.setGameMenuScreen(gameTitle, this::launchGame);
  }

  private void launchGame(String gameLevel) {
    String filePath = LEVEL_FILE_LOCATIONS.getString(gameTitle+","+gameLevel);
    game = gameFactory.makeCorrectGame(filePath);
    display.setGameDisplay(game);
//    display.setGameDisplay(this::pause, this::play, this::restart); TODO
    timeline.play();
  }

  private void initializeTimeline() {
      displayFrame = new KeyFrame(Duration.millis(STEP_SPEED), e -> step());
      timeline = new Timeline();
      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.getKeyFrames().add(displayFrame);
  }

  private void step() {
    if(game.isLost()){
      victoryScreen();
    }
    if(game.isWon()) {
      victoryScreen();
    }
    else{
      game.updateLevel();
      display.updateDisplay();
    }
  }

  private void victoryScreen() {
    timeline.stop();
    display.setMainMenuScreen(this::launchGameMenu);
  }

  public Screen getGameMenu() {
    return display.getGameMenu(this::launchGame);
  }

  public GamePlay getGame() {
    return game;
  }
}


