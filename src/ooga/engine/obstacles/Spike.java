package ooga.engine.obstacles;

import javafx.scene.Node;

/**
 * Metroid obstacle that kills Samus on hit
 */
public class Spike extends Obstacle{

  public Spike(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
  }

  @Override
  public Node getNodeObject() {
    return null;
  }
}
