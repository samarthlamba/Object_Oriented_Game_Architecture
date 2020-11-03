package ooga.engine.obstacles;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public abstract class Obstacle extends Rectangle{
  private double moveX = 0;
  private double moveY = 0;
  private double initialX;
  private double initialY;
  private boolean reached;
  public Obstacle(int obstacleWidth,int obstacleHeight, double initialX, double initialY) {
    this.initialX = initialX;
    this.initialY= initialY;
    reached = false;
    setX(initialX);
    setY(initialY);
    setWidth(obstacleWidth);
    setHeight(obstacleHeight);
   // nodeObject = new Rectangle(initialX, initialY, obstacleWidth, obstacleHeight);
  }

  public void moveXBy(double x){
    relocate(getBoundsInParent().getMinX()+x,getBoundsInParent().getMinY());
    System.out.println(getBoundsInParent());
  }

  private double getCurrentX(){
    return  getBoundsInParent().getCenterX();
  }
  private double getCurrentY(){
    return getBoundsInParent().getCenterY();
  }

  public void update() {
    //move up and down
  }

  public void moveYBy(double y){
    relocate(getBoundsInParent().getMinX(), getBoundsInParent().getMinY()+y);
    System.out.println(getBoundsInParent());
  }

  public void moveContinouslyXBy(double X){
    moveX = X;
  }

  public void moveContinouslyYBy(double Y){
    moveY = Y;
  }

  public abstract Node getNodeObject();
}
