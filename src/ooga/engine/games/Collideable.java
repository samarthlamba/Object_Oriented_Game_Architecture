package ooga.engine.games;


import javafx.scene.Node;
import ooga.engine.entities.Entity;

public interface Collideable {

  Node getNode();

  void leftCollideable(Entity entity);

  void rightCollideable(Entity entity);

  void topCollideable(Entity entity);

  void bottomCollideable(Entity entity);
}
