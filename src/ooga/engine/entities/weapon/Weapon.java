package ooga.engine.entities.weapon;

import ooga.engine.entities.Entity;

public abstract class Weapon extends Entity{
  public Weapon(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }

  @Override
  public boolean hasGravity(){
        return false;
    }
}
