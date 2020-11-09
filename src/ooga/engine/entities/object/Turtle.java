package ooga.engine.entities.object;

import ooga.engine.entities.Entity;
import ooga.engine.entities.enemy.Enemy;

/**
 * gives y velocity when you jump on them
 */
public class Turtle extends Enemy {

  public Turtle(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setId("turtle");
  }

  @Override
  public void topCollideable(Entity entity) {
    System.out.println(entity.getId());
    if (entity.getId() == "player"){
      entity.setYForce(-2000); //use up method once moved to player
  }
}

}
