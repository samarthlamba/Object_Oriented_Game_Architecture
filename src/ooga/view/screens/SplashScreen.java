package ooga.view.screens;

import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
//<<<<<<< HEAD:src/ooga/view/SplashScreen.java
import javafx.geometry.Pos;
//=======
//>>>>>>> jnh24:src/ooga/view/screens/SplashScreen.java
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import ooga.GameEndStatus;

//<<<<<<< HEAD:src/ooga/view/SplashScreen.java
/**
 * A screen that displays a GIF based on the way the game ended, with buttons to set main menu and
 * restart the game
 */
public class SplashScreen extends Screen{
//=======
//public class SplashScreen extends Screen {
//>>>>>>> jnh24:src/ooga/view/screens/SplashScreen.java
  private final String displayKey;
  private final Runnable restart;
  private final Runnable setMainMenu;

  public SplashScreen (GameEndStatus displayKey,Runnable setMainMenu, Runnable restartGame) {
    this.displayKey = displayKey.toString();
    this.restart = restartGame;
    this.setMainMenu = setMainMenu;
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
    myMenu.setAlignment(Pos.CENTER);
    return myMenu;
  }

  private void mainMenu() {
    setMainMenu.run();
  }

  private void restart() {
    restart.run();
  }

  /**
   * Called from Display to give the scene from splash screen to the stage.
   * @return the scene for this splash screen, with a gif and restart/main menu buttons on it.
   */
  @Override
  public Scene getView() {
    BorderPane root = new BorderPane();
      ImageView gif = getGif();
    Timeline timeline = new Timeline(
        new KeyFrame(Duration.ZERO, e -> {
          gif.setFitWidth(SCREEN_WIDTH);
          gif.setFitHeight(SCREEN_HEIGHT);
          gif.setPreserveRatio(false);
          root.getChildren().add(gif);
          root.setTop(makeMenu());
        }),
        new KeyFrame(Duration.seconds(2), e -> {
        }));
    timeline.play();
    Scene currentScene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
    currentScene.getStylesheets().addAll("gamebase.css");
    return currentScene;
  }

  private ImageView getGif(){
    String gifPath = "/images/" + displayKey + "Screen.gif";
    return new ImageView(gifPath);
  }
}
