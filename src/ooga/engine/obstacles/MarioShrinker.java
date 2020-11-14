package ooga.engine.obstacles;

import javafx.scene.Node;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

/**
 * Powerup in mario that shrinks mario to half size when hit
 */
public class MarioShrinker extends Obstacle {

  public MarioShrinker(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId("shrinker");
  }


  @Override
  public void leftCollideable(Entity entity) {
    scalePlayer(entity);
  }

  @Override
  public void rightCollideable(Entity entity) {
    scalePlayer(entity);
  }

  @Override
  public void bottomCollideable(Entity entity) {
    scalePlayer(entity);
  }

  @Override
  public void topCollideable(Entity entity) {
    scalePlayer(entity);
  }


}
