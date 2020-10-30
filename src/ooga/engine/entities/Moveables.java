package ooga.engine.entities;

import javafx.scene.Node;

public interface Moveables {

  double getX();

  double getY();

  void setX(double inputX);

  void setY(double inputY);

  void setHitpoints(int hitpoints);

  int getHitpoints();

  Node getNode();

  //true = right


}
