package ooga.engine.entities.enemy;

import ooga.engine.entities.Entity;

/**
 * Metroid basic enemy
 */
public class SpacePirate extends Enemy {
  private static final int FULL_HEALTH = 100;
  public SpacePirate(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setHitpoints(FULL_HEALTH);
  }



  public void leftCollideable(Entity entity) {
    healthPenaltyOnObject(entity, "player");
    thisDeath(entity, "player");
  }


  public void rightCollideable(Entity entity) {
    healthPenaltyOnObject(entity, "player");
    thisDeath(entity, "player");
  }

  public void bottomCollideable(Entity entity) {
    healthPenaltyOnObject(entity, "player");
    thisDeath(entity, "player");
  }

  public void topCollideable(Entity entity) {
    healthPenaltyOnObject(entity, "player");
    thisDeath(entity, "player");
  }


}
