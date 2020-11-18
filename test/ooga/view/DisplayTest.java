package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import ooga.GameController;
import ooga.GameEndStatus;
import ooga.engine.games.Game;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Test;

public class DisplayTest extends DukeApplicationTest {

  private Stage myStage;
  private Display myDisplay;
  private Game myGame;



  @Override
  public void start(Stage stage) throws Exception {
    myStage = stage;
    myStage.show();
    Timeline testTimeline = new Timeline();
    testTimeline.setCycleCount(5);
    GameController testController = new GameController(stage,testTimeline,this::setMyGame);
    myDisplay = new Display(testController);
  }

  @Test
  public void testAlertIsShownOnFactoryException() {
    javafxRun(() -> myDisplay.setSplashScreen(GameEndStatus.VICTORY));
    Scene defeatScene = myStage.getScene();
    Button restart = (Button) defeatScene.lookup("#restart");
    assertNotNull(restart);
    assertEquals("Restart",restart.getText());
    javafxRun(() -> restart.fire());
    List<Window> windows = robotContext().getWindowFinder().listWindows();
    assertEquals(2,windows.size());
  }

  public void setMyGame(Game myGame) {
    this.myGame = myGame;
  }


}
