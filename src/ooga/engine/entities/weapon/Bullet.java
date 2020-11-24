package ooga.engine.entities.weapon;

import ooga.engine.entities.Entity;

/**
 * Bullet subclass extends weapon
 * Weapon used by Metoid samus
 */
public class Bullet extends Weapon {
  private final static String ID = "bullet";
  public Bullet(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setId(ID);
  }
}
