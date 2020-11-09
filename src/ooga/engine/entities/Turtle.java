package ooga.engine.entities;

import javafx.scene.Node;

/**
 * Enemy in mario that gives y velocity when you jump on them
 */
public class Turtle extends Enemy{

  public Turtle(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setId("turtle");
  }

  @Override
  public void topCollideable(Entity entity) {
    System.out.println(entity.getId());
    if (entity.getId() == "player"){
      entity.setYForce(-2000); //use up method once moved to player
  }
}

  @Override
  public Node getNodeObject() {
    return null;
  }
}
