package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

/**
 * Vikings obstacle
 * is set as source of water fall
 * used to find how waterfall is connected and where it starts
 */
public class WaterSource extends Entity {

  private static final String ID = "watersource";

  public WaterSource(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setId(ID);
    setSource(true);
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
