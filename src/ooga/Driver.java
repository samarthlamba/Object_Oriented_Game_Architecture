package ooga;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.engine.games.Game;
import ooga.engine.games.HighScore;
import ooga.view.Display;


public class Driver extends Application {

  private static final int STEP_SPEED = 100;
  private KeyFrame displayFrame;
  private Timeline timeline;
  private Game game;
  private Display display;
  private GameController gameController;

  @Override
  public void start(Stage initialStage) throws Exception {
    initializeTimeline();
    gameController = new GameController(initialStage,timeline,this::setGame);
    display = new Display(gameController);
    initialStage.show();
    display.setMainMenuScreen();
  }

  protected void setGame(Game game) {
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
      splashScreen(GameEndStatus.LOSS);
      HighScore highScore = new HighScore(gameController.getGameName());
      highScore.checkAddHighScore(game.getPoints());
    }
    if(game.isWon()) {
      splashScreen(GameEndStatus.VICTORY);
    }
    else{
      game.updateLevel();
      display.updateDisplay();
    }
  }

  private void splashScreen(GameEndStatus status) {
    timeline.stop();
    display.setSplashScreen(status);
  }
}


