package ooga.engine.entities;

import javafx.scene.Node;

/**
 * Metroid basic enemy
 */
public class SpacePirate extends Enemy{

  public SpacePirate(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }


  @Override
  public Node getNodeObject() {
    return null;
  }
}
