package ooga.engine.games.beans;

import java.io.Serializable;

/**
 * Beans to pass constants into game through game constructor
 */
public class GameBean implements Serializable {

  private double gravity;
  private double moveForce;
  private int jumpMax;
  private int outOfBoundsOffset;


  /**
   * Returns gravity constant
   * @return double constant force down acting on all entities that have gravity
   */
  public double getGravity() {
    return gravity;
  }

  /**
   * Returns move force constant
   * @return double force applied to player when key pressed
   */
  public double getMoveForce() {
    return moveForce;
  }

  /**
   * Returns maximum jump velocity constant
   * @return int max velocity that player can jump when key pressed
   */
  public int getJumpMax(){ return jumpMax; }

  /**
   * Returns maximum constant out of bounds of game play arena
   * Used for falling death and prevents objects building up that have
   * already exited playable arena
   * @return int constant maximum distance away from nearest obstacle
   */
  public int getOutOfBoundsOffset(){return outOfBoundsOffset; }

  /**
   * Sets gravity constant
   * @param gravity read in from properties file
   */
  public void setGravity(String gravity) {
    this.gravity = Double.valueOf(gravity);
  }

  /**
   * Sets move force constant
   * @param moveForce read in from properties file
   */
  public void setMoveForce(String moveForce) {
    this.moveForce = Double.valueOf(moveForce);
  }

  /**
   * Sets jump max constant
   * @param jumpMax read in from properties file
   */
  public void setJumpMax(String jumpMax){ this.jumpMax = Integer.valueOf(jumpMax); }

  /**
   * Sets out of bounds offset constant
   * @param outOfBoundsOffset read in from properties file
   */
  public void setOutOfBoundsOffset(String outOfBoundsOffset){this.outOfBoundsOffset = Integer.valueOf(outOfBoundsOffset);}


}
