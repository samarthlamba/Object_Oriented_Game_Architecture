package ooga.engine.entities.player;

import ooga.engine.entities.Entity;

public abstract class Player extends Entity {

  private final static String ID = "player";
  private static final int FULL_HEALTH = 3;
  private boolean finished = false;

  public Player(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
    setId(ID);
    setHitpoints(FULL_HEALTH);
  }

  @Override
  public boolean hasWon(){
    return finished;
  }

  @Override
  public void setWon(boolean finished){
    this.finished = finished;
  }


}
