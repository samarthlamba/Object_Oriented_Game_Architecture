package ooga.engine.entities;

import javafx.scene.Node;

/**
 * Lost Vikings Basic Enemy
 */
public class Goblin extends Enemy{

  public Goblin(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }


  @Override
  public Node getNodeObject() {
    return null;
  }
}
