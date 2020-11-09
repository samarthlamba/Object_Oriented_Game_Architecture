package ooga.engine.obstacles;


import javafx.scene.Node;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Moveable;

public interface Collideable {
  void leftCollideable(Entity entity);
  void rightCollideable(Entity entity);
  void topCollideable(Entity entity);
  void bottomCollideable(Entity entity);
}
