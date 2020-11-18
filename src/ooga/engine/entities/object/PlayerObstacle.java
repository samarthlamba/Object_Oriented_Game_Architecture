package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

public class PlayerObstacle extends Entity {
  private static final String ID = "playerobstacle";
  public PlayerObstacle(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setId(ID);
  }

  @Override
  public boolean hasGravity(){
        return false;
    }
}
