package ooga.engine.entities;

public abstract class Entity implements Moveables {
  private final int SCENE_WIDTH;
  private final int SCENE_HEIGHT;
  public Entity(int sceneWidth,int sceneHeight,  double initialX, double initialY) {
    this.SCENE_WIDTH = sceneWidth;
    this.SCENE_HEIGHT = sceneHeight;
    this.setX(initialX);
    this.setX(initialY);
  }
}
