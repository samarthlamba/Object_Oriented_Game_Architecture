package ooga.view;

import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.Scene;
import ooga.GameController;
import ooga.TimelineManager;

public class SplashScreen extends Screen{
  private final String displayKey;
  private final Scene myScene;
  private final Runnable restart;
  private final Runnable setMainMenu;

  public SplashScreen (String displayKey,Runnable setMainMenu, Runnable restartGame) {
    this.displayKey = displayKey;
    this.restart = restartGame;
    this.setMainMenu = setMainMenu;
    myScene = new Scene(makeBMenu(),SCREEN_WIDTH,SCREEN_HEIGHT);
  }

  private void doCorrectThing(String buttonName) {
    System.out.println(buttonName);
    if(buttonName.equals("Restart")) {
      restart.run();
    }
    if(buttonName.equals("MainMenu")) {
      setMainMenu.run();
    }
  }

  private Menu makeBMenu() {
    Menu myMenu = new Menu(ResourceBundle.getBundle("SplashButtons"),this::doCorrectThing);
    return myMenu;
  }

  @Override
  public Scene getView() {
    return myScene;
  }
}
