package ooga.engine.entities;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.games.Collideable;

public abstract class Entity extends Rectangle implements Collideable, Movable {
  private final int SCENE_WIDTH;
  private final int SCENE_HEIGHT;
  public static final int HEALTH_PENALTY = -20;
  private int currentHitpoints = 5;
  private Node nodeObject;
  private double speed = 0;
  private double previousX;
  private double previousY;
  private double jumpCapacity = 0;
  private double xForce = 0;
  private double yForce = 0;
  boolean status_Alive = true;
  private double timeElapsedY = 0;
  private double timeElapsedX = 0;
  private double timeInterval = 0;
  private boolean facing = true;
  private boolean jump = false;

  public Entity(int objectWidth,int objectHeight,  double initialX, double initialY) {
    this.SCENE_WIDTH = objectWidth;
    this.SCENE_HEIGHT = objectHeight;
    nodeObject = new Rectangle(initialX, initialY, objectWidth, objectHeight);
    this.previousX = initialX + objectWidth / 2;
    this.previousY = initialY + objectHeight;
    setX(initialX);
    setY(initialY);
    setWidth(objectWidth);
    setHeight(objectHeight);
    this.setCenterX(initialX + objectWidth / 2);
    this.setMaxY(initialY + objectHeight);
  }

  public Node getNode() {
    return nodeObject;
  }

  public boolean getStatusAlive(){
      return this.status_Alive;
  }

  //public abstract int getID();

  public double getVelocityX(){
    return speed;
  }

  public double getVelocityY(){
    return jumpCapacity;
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
      setX(inputX - SCENE_WIDTH/2);
  }

  public void setMaxY(double inputY){
      nodeObject.setLayoutY(inputY - nodeObject.getLayoutBounds().getMaxY());
      setY(inputY - SCENE_HEIGHT);
    //nodeObject.setLayoutY(inputY+nodeObject.getLayoutY());
  }

  public void setHitpoints(int hitpoints){
      currentHitpoints=hitpoints;
      if (currentHitpoints <= 0){
          status_Alive = false;
      }
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

    public double getEntityWidth(){
      return SCENE_WIDTH;
  }

    public double getEntityHeight(){
        return SCENE_HEIGHT;
    }

    public double getMaxY(){
      return nodeObject.getBoundsInParent().getMaxY();
  }

    public void setXForce(double force){
      xForce = force;
  }

    public void setYForce(double force){
      yForce = force;
  }

    public double getXForce(){
      return xForce;
  }

    public double getYForce(){
      return yForce;
  }

    public double getTimeElapsedX() {
        return timeElapsedX;
    }

    public double getTimeElapsedY(){
      return timeElapsedY;
    }

    public void setTimeElapsedY(double time){
      timeElapsedY = time;
    }

    public void setTimeElapsedX(double time){
        timeElapsedX = time;
    }

    public boolean hasGravity(){
      return true;
    }

    public boolean getFacing(){
      return facing;
    }

    public boolean isJump(){
        return jump;
    }

    public void setJump(boolean isJump){
        jump = isJump;
    }

    public void setFacing(boolean direction){
      facing = direction;
    }



//TODO: Use reflection, default no collision

    public void leftCollideable(Entity entity) {
      //TODO: action reflection of below methods
    }

    public void rightCollideable(Entity entity) {
        //TODO: action reflection of below methods
    }

    public void bottomCollideable(Entity entity) {
        //TODO: action reflection of below methods
    }

    public void topCollideable(Entity entity) {
        //TODO: action reflection of below methods
    }


    protected void entityDeath(Entity entity, String object) {
        if (entity.getId().equals(object)) {
            entity.setHitpoints(0);
        }
    }

    protected void thisDeath(Entity entity, String object) {
        if (entity.getId().equals(object)) {
            this.setHitpoints(0);
        }
    }

    protected void wallObstacle(Entity entity, String object){
        if(entity.getId().equals(object)){
            entity.setHitpoints(0);
        }
    }

    protected void applyY(Entity entity, String object) {
        if (entity.getId().equals(object)){
            entity.setYForce(-1000); //use up method once moved to player
        }
    }

    protected void playerHealthPenalty(Entity entity, String causeHeathPenalty) {
        if (entity.getId().equals(causeHeathPenalty)) {
            entity.setHitpoints(entity.getHitpoints() + HEALTH_PENALTY);
        }
    }

    protected void enemyHeathPenalty(Entity entity, String causeHeathPenalty) {
        if (entity.getId().equals(causeHeathPenalty) && entity.getYForce() > 300) {
            setHitpoints(0);
            entity.setHitpoints(entity.getHitpoints() - HEALTH_PENALTY);
        }
    }

    //add id.
}
