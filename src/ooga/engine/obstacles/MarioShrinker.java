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



}
