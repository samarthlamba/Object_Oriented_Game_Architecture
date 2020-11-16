package ooga;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.engine.games.Game;
import ooga.engine.games.GamePlay;
import ooga.engine.games.HighScore;
import ooga.loader.GameFactory;
import ooga.view.Display;
import ooga.view.Screen;

import java.util.ResourceBundle;

public class Driver extends Application {
  protected Game myGame;

  private static final int STEP_SPEED = 100;
  private static final ResourceBundle LEVEL_FILE_LOCATIONS = ResourceBundle.getBundle("LevelFileLocations");
  private KeyFrame displayFrame;
  private Timeline timeline;
  private Game game;
  private Display display;
  private GameFactory gameFactory;
  private String gameTitle;
  private String pathToCurrentGame;

  @Override
  public void start(Stage initialStage) throws Exception {
    initializeTimeline();
    display = new Display(new GameController(initialStage,timeline,this::setGame));
    gameFactory = new GameFactory();
    initialStage.show();
    display.setMainMenuScreen();
  }

  private void setGame(Game game) {
    this.game = game;
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
      HighScore highScore = new HighScore(gameTitle);
      highScore.checkAddHighScore(game.getPoints());
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
    display.setSplashScreen("Victory");
  }


  public GamePlay getGame() {
    return game;
  }
}


