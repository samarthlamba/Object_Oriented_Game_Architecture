package ooga.engine.entities;

import javafx.scene.Node;

public abstract class Enemy extends Entity{

  public Enemy(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
  }

}
