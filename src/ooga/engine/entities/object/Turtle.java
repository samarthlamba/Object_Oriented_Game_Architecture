package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

/**
 * gives y velocity when you jump on them
 */
public class Turtle extends Entity {

  public Turtle(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setId("turtle");
  }

}