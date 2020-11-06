package ooga.engine.obstacles;


import javafx.scene.Node;
import ooga.engine.entities.Moveables;

import java.util.Map;

public interface Collideable {
  Node getNodeObject();
  Map<String, String> getCollisionRules();
  void leftCollideable(Moveables entity);
  void rightCollideable(Moveables entity);
  void topCollideable(Moveables entity);
  void bottomCollideable(Moveables entity);
}
