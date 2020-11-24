package ooga.engine.entities.weapon;

import ooga.engine.entities.Entity;

/**
 * Weapon abstract class sets weapons to have no gravity
 * so fly straight through air appropriately
 */
public abstract class Weapon extends Entity{
  public Weapon(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }

  /**
   * Does not have gravity force acting on entity
   * @return boolean false no gravity force experienced
   */
  @Override
  public boolean hasGravity(){
        return false;
    }
}
