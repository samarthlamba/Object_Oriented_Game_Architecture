package ooga.engine.obstacles;


import javafx.scene.Node;
import ooga.engine.entities.Entity;

public class Wall extends Obstacle {
  private double initialX;
  private double initialY;
  private int obstacleWidth;
  private int obstacleHeight;
  private final static String ID = "wall";
  private static final double MOVE_FORCE = 1000;
  private static final double NEGATIVE_DIRECTION = -1;
  public static final double GRAVITY = 59900;

  public Wall(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    this.initialX = initialX;
    this.initialY= initialY;
    this.obstacleHeight = obstacleHeight;
    this.obstacleWidth = obstacleWidth;
    setId(ID);
  }
  @Override
  public void leftCollideable(Entity entity) {
    removeWeapon(entity);
    entity.setXForce(0);
    entity.setCenterX(getBoundsInParent().getMaxX() + entity.getEntityWidth()/2);
    entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
  }

  @Override
  public void rightCollideable(Entity entity) {
    removeWeapon(entity);
    entity.setXForce(0);
    entity.setCenterX(getBoundsInParent().getMinX() - entity.getEntityWidth()/2);
    entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
  }
  @Override
  public void bottomCollideable(Entity entity) {
    // System.out.println("bottom");
    //entity.setMaxY(getBoundsInParent().getMaxY() + entity.getEntityHeight());
    removeWeapon(entity);
    entity.setYForce(GRAVITY);
    entity.setVelocityY(0);
    //entity.setJump(false);
  }
  @Override
  public void topCollideable(Entity entity) {
    removeWeapon(entity);
    entity.setMaxY(getBoundsInParent().getMinY());
    entity.setYForce(entity.getYForce() + NEGATIVE_DIRECTION * GRAVITY);
    entity.setVelocityY(0);
    entity.setJump(false);
  }


}