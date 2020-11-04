package ooga.view;

import java.util.Collection;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Mario;
import ooga.engine.entities.Player;
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
    Player player = new Mario(1,1,1,1);
    for(Entity each : entities) {
      if(each instanceof Player) {
        player = (Player) each;
      }
    }
    entities.stream().forEach(entity -> {
      Painter.paint((Shape) entity.getNode(), entity.getClass());
      sceneRoot.getChildren().add(entity.getNode());
    });
    player.getNode().translateXProperty().addListener((obs,old,newValue)-> {
      int offset = newValue.intValue();
      System.out.println(offset);
      if(offset > 200) {
        System.out.println("hey look");
        sceneRoot.setLayoutX(-(offset-200));
      }
    });
    sceneFromGame = new Scene(sceneRoot,800,600);
    sceneFromGame.onKeyPressedProperty().bind(player.onKeyPressedProperty());
  }

  @Override
  public Scene getView() {
    return sceneFromGame;

  }
}
