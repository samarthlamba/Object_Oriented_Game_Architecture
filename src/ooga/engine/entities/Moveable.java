package ooga.engine.entities;

import javafx.scene.Node;

 public interface Moveable {
   Node getNode();
   String getId();
   double getMaxY();
   double getCenterX();
}
