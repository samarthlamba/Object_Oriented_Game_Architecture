package ooga.engine.obstacles;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public abstract class Obstacle{
  private final Node nodeObject;
  private double moveX = 0;
  private double moveY = 0;
  private double initialX;
  private double initialY;
  private boolean reached;
  public Obstacle(int obstacleWidth,int obstacleHeight, double initialX, double initialY) {
    this.initialX = initialX;
    this.initialY= initialY;
    reached = false;
    nodeObject = new Rectangle(initialX, initialY, obstacleWidth, obstacleHeight);
  }

  public void moveXBy(double x){
    nodeObject.relocate(nodeObject.getBoundsInParent().getMinX()+x, nodeObject.getBoundsInParent().getMinY());
    System.out.println(nodeObject.getBoundsInParent());
  }

  private double getCurrentX(){
    return  nodeObject.getBoundsInParent().getCenterX();
  }
  private double getCurrentY(){
    return nodeObject.getBoundsInParent().getCenterY();
  }

  public void update() {
    //move up and down
  }

  public void moveYBy(double y){
    nodeObject.relocate(nodeObject.getBoundsInParent().getMinX(), nodeObject.getBoundsInParent().getMinY()+y);
    System.out.println(nodeObject.getBoundsInParent());
  }

  public void moveContinouslyXBy(double X){
    moveX = X;
  }

  public void moveContinouslyYBy(double Y){
    moveY = Y;
  }


  public Node getNodeObject() {
    return nodeObject;
  }
}
