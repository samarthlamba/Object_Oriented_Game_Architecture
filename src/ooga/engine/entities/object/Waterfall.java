package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

/**
 * Viking obstacle
 * has percolation state true
 */
public class Waterfall extends Entity {

  private static final String ID = "waterfall";

  public Waterfall(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId(ID);
    setPercolate(true);
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
