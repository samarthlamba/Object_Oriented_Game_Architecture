package ooga.engine.entities;

import javafx.scene.Node;

/**
 * Interface MovableBounds passed to view so animation and view know correct states to display
 * and has access to all methods it needs.
 * Implemented by Entity
 */
 public interface MovableBounds {
   Node getNode();

   String getId();

   double getMaxY();

   double getCenterX();

   boolean getFacing();

   int getHealth();

   boolean getSpecialAction();

 }
