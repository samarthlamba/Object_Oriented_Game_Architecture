package ooga.engine.obstacles;

import javafx.scene.Node;

/**
 * Vikings obstacle that is collideable from top only and whose "flow" is blocked by non-active viking
 */
public class Waterfall extends Obstacle {

  public Waterfall(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId("waterfall");
  }

}
