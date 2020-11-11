package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

/**
 * Gives mario coin when hit
 */
public class Coin extends Entity {

  public static final int COIN_HEALTH = 1;
  public static final int COIN_SIZE = 50;

  public Coin(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    this.setHitpoints(COIN_HEALTH);
    setWidth(COIN_SIZE);
    setHeight(COIN_SIZE);
    setId("coin");
  }

  @Override
  public void leftCollideable(Entity entity) {
    if (entity.getId() == "player"){
      setHitpoints(0);
    }
  }

  @Override
  public void rightCollideable(Entity entity) {
    if (entity.getId() == "player"){
      setHitpoints(0);
    }
  }

  @Override
  public void bottomCollideable(Entity entity) {
    if (entity.getId() == "player"){
      setHitpoints(0);
    }
  }

  @Override
  public void topCollideable(Entity entity) {
    if (entity.getId() == "player"){
      setHitpoints(0);
    }
  }

}
