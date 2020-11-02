package ooga.engine.entities;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public abstract class Entity extends Node implements Moveables {
  private final int SCENE_WIDTH;
  private final int SCENE_HEIGHT;
  private int currentHitpoints;
  private Node nodeObject;
  private double speed = 0;
  private static final int JUMP_CAPACITY = -4;
  private double previousX;
  private double previousY;
  private double jumpCapacity = 0;


  public Entity(int objectWidth,int objectHeight,  double initialX, double initialY) {
    this.SCENE_WIDTH = objectWidth;
    this.SCENE_HEIGHT = objectHeight;
    nodeObject = new Rectangle(initialX, initialY, objectWidth, objectHeight);
    this.previousX = initialX;
    this.previousY = initialY;
      this.setCenterX(initialX);
      this.setMaxY(initialY);
  }

  public Node getNode() {
    return nodeObject;
  }

  public abstract int getID();

  public double getVelocityX(){
    return speed;
  }

  public double getVelocityY(){
    return jumpCapacity;
  }

  public double getJumpMax(){
      return JUMP_CAPACITY;
  }

 /* public double mass(){
    return 5;
  }*/
  public void setVelocityX(double x){
    this.speed = x;
  }

  public void setVelocityY(double y){
    this.jumpCapacity = y;
  }

  public void setCenterX(double inputX){
   nodeObject.setLayoutX(inputX - nodeObject.getLayoutBounds().getCenterX());
  }

  public void setLeftX(double inputX){
      nodeObject.setLayoutX(inputX - nodeObject.getLayoutBounds().getMinX());
  }

    public void setRightX(double inputX){
        nodeObject.setLayoutX(inputX - nodeObject.getLayoutBounds().getMaxX());
    }

  public void setMaxY(double inputY){
      nodeObject.setLayoutY(inputY - nodeObject.getLayoutBounds().getMaxY());
    //nodeObject.setLayoutY(inputY+nodeObject.getLayoutY());
  }

    public void setMinY(double inputY){
        nodeObject.setLayoutY(inputY - nodeObject.getLayoutBounds().getMinY());
        //nodeObject.setLayoutY(inputY+nodeObject.getLayoutY());
    }

  public void setHitpoints(int hitpoints){
    currentHitpoints=hitpoints;
  }

  public int getHitpoints(){
    return currentHitpoints;
  }

  public void setPreviousX(double previous){
    previousX = previous;
  }

  public double getPreviousX(){
    return previousX;
  }

  public void setPreviousY(double previous){
    previousY = previous;
  }

  public double getPreviousY(){
    return previousY;
  }

 /* public double getX(){
      return nodeObject.getLayoutX();
  }*/

  public double getCenterX(){
    //  return nodeObject.getLayoutY();
      return nodeObject.getBoundsInParent().getCenterX();
  }

  public double getXLeft(){
      return nodeObject.getBoundsInParent().getMinX();
  }

  public double getXRight(){
      return nodeObject.getBoundsInParent().getMaxX();
  }

  public double getMaxY(){
      return nodeObject.getBoundsInParent().getMaxY();
  }

  //add id.
}
