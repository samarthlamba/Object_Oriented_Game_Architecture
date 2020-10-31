package ooga.engine.entities;

public class Mario extends Player{

  public Mario(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }


  @Override
  public void setHitpoints(int hitpoints) {

  }

  @Override
  public int getHitpoints() {
    return 0;
  }
  /*@Override
  public double getMass() {
    return 0;
  }*/

  public boolean getFacing() {
    return false;
  }
}
