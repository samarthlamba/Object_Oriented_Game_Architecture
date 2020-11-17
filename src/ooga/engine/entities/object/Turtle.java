package ooga.engine.entities.object;

import ooga.engine.entities.Entity;
import ooga.engine.entities.enemy.Enemy;

/**
 * gives y velocity when you jump on them
 */
public class Turtle extends Entity {

  public Turtle(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setId("turtle");
  }

  @Override
  public void topCollideable(Entity entity) {
    if (entity.getId() == "player"){
      entity.setYForce(-1000); //use up method once moved to player
  }
}

}
