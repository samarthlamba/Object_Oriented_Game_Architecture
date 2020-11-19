package ooga.engine.obstacles;

import javafx.scene.Node;
import ooga.engine.games.Collideable;

public interface Unmovable {

  Node getNode();

  Collideable getCollideable();

  void setNormalForce(double gravity);
}
