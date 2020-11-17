package ooga.engine.entities.enemy;

import ooga.engine.entities.Entity;
import ooga.engine.entities.enemy.Enemy;

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
    dead(entity, entity.getId().equals("player"));
  }


  public void rightCollideable(Entity entity) {
    dead(entity, entity.getId().equals("player"));
  }

  public void bottomCollideable(Entity entity) {
    dead(entity, entity.getId().equals("player"));
  }

  public void topCollideable(Entity entity) {
    dead(entity, entity.getId().equals("player"));
  }


}
