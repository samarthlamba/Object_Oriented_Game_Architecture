package ooga;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.stage.Stage;
import ooga.view.Screen;
import ooga.util.DukeApplicationTest;

public class DriverTest extends DukeApplicationTest {
  private Driver myDriver;
  private Screen myMenu;

  /**
   * Start special test version of application that does not animate on its own before each test.
   * <p>
   * Automatically called @BeforeEach by TestFX.
   */
  @Override
  public void start(Stage stage) throws Exception {
    myDriver = new Driver();
    myDriver.start(stage);
    myMenu = myDriver.getGameMenu();
    // create game's scene with all shapes in their initial positions and show it
    stage.setScene(myMenu.getView());
    stage.show();

  }

}
