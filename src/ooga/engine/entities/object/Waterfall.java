package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

/**
 * Vikings obstacle that is collideable from top only and whose "flow" is blocked by non-active viking
 */
public class Waterfall extends Entity {
  private static final String ID = "waterfall";
  public Waterfall(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId(ID);
    setPercolate(true);
  }

  @Override
  public boolean hasGravity(){
    return false;
  }


}
