package ooga.engine.entities;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public abstract class Entity extends Node implements Moveables {
  private final int SCENE_WIDTH;
  private final int SCENE_HEIGHT;
  private int currentHitpoints;
  private Node nodeObject;
  private double speed = 5;
  private static final int JUMP_CAPACITY = 10;
  private double previousX = 0;
  private double previousY = 0;


  public Entity(int objectWidth,int objectHeight,  double initialX, double initialY) {
    this.SCENE_WIDTH = objectWidth;
    this.SCENE_HEIGHT = objectHeight;
    nodeObject = new Rectangle(initialX, initialY, objectWidth, objectHeight);
      this.setX(initialX);
      this.setY(initialY);
  }

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

 /* public double mass(){
    return 5;
  }*/
  public void setVelocityX(double x){
    this.speed = x;
  }

  public void setVelocityY(double y){
    this.speed = y;
  }

  public void setX(double inputX){
    //nodeObject.setLayoutX(inputX+nodeObject.getLayoutX());
      //nodeObject.setTranslateX();
      //nodeObject.relocate(10, 10);
   nodeObject.setLayoutX(inputX - nodeObject.getLayoutBounds().getCenterX());
   System.out.println(nodeObject.getBoundsInParent());
  }

  public void setY(double inputY){
      nodeObject.setLayoutY(inputY - nodeObject.getLayoutBounds().getCenterY());
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

  public double getX(){
      return nodeObject.getBoundsInParent().getCenterX();
  }

  public double getY(){
      return nodeObject.getBoundsInParent().getCenterY();
  }

  //add id.
}
