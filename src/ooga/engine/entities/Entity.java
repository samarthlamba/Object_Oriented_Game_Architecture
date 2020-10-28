package ooga.engine.entities;

import javafx.scene.Node;

public abstract class Entity extends Node implements Moveables {
  private final int SCENE_WIDTH;
  private final int SCENE_HEIGHT;
  public Entity(int sceneWidth,int sceneHeight,  double initialX, double initialY) {
    this.SCENE_WIDTH = sceneWidth;
    this.SCENE_HEIGHT = sceneHeight;
    this.setX(initialX);
    this.setX(initialY);
  }
}
