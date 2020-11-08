package ooga.engine.obstacles;

import javafx.scene.Node;

/**
 * Gives mario coin when hit
 */
public class Coin extends Obstacle {

  public Coin(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
  }

  @Override
  public Node getNodeObject() {
    return this;
  }

}
