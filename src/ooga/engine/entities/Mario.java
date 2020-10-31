package ooga.engine.entities;

public class Mario extends Player{

  public Mario(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
  }

  @Override
  public double getX() {
    return 0;
  }

  @Override
  public double getY() {
    return 0;
  }

  @Override
  public void setX(double inputX) {

  }

  @Override
  public void setY(double inputY) {

  }

  @Override
  public void setHitpoints(int hitpoints) {

  }

  @Override
  public int getHitpoints() {
    return 0;
  }

  @Override
  public double getMass() {
    return 0;
  }

  public boolean getFacing() {
    return false;
  }
}
