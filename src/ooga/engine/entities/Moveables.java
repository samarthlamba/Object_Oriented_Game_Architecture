package ooga.engine.entities;

import javafx.scene.Node;

public interface Moveables {

  void setX(double inputX);

  void setY(double inputY);

  void setHitpoints(int hitpoints);

  int getHitpoints();

  Node getNode();

  //true = right

  //double getMass();
}
