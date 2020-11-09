package ooga.engine.games;

import java.util.Collection;

import javafx.scene.Node;
import ooga.engine.entities.Bounds;

public interface GamePlay {
    void updateLevel();
    Collection<? extends Node> getBackground();
    Collection<? extends Bounds> getEntities();
    void moveRight();
    void moveLeft();
    void moveUp();

}
