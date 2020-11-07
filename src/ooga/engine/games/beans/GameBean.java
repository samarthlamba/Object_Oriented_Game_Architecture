package ooga.engine.games.beans;

import java.io.Serializable;

public class GameBean implements Serializable {

  private double gravity;
  private double moveForce;

  public double getGravity() {
    return gravity;
  }

  public double getMoveForce() {
    return moveForce;
  }

  public void setGravity(String gravity) {
    this.gravity = Double.valueOf(gravity);
  }

  public void setMoveForce(String moveForce) {
    this.moveForce = Double.valueOf(moveForce);
  }

}
