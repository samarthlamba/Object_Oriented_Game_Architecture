package ooga.engine.obstacles;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.entities.Entity;
import ooga.engine.games.Collideable;

public abstract class Obstacle extends Rectangle implements Collideable, Unmovable {
  private static final double MOVE_FORCE = 1000;
  private static final double NEGATIVE_DIRECTION = -1;
  public double gravity = 59900;
  private double moveX = 0;
  private double moveY = 0;
  private double initialX;
  private double initialY;
  private boolean reached;
  private boolean left = false;
  private boolean right = false;
  private boolean hasShrunk = false;

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

  public Node getNode(){
    return (Node) this;
  }

  public Collideable getCollideable(){
    return (Collideable) this;
  }

  public void moveXBy(double x){
    relocate(getBoundsInParent().getMinX()+x,getBoundsInParent().getMinY());
    System.out.println(getBoundsInParent());
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


  public void leftCollideable(Entity entity) {
    removeWeapon(entity);
    leftCollide(entity);
  }


  public void rightCollideable(Entity entity) {
    removeWeapon(entity);
    rightCollide(entity);
  }


  public void bottomCollideable(Entity entity) {
    removeWeapon(entity);
    bottomCollide(entity);
  }

  public void topCollideable(Entity entity) {
    removeWeapon(entity);
    topCollide(entity);
  }

  private void topCollide(Entity entity) {
    entity.setMaxY(getBoundsInParent().getMinY());
    entity.setYForce(entity.getYForce() + NEGATIVE_DIRECTION * gravity);
    entity.setVelocityY(0);
    entity.setJump(false);
  }

  private void removeWeapon(Entity entity){
    if (entity.getId().equals("bullet") || entity.getId().equals("arrow")) {
       entity.setHitpoints(0);
    }
  }

  private void leftCollide(Entity entity) {
    entity.setXForce(0);
    entity.setCenterX(entity.getCenterX() + 1);
    entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
  }

  private void rightCollide(Entity entity) {
    entity.setXForce(0);
    entity.setCenterX(entity.getCenterX() - 1);
    // entity.setCenterX(getBoundsInParent().getMinX() - entity.getEntityWidth()/2);
    entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
  }

  private void bottomCollide(Entity entity) {
    entity.setVelocityY(0);
  }

  public void wonGame(Entity entity) {
    entity.setWon(true);
  }

  public void entityDeath(Entity entity) {
    entity.setHitpoints(0);
  }

  //https://stackoverflow.com/questions/24393636/the-pain-with-the-pane-in-javafx-how-can-you-scale-nodes-with-fixed-top-left-co
  public void scalePlayer(Entity entity){
    if(entity.getId().equals("player")){
      if(!hasShrunk) {
        entity.getNode().setScaleX(0.5);
        entity.getNode().setScaleY(0.5);
        //entity.setMaxY(entity.getScene().getHeight() / 4);
        hasShrunk = true;
      }
    }
  }

}
