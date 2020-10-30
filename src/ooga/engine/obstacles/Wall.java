package ooga.engine.obstacles;


import javafx.scene.Node;

public class Wall extends Obstacle{

  public Wall(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
  }

  @Override
  public boolean hasCollided(Node node) {
    return false;
  }


  @Override
  public double getMass() {
    return 0;
  }
}
