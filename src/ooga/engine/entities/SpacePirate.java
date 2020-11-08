package ooga.engine.entities;

/**
 * Metroid basic enemy
 */
public class SpacePirate extends Enemy{
  private static final int FULL_HEALTH = 100;
  public SpacePirate(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setHitpoints(FULL_HEALTH);
  }


}
