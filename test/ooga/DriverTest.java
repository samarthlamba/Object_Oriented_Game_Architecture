package ooga;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.engine.games.MarioGame;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class DriverTest extends DukeApplicationTest {
  private Driver myDriver;

  /**
   * Start special test version of application that does not animate on its own before each test.
   * <p>
   * Automatically called @BeforeEach by TestFX.
   */
  @Override
  public void start(Stage stage) throws Exception {
    myDriver = new Driver();
    myDriver.start(stage);
    // create game's scene with all shapes in their initial positions and show it
    stage.setScene(myDriver.myMenu.getView());
    stage.show();

  }

  @Test
  public void testGameIsUpdated() {
    Button startButton = (Button) myDriver.myMenu.getView().getRoot().getChildrenUnmodifiable().get(0);
    clickOn(startButton);
    assertTrue(myDriver.myGame != null);
    assertTrue(myDriver.myGame instanceof MarioGame);
  }

}
