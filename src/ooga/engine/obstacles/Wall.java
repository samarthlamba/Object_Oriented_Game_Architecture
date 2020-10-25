package ooga.engine.obstacles;

import org.w3c.dom.Node;

public class Wall extends Obstacle{

  public Wall(int sceneWidth, int sceneHeight) {
    super(sceneWidth, sceneHeight);
  }

  @Override
  public boolean hasCollided(Node node) {
    return false;
  }
}
