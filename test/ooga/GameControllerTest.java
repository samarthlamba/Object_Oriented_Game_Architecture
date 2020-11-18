package ooga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;
import javafx.animation.Animation.Status;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.engine.entities.Movable;
import ooga.engine.entities.MovableBounds;
import ooga.engine.entities.player.Mario;
import ooga.engine.games.Game;
import ooga.loader.FactoryException;
import ooga.loader.GameFactory;
import ooga.util.DukeApplicationTest;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.junit.jupiter.api.Test;

public class GameControllerTest extends DukeApplicationTest{
  private Timeline testTimeline;
  private GameFactory testFactory;
  private GameController testController;
  private Rectangle testRectangle;
  private Game myGame;
  private Stage myStage;

  @Override
  public void start(Stage stage) {
    testRectangle = new Rectangle(50,50, Color.RED);
    testFactory = new GameFactory();
    Scene myScene = new Scene(new Group(testRectangle),200,200);
    myStage = stage;
    myStage.setScene(myScene);
    myStage.show();
    testTimeline = new Timeline();
    testTimeline.setCycleCount(5);
    testController = new GameController(stage,testTimeline,this::setMyGame);
  }


  @Test
  public void testTimelineRunsOnPlay() {
    final double initialX = testRectangle.getX();
    final double initialY = testRectangle.getY();
    KeyFrame moveRectangle = new KeyFrame(Duration.millis(100),e -> {
      testRectangle.setX(testRectangle.getX()+10);
    });
    testTimeline.getKeyFrames().add(moveRectangle);
    testController.playTimeline();
    sleep(600);
    final double finalX = testRectangle.getX();
    final double finalY = testRectangle.getY();
    assertEquals(initialX+50, finalX);
    assertEquals(initialY, finalY);
  }

  @Test
  public void testTimelineStopsOnPause() {
    final double initialX = testRectangle.getX();
    final double initialY = testRectangle.getY();
    KeyFrame moveRectangle = new KeyFrame(Duration.millis(100),e -> {
      testRectangle.setX(testRectangle.getX()+10);
    });
    testTimeline.getKeyFrames().add(moveRectangle);
    testController.playTimeline();
    sleep(150);
    testController.pauseTimeline();
    final double finalX = testRectangle.getX();
    final double finalY = testRectangle.getY();
    assertEquals(initialX+10, finalX);
    assertEquals(initialY, finalY);
  }

  @Test
  public void testLaunchGameChangesMyGame() throws FactoryException {
    assertNull(myGame);
    testController.launchGame("TestFile");

    assertNotNull(myGame);
    Game testFileGame = testFactory.makeCorrectGame("TestFile");

    Collection<? extends MovableBounds> entitiesFromTestFile = testFileGame.getEntities();
    Collection<? extends MovableBounds> entitiesFromMyGame = myGame.getEntities();
    assertEquals(entitiesFromTestFile.size(),entitiesFromMyGame.size());

    Collection<Node> obstaclesFromTestFile = testFileGame.getBackground();
    Collection<Node> obstaclesFromMyGame = myGame.getBackground();
    assertEquals(obstaclesFromTestFile.size(),obstaclesFromMyGame.size());
    assertTrue(myGame.getActivePlayer() instanceof Mario);
  }

  @Test
  public void testLaunchGamePlaysTimeline() throws FactoryException {
    KeyFrame autoPass = new KeyFrame(Duration.millis(100),e -> {
      assertTrue(true);
    });
    testTimeline.getKeyFrames().add(autoPass);
    testController.launchGame("TestFile");
    assertEquals(Status.RUNNING,testTimeline.getStatus());
    sleep(600);
    assertEquals(Status.STOPPED,testTimeline.getStatus());
  }

  @Test
  public void testGetGameReturnsMyGame() throws FactoryException {
    testController.launchGame("TestFile");
    assertEquals(myGame,testController.getGame());
    assertNotNull(myGame);
  }

  @Test
  public void testRestartGameRestartsGame() throws FactoryException {
    testController.launchGame("TestFile");
    Rectangle mario = (Rectangle) myGame.getActivePlayer().getNode();
    double initialYPosition = mario.getY();
    testController.getGame().updateLevel();
    assertTrue(mario.getY() > initialYPosition);
    testController.restartGame();
    Rectangle newMario = (Rectangle) myGame.getActivePlayer().getNode();
    assertEquals(initialYPosition,newMario.getY());
  }

  @Test
  public void testSetSceneChangesScene() {
    Group rootOfScene = new Group();
    Scene randomScene = new Scene(rootOfScene);
    javafxRun(() -> testController.setScene(randomScene));
    assertEquals(randomScene,myStage.getScene());
  }

  @Test
  public void testGetGameNameReturnsCorrectName() throws FactoryException {
    testController.launchGame("TestFile");
    assertEquals("TestFile",testController.getGameName());
  }

  @Test
  public void testRestartGameAfterRandomDoesNotThrowException() {
    try {
      testController.makeRandomGame("Mario");
      testController.restartGame();
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testGetPathToCurrentLevelWhenRandomReturnsRandom() throws FactoryException {
    testController.makeRandomGame("Mario");
    assertEquals("random",testController.getGameName());
  }


  public void setMyGame(Game myGame) {
    this.myGame = myGame;
  }
}
