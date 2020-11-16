package ooga.view;

import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.Scene;

public class SplashScreen extends Screen{
  private final String displayKey;
  private final Scene myScene;
  private final Runnable restart;
  private final Runnable setMainMenu;

  public SplashScreen (String displayKey,Runnable setMainMenu, Runnable restartGame) {
    this.displayKey = displayKey;
    this.restart = restartGame;
    this.setMainMenu = setMainMenu;
    this.myScene = new Scene(makeMenu(),SCREEN_WIDTH,SCREEN_HEIGHT);
  }

  private void invokeCorrectMethod(String buttonName) {
    try{
      Method correctMethod = this.getClass().getDeclaredMethod(buttonName);
      correctMethod.invoke(this);
    } catch (Exception e) {
      setMainMenu.run();
    }
  }
  private Menu makeMenu() {
    Menu myMenu = new Menu(ResourceBundle.getBundle("SplashButtons"),this::invokeCorrectMethod);
    return myMenu;
  }

  private void mainMenu() {
    setMainMenu.run();
  }

  private void restart() {
    restart.run();
  }

  @Override
  //HERE ROSHNI MAKES GIFS USING DISPLAYKEY TO SELECT CORRECT ONE
  public Scene getView() {
    return myScene;
  }
}
