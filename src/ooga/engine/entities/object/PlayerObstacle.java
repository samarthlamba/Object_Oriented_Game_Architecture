package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

/**
 * Used in lost vikings as inactive vikings
 */
public class PlayerObstacle extends Entity {

  private static final String ID = "playerobstacle";

  public PlayerObstacle(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setId(ID);
  }

  /**
   * Does not have gravity force acting on entity
   * @return boolean false no gravity force experienced
   */
  @Override
  public boolean hasGravity() {
    return false;
  }
}
