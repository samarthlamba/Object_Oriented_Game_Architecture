package ooga.engine.entities;

import javafx.scene.Node;

 public interface MovableBounds {
   Node getNode();

   String getId();

   double getMaxY();

   double getCenterX();

   boolean getFacing();

   int getHealth();

   boolean getSpecialAction();

 }
