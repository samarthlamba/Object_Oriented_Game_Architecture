package ooga.engine.obstacles;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public abstract class Obstacle implements Collideable {
  private final Node nodeObject;
  private double moveX = 0;
  private double moveY = 0;
  private double initialX;
  private double initialY;
  private boolean reached;
  public Obstacle(int sceneWidth,int sceneHeight, double initialX, double initialY) {
    this.initialX = initialX;
    this.initialY= initialY;
    reached = false;
    nodeObject = new Rectangle(initialX, initialY, sceneWidth, sceneHeight);
  }

  public void moveX(double x){
    nodeObject.setLayoutX(nodeObject.getLayoutX()+x);
  }

  private double getCurrentX(){
    return nodeObject.getLayoutX();
  }
  private double getCurrentY(){
    return nodeObject.getLayoutY();
  }

  public void update() {
    //move up and down
  }

  public double obstacleBouncerValue(){
    return 5;
  }

  public void moveY(double y){
    nodeObject.setLayoutY(nodeObject.getLayoutY()+y);
  }

  public void moveContinouslyX(double X){
    moveX = X;
  }

  public void moveContinouslyY(double Y){
    moveY = Y;
  }


  public Node getNodeObject() {
    return nodeObject;
  }
}
