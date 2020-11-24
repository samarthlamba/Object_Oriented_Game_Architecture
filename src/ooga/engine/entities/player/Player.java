package ooga.engine.entities.player;

import ooga.engine.entities.Entity;

/**
 * abstract Player extends Entity and has health points set at 3
 * Sets baseline attributes of all main players for all game implementations
 */
public abstract class Player extends Entity {

  private final static String ID = "player";
  private static final int FULL_HEALTH = 3;
  private boolean finished = false;

  public Player(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
    setId(ID);
    setHitpoints(FULL_HEALTH);
  }

  /**
   * Check if player has won
   * @return finished boolean
   */
  @Override
  public boolean hasWon() {
    return finished;
  }

  /**
   * sets player to boolean if won
   * @param finished boolean if player won
   */
  @Override
  public void setWon(boolean finished) {
    this.finished = finished;
  }


}
