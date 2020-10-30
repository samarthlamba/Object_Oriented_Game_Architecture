package ooga.engine.entities;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public abstract class Enemy extends Entity{
  private final static int ID = 1;

  public Enemy(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
  }

  public int getID(){
    return this.ID;
  }

}
