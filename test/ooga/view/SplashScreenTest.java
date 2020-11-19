package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.GameController;
import ooga.GameEndStatus;
import ooga.engine.games.Game;
import ooga.loader.FactoryException;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Test;

public class SplashScreenTest extends DukeApplicationTest {
  private Display myDisplay;
  private Game myGame;
  private Stage myStage;
  private GameController testController;

  @Override
  public void start(Stage stage) {
    Rectangle testRectangle = new Rectangle(50,50, Color.RED);
    Scene myScene = new Scene(new Group(testRectangle),200,200);
    myStage = stage;
    myStage.setScene(myScene);
    myStage.show();
    Timeline testTimeline = new Timeline();
    testTimeline.setCycleCount(5);
    testController = new GameController(stage,testTimeline,this::setMyGame);
    myDisplay = new Display(testController);
  }

  @Test
  public void testSetSplashScreenChangesScene() {
    Scene initialScene = myStage.getScene();
    javafxRun(() -> myDisplay.setSplashScreen(GameEndStatus.LOSS));
    assertNotEquals(initialScene,myStage.getScene());
  }

  @Test
  public void testSplashScreenContainsCorrectButtons() {
    javafxRun(() -> myDisplay.setSplashScreen(GameEndStatus.LOSS));
    Scene splashScene = myStage.getScene();
    Button mainMenu = (Button) splashScene.lookup("#mainMenu");
    assertNotNull(mainMenu);
    assertEquals("Main Menu",mainMenu.getText());
    Button restart = (Button) splashScene.lookup("#restart");
    assertNotNull(restart);
    assertEquals("Restart",restart.getText());
  }

  @Test
  public void testMainMenuButtonReturnsToMainMenu() {
    javafxRun(() -> myDisplay.setSplashScreen(GameEndStatus.LOSS));
    Scene splashScene = myStage.getScene();
    Button mainMenu = (Button) splashScene.lookup("#mainMenu");

    javafxRun(() -> mainMenu.fire());

    Scene mainMenuScene = myStage.getScene();
    assertNotEquals(mainMenuScene,splashScene);
    Button game1 = (Button) mainMenuScene.lookup("#g1");
    assertNotNull(game1);
    Button game2 = (Button) mainMenuScene.lookup("#g2");
    assertNotNull(game2);
    Button game3 = (Button) mainMenuScene.lookup("#g3");
    assertNotNull(game3);
  }

  @Test
  public void testRestartRestartsGame() throws FactoryException {
    testController.launchGame("TestFile");
    Rectangle mario = (Rectangle) myGame.getActivePlayer().getNode();
    double initialYPosition = mario.getY();
    testController.getGame().updateLevel();
    assertTrue(mario.getY() > initialYPosition);

    javafxRun(() -> myDisplay.setSplashScreen(GameEndStatus.LOSS));
    Scene splashScene = myStage.getScene();
    Button restart = (Button) splashScene.lookup("#restart");
    assertNotNull(restart);
    javafxRun(() -> restart.fire());

    Rectangle newMario = (Rectangle) myGame.getActivePlayer().getNode();
    assertEquals(initialYPosition,newMario.getY());
  }

  private void setMyGame(Game game){
    myGame = game;
  }

}
