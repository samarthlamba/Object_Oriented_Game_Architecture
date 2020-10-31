package ooga;

import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.engine.Games.Game;
import ooga.loader.GameFactory;
import ooga.view.GameView;
import ooga.view.MainMenuScreen;

public class Driver extends Application {
  private Game myGame;

  @Override
  public void start(Stage primaryStage) throws Exception {
    ResourceBundle gameBundle = ResourceBundle.getBundle("GameConfig");
    final double frameRate = Double.parseDouble(gameBundle.getString("framerate"));
    KeyFrame frame = new KeyFrame(Duration.seconds(1/frameRate), e -> step(1/frameRate));
    Timeline t = new Timeline();
    t.setCycleCount(Timeline.INDEFINITE);
    t.getKeyFrames().add(frame);
    GameFactory factory = new GameFactory();
    MainMenuScreen myMenu = new MainMenuScreen(buttonText -> {
      myGame = factory.makeCorrectGame(gameBundle.getString(buttonText));
      GameView view = new GameView(myGame);
      primaryStage.setScene(view.getView());
      t.play();
    });
    primaryStage.setScene(myMenu.getView());
    primaryStage.show();
  }

  private void step(double v) {
    myGame.updateLevel();
  }
}
