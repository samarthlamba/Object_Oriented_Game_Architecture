package ooga.engine.entities.player;

import ooga.engine.entities.Entity;
import ooga.engine.entities.player.Player;

/**
 * Lost Vikings Implementation of player
 */
public class Viking extends Player {

  public Viking(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
  }

 /* @Override
  public void leftCollideable(Entity entity) {
    entityDeath(entity, "enemy");
  }


  @Override
  public void rightCollideable(Entity entity) {
    entityDeath(entity, "enemy");
  }*/

}
