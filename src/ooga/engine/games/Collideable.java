package ooga.engine.games;


import javafx.scene.Node;
import ooga.engine.entities.Entity;

/**
 * Interface implemented by Entity and Obstacle
 * Allows for both Entity Entity collision and Entity Obstacle collisions
 * Use by collision manager since it only needs and Entity and a Collideable input
 */
public interface Collideable {

  Node getNode();

  void leftCollideable(Entity entity);

  void rightCollideable(Entity entity);

  void topCollideable(Entity entity);

  void bottomCollideable(Entity entity);
}
