package ooga.engine.entities;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public abstract class Enemy extends Entity{
  private final static int ID = 1;

  public Enemy(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }

  public int getID(){
    return this.ID;
  }

}
