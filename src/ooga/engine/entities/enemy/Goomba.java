package ooga.engine.entities.enemy;

/**
 * Mario Basic Enemy
 * has auto horizontal movement attribute
 */
public class Goomba extends Enemy {
  public static final int VELOCITY = 200;

  public Goomba(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setHorizontalMovement(true, VELOCITY);
  }



}


