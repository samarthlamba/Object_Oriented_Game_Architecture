package ooga.engine.entities;

import javafx.scene.Node;

public interface Moveables {

  void setCenterX(double inputX);

  void setMaxY(double inputY);

  void setHitpoints(int hitpoints);

  int getHitpoints();

  Node getNode();

  //true = right

}
