package ooga.engine.entities;

import javafx.scene.Node;

/**
 * Enemy in mario that gives y velocity when you jump on them
 */
public class Turtle extends Enemy{

  public Turtle(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }

  @Override
  public void topCollideable(Entity entity) {
    if (entity.getId() == "player"){
      entity.setVelocityY(20);
  }
}

  @Override
  public Node getNodeObject() {
    return null;
  }
}
