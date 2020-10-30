package ooga.engine.entities;

public interface Moveables {

  double getX();

  double getY();

  void setX(double inputX);

  void setY(double inputY);

  void setHitpoints(int hitpoints);

  int getHitpoints();

  boolean getFacing();
  //true = right

  double getMass();
}
