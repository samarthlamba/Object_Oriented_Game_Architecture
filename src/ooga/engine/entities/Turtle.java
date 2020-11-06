package ooga.engine.entities;

/**
 * Enemy in mario that gives y velocity when you jump on them
 */
public class Turtle extends Enemy{

  public Turtle(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }
}
