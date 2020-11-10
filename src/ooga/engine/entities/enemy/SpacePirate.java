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
    if (entity.getId() == "player") {
      entity.setXForce(0);
      entity.setCenterX(getBoundsInParent().getMaxX() + entity.getEntityWidth() / 2);
      entity.setHitpoints(0);
    }
  }


  public void rightCollideable(Entity entity) {
    if (entity.getId() == "player") {
      entity.setXForce(0);
      entity.setCenterX(getBoundsInParent().getMinX() - entity.getEntityWidth() / 2);
      entity.setHitpoints(0);
    }
  }

  public void bottomCollideable(Entity entity) {
    if (entity.getId() == "player") {
      entity.setVelocityY(0);
      entity.setHitpoints(0);
      //entity.setJump(false);
    }
  }

  public void topCollideable(Entity entity) {
    if (entity.getId() == "player") {
      entity.setMaxY(getBoundsInParent().getMinY());
      entity.setTimeElapsedY(entity.getTimeElapsedX());
      entity.setVelocityY(0);
      entity.setJump(false);
      entity.setHitpoints(0);
    }
  }
}
