package ooga.engine.obstacles;


import javafx.scene.Node;

import java.util.Map;

public interface Collideable {
  Node getNodeObject();
  Map<String, String> collisionRules();
}
