package ooga.engine.entities;

import javafx.scene.Node;

 public interface Movable {
   Node getNode();
   String getId();
   double getMaxY();
   double getCenterX();
}
