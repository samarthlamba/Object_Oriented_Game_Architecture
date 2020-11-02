package ooga.view;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collection;
import javafx.scene.Scene;
import ooga.engine.entities.Entity;
import ooga.engine.games.Game;
import ooga.engine.obstacles.Obstacle;
import ooga.loader.GameFactory;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class GameViewTest extends DukeApplicationTest{

  @Test
  public void testGameViewMakesSceneWithAllObjects() {
    GameFactory gameMaker = new GameFactory();
    Game myGame = gameMaker.makeCorrectGame("testFile.csv");
    javafxRun(() -> {
      GameView myView = new GameView(myGame);
      Scene myScene = myView.getView();
      Collection<Obstacle> obstacles = myGame.getBackground();
      Collection<Entity> entities = myGame.getEntities();
      obstacles.stream().forEach(obstacle -> {
        assertTrue(myScene.getRoot().getChildrenUnmodifiable().contains(obstacle.getNodeObject()));
      });
      entities.stream().forEach(entity -> {
        assertTrue(myScene.getRoot().getChildrenUnmodifiable().contains(entity.getNode()));
      });
    });
  }

}
