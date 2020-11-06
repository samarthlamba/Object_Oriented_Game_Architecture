package ooga.engine.obstacles;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.entities.Moveables;

import java.util.Map;

public abstract class Obstacle extends Rectangle implements Collideable{
  private static final double MOVE_FORCE = 1000;
  private static final double NEGATIVE_DIRECTION = -1;
  public static final double GRAVITY = 9.8;
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

  public abstract Map<String, String> getCollisionRules();

  public void leftCollideable(Moveables entity) {
    System.out.println("left");
    entity.setXForce(entity.getXForce() - MOVE_FORCE);
    entity.setCenterX(getBoundsInParent().getMaxX() + entity.getEntityWidth() / 2);
  }


  public void rightCollideable(Moveables entity) {
    System.out.println("right");
    entity.setXForce(entity.getXForce() + MOVE_FORCE);
    entity.setCenterX(getBoundsInParent().getMinX() - entity.getEntityWidth() / 2);
  }

  public void bottomCollideable(Moveables entity) {
    System.out.println("bottom");
    //entity.setMaxY(getBoundsInParent().getMaxY() + entity.getEntityHeight());
    entity.setYForce(GRAVITY);
    entity.setVelocityY(-entity.getVelocityY());
  }

  public void topCollideable(Moveables entity) {
    entity.setMaxY(getBoundsInParent().getMinY());
    entity.setYForce(entity.getYForce() + NEGATIVE_DIRECTION * GRAVITY);
    System.out.println("top");
      entity.setTimeElapsedY(entity.getTimeElapsedX());
     //elapsedTime = dt;
      entity.setVelocityY(0);
      entity.setJump(false);
     // jump = false;
  }
}
