package ooga.engine.entities;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public abstract class Entity implements Moveables {
  private final int SCENE_WIDTH;
  private final int SCENE_HEIGHT;
  private int currentHitpoints;
  private Node nodeObject;
  private double speed = 5;
  private static final int JUMP_CAPACITY = 10;
  public Entity(int objectWidth,int objectHeight,  double initialX, double initialY) {
    this.SCENE_WIDTH = objectWidth;
    this.SCENE_HEIGHT = objectHeight;
    this.setX(initialX);
    this.setX(initialY);
    nodeObject = new Rectangle(objectWidth, objectHeight);
  }

  @Override
  public Node getNode() {
    return nodeObject;
  }

  public abstract int getID();

  public double getVelocityX(){
    return speed;
  }

  public double getVelocityY(){
    return JUMP_CAPACITY;
  }

  public double mass(){
    return 5;
  }
  public void setVelocityX(double x){
    this.speed = x;
  }


  public void setX(double inputX){

    nodeObject.setLayoutX(inputX+nodeObject.getLayoutX());
  }

  public void setY(double inputY){
    nodeObject.setLayoutY(inputY+nodeObject.getLayoutY());
  }

  public void setHitpoints(int hitpoints){
    currentHitpoints=hitpoints;
  }

  public int getHitpoints(){
    return currentHitpoints;
  }

  //add id.
}
