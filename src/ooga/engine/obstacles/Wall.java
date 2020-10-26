package ooga.engine.obstacles;

import org.w3c.dom.Node;

public class Wall extends Obstacle{

  public Wall(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
  }

  @Override
  public boolean hasCollided(Node node) {
    return false;
  }
}
