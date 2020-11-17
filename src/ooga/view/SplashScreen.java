package ooga.view;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SplashScreen extends Screen{
  private final String displayKey;
 // private final Scene myScene;
  private final Runnable restart;
  private final Runnable setMainMenu;

  public SplashScreen (String displayKey,Runnable setMainMenu, Runnable restartGame) {
    this.displayKey = displayKey;
    this.restart = restartGame;
    this.setMainMenu = setMainMenu;
    //instead of just the menu, you could add the menu at the bottom only.
    //this.myScene = new Scene(makeMenu(),SCREEN_WIDTH,SCREEN_HEIGHT);
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
            new KeyFrame(Duration.seconds(2), e -> {}));

    timeline.play();
    return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  private ImageView getGif(){
    String gifPath = "/images/" + displayKey + "Screen.gif";
    return new ImageView(gifPath);
  }
}
