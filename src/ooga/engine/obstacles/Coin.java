package ooga.engine.obstacles;

import javafx.scene.Node;
import ooga.engine.obstacles.Obstacle;

/**
 * Gives mario coin when hit
 */
public class Coin extends Obstacle {

  public Coin(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
  }

  @Override
  public Node getNodeObject() {
    return null;
  }
}
