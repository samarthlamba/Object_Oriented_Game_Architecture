package ooga.engine.entities;

import javafx.scene.Node;

/**
 * Lost Vikings Implementation of player
 */
public class Viking extends Player{

  public Viking(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
  }


  @Override
  public Node getNodeObject() {
    return null;
  }
}
