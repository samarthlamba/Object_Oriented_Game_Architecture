package ooga.engine.obstacles;


import javafx.scene.Node;

public interface Collideable {
  boolean hasCollided(Node node);//replace with sides
  double getMass();
}
