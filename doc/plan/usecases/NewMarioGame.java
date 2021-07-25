import javafx.animation.KeyFrame;
import javafx.stage.Stage;
import ooga.engine.games.Game;

import java.sql.Driver;

//This class gives 2 use case examples of what will be implemented in Driver:
//Starting a new mario game and updating the position of objects on the game screen
public class NewMarioGame extends Driver {
  private Game currentGame;

  @Override
  //Use Case 1: The start method of Driver will initialize the main menu screen and loader. It will also
  //pass a string in to loader.getCorrectGame() based on the buttons pressed on main menu to initialize
  //the correct implementation of Game Objects
  public void start(Stage primaryStage) throws Exception {
    MainMenuScreen startMenu = new MainMenuScreen();
    CSVLoader loader = new CSVLoader();
    primaryStage.setScene(startMenu.getScene());
    currentGame = loader.getCorrectGame("Mario");
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step());
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
  }

  //Use case 2: The step method of driver will just call .updateLevel() on the current level to update
  //the position of all Game Objects contained in Level
  private void step() {
    currentGame.updateLevel();
  }
}