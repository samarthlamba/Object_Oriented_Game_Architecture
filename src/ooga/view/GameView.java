package ooga.view;

import java.util.Collection;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import ooga.engine.entities.Entity;
import ooga.engine.games.Game;
import ooga.engine.obstacles.Obstacle;
import ooga.loader.Painter;

public class GameView extends Screen{

  private final Game myGame;
  private Scene sceneFromGame;

  public GameView(Game gameToShow) {
    myGame = gameToShow;
    makeScene();
  }

  private void makeScene() {
    Pane sceneRoot = new Pane();
    Collection<Obstacle> background = myGame.getBackground();
    background.stream().forEach(obstacle -> {
      Painter.paint((Shape) obstacle.getNodeObject(), obstacle.getClass());
      sceneRoot.getChildren().add(obstacle.getNodeObject());
    });
    Collection<Entity> entities = myGame.getEntities();
    entities.stream().forEach(entity -> {
      Painter.paint((Shape) entity.getNode(), entity.getClass());
      sceneRoot.getChildren().add(entity.getNode());
    });
    sceneFromGame = new Scene(sceneRoot);
  }

  @Override
  public Scene getView() {
    return sceneFromGame;

  }
}
