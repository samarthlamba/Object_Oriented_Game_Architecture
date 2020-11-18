package ooga.engine.obstacles;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.entities.Entity;
import ooga.engine.games.Collideable;
import ooga.engine.games.GamePropertyFileReader;
import java.lang.reflect.Method;
import java.util.Iterator;

public abstract class Obstacle extends Rectangle implements Collideable, Unmovable {
  private static final double NEGATIVE_DIRECTION = -1;
  private static final double SHRINK_RATIO = .7;
  private double normalForce = 0;
  private double moveX = 0;
  private double moveY = 0;
  private double initialX;
  private double initialY;
  private boolean reached;
  private boolean left = false;
  private boolean right = false;

  public Obstacle(int obstacleWidth,int obstacleHeight, double initialX, double initialY) {
    this.initialX = initialX;
    this.initialY= initialY;
    reached = false;
    setX(initialX);
    setY(initialY);
    setWidth(obstacleWidth);
    setHeight(obstacleHeight);
  }

  public Node getNode(){
    return (Node) this;
  }

  public Collideable getCollideable(){
    return (Collideable) this;
  }

  public void moveXBy(double x){
    relocate(getBoundsInParent().getMinX()+x,getBoundsInParent().getMinY());
  }


  public void moveYBy(double y){
    relocate(getBoundsInParent().getMinX(), getBoundsInParent().getMinY()+y);
  }

  public void moveContinouslyXBy(double X){
    moveX = X;
  }

  public void moveContinouslyYBy(double Y){
    moveY = Y;
  }

  public void setNormalForce(double gravity){
    this.normalForce = gravity;
  }

  public void leftCollideable(Entity entity) {
    invokeMethod(entity, "left");
  }


  public void rightCollideable(Entity entity) {
    invokeMethod(entity, "right");
  }


  public void bottomCollideable(Entity entity) {
    invokeMethod(entity, "bottom");
  }

  public void topCollideable(Entity entity) {
    invokeMethod(entity, "top");
  }


  protected void invokeMethod(Entity entity, String collisionName){

      GamePropertyFileReader reader = new GamePropertyFileReader(this.getClass().getSimpleName());
      Iterator methods = reader.getMethods(collisionName).iterator();
      while (methods != null && methods.hasNext()) {
        Class current = this.getClass().getSuperclass();
        try {
        Method x = current.getDeclaredMethod((String) methods.next(), Entity.class);
        x.setAccessible(true);
        x.invoke(this, entity);
      }
        catch (Exception e) {
          return;
        }
    }
  }

  private void topCollide(Entity entity) {
    entity.setMaxY(getBoundsInParent().getMinY());
    entity.setYForce(entity.getYForce() + NEGATIVE_DIRECTION * normalForce);
    entity.setVelocityY(0);
    entity.setJump(false);

  }

  protected void removeWeapon(Entity entity){
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
      if(!entity.hasShrunk()) {
        entity.setHeight(entity.getWidth() * SHRINK_RATIO);
        entity.setWidth(entity.getHeight() * SHRINK_RATIO);
        entity.setShrunk(true);
      }
    }
  }

}
