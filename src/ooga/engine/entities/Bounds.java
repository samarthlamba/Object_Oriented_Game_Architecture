package ooga.engine.entities;

import javafx.scene.Node;

 public interface Bounds {
   Node getNode();
   String getId();
   double getMaxY();
   double getCenterX();
   boolean getFacing();
}
