package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

public class WaterSource extends Entity {
  private static final String ID = "watersource";
  public WaterSource(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setId(ID);
    setSource(true);
  }

  @Override
  public boolean hasGravity(){
        return false;
    }
}
