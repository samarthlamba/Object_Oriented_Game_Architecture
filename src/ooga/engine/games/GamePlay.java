package ooga.engine.games;

import java.util.Collection;
import javafx.scene.Node;
import ooga.engine.entities.MovableBounds;
import ooga.view.UpdateObjectsOnScreen;

/**
 * Interface to pass correct objects from game engine to frontend display
 */
public interface GamePlay {

  void updateLevel();

  Collection<? extends Node> getBackground();

  Collection<? extends MovableBounds> getEntities();

  void moveRight();

  void moveLeft();

  void moveUp();

  void setDisplay(UpdateObjectsOnScreen updateObjectsOnScreen);

  int getPoints();

  boolean isWon();

  boolean isLost();
}
