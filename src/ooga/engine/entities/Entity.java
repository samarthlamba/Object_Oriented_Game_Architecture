package ooga.engine.entities;

import javafx.scene.Node;

public abstract class Entity implements Moveables {
  private final int SCENE_WIDTH;
  private final int SCENE_HEIGHT;
  private double currentX;
  private double currentY;
  private int currentHitpoints;
  public Entity(int sceneWidth,int sceneHeight,  double initialX, double initialY) {
    this.SCENE_WIDTH = sceneWidth;
    this.SCENE_HEIGHT = sceneHeight;
    this.setX(initialX);
    this.setX(initialY);
  }
  public abstract int getID();
  public double getX(){
    return this.currentX;
  }

  public double getY(){
    return this.currentY;
  }

  public void setX(double inputX){
    currentX= inputX;
  }

  public void setY(double inputY){
    currentY=inputY;
  }

  public void setHitpoints(int hitpoints){
    currentHitpoints=hitpoints;
  }

  public int getHitpoints(){
    return currentHitpoints;
  }

  //add id.
}
