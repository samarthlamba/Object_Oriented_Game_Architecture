package ooga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.engine.games.Game;
import ooga.loader.FactoryException;
import ooga.loader.GameFactory;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Test;

public class DriverTest extends DukeApplicationTest {
  private Stage myStage;
  private Driver myDriver;
  private GameFactory factory = new GameFactory();

  @Override
  public void start(Stage stage) throws Exception {
    myStage = stage;
    myDriver = new Driver();
    myDriver.start(myStage);
  }

  @Test
  public void testInitialSceneIsMainMenu() {
    Scene mainMenuScene = myStage.getScene();
    Button game1 = (Button) mainMenuScene.lookup("#g1");
    assertNotNull(game1);
    Button game2 = (Button) mainMenuScene.lookup("#g2");
    assertNotNull(game2);
    Button game3 = (Button) mainMenuScene.lookup("#g3");
    assertNotNull(game3);
  }

  @Test
  public void testPressingButtonMovesToGameMenu() {
    Scene mainMenuScene = myStage.getScene();
    Button game1 = (Button) mainMenuScene.lookup("#g1");
    assertNotNull(game1);
    javafxRun(() -> game1.fire());
    Scene gameMenuScene = myStage.getScene();
    assertNotEquals(mainMenuScene,gameMenuScene);
    Button l1 = (Button) gameMenuScene.lookup("#l1");
    assertNotNull(l1);
    Button l2 = (Button) gameMenuScene.lookup("#l2");
    assertNotNull(l2);
    Button l3 = (Button) gameMenuScene.lookup("#l3");
    assertNotNull(l3);
  }

  @Test
  public void testPressingButtonMovesToGame() {
    Scene mainMenuScene = myStage.getScene();
    Button game1 = (Button) mainMenuScene.lookup("#g1");
    assertNotNull(game1);
    javafxRun(() -> game1.fire());
    Scene gameMenuScene = myStage.getScene();
    Button l1 = (Button) gameMenuScene.lookup("#l1");
    assertNotNull(l1);
    javafxRun(() -> l1.fire());
    Scene gameScene = myStage.getScene();
    assertNotEquals(gameScene,gameMenuScene);
  }

  @Test
  public void testPressingRandomButtonMovesToGame() {
    Scene mainMenuScene = myStage.getScene();
    Button game1 = (Button) mainMenuScene.lookup("#g1");
    assertNotNull(game1);
    javafxRun(() -> game1.fire());
    Scene gameMenuScene = myStage.getScene();
    Button random = (Button) gameMenuScene.lookup("#random");
    assertNotNull(random);
    javafxRun(() -> random.fire());
    Scene gameScene = myStage.getScene();
    assertNotEquals(gameScene,gameMenuScene);
  }

  @Test
  public void testVictoryWithDummyLevel() throws FactoryException {
    Scene mainMenuScene = myStage.getScene();
    Button game1 = (Button) mainMenuScene.lookup("#g1");
    assertNotNull(game1);
    javafxRun(() -> game1.fire());
    Scene gameMenuScene = myStage.getScene();
    assertNotEquals(mainMenuScene,gameMenuScene);
    Button l1 = (Button) gameMenuScene.lookup("#l1");
    assertNotNull(l1);
    Game easyVictoryGame = factory.makeCorrectGame("testVictory");
    EventHandler<ActionEvent> previousAction = l1.getOnAction();
    l1.setOnAction(e -> {
      previousAction.handle(e);
      myDriver.setGame(easyVictoryGame);
    });
    Scene gameScene = myStage.getScene();

    javafxRun(() -> l1.fire());
    sleep(200);

    Scene victoryScene = myStage.getScene();
    assertNotEquals(victoryScene,gameScene);

    Button mainMenu = (Button) victoryScene.lookup("#mainMenu");
    assertNotNull(mainMenu);
    assertEquals("Main Menu",mainMenu.getText());
    Button restart = (Button) victoryScene.lookup("#restart");
    assertNotNull(restart);
    assertEquals("Restart",restart.getText());
  }

  @Test
  public void testLossWithDummyLevel() throws FactoryException {
    Scene mainMenuScene = myStage.getScene();
    Button game1 = (Button) mainMenuScene.lookup("#g1");
    assertNotNull(game1);
    javafxRun(() -> game1.fire());
    Scene gameMenuScene = myStage.getScene();
    assertNotEquals(mainMenuScene,gameMenuScene);
    Button l1 = (Button) gameMenuScene.lookup("#l1");
    assertNotNull(l1);
    Game instantLossGame = factory.makeCorrectGame("testDefeat");
    EventHandler<ActionEvent> previousAction = l1.getOnAction();
    l1.setOnAction(e -> {
      previousAction.handle(e);
      myDriver.setGame(instantLossGame);
    });
    Scene gameScene = myStage.getScene();

    javafxRun(() -> l1.fire());
    sleep(500);

    Scene defeatScene = myStage.getScene();
    assertNotEquals(defeatScene,gameScene);
    Button mainMenu = (Button) defeatScene.lookup("#mainMenu");
    assertNotNull(mainMenu);
    assertEquals("Main Menu",mainMenu.getText());
    Button restart = (Button) defeatScene.lookup("#restart");
    assertNotNull(restart);
    assertEquals("Restart",restart.getText());
  }



}
