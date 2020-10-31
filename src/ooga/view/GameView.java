package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ooga.engine.Games.Game;

public class GameView extends Screen{

  private final Game myGame;

  public GameView(Game gameToShow) {
    myGame = gameToShow;
  }

  @Override
  public Scene getView() {
    Group root = new Group();
    Rectangle bigAssRectangle = new Rectangle(100,100,100,100);
    bigAssRectangle.setFill(Color.RED);
    root.getChildren().add(bigAssRectangle);
    return new Scene(root,800,600);
  }
}
