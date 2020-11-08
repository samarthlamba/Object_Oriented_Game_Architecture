package ooga.engine.games;

import java.util.Collection;

import ooga.engine.entities.Entity;
import ooga.engine.entities.Moveable;
import ooga.engine.obstacles.Collideable;

public interface GamePlay {
    void updateLevel();
    Collection<? extends Collideable> getBackground();
    Collection<? extends Moveable> getEntities();
    void moveRight();
    void moveLeft();
    void moveUp();

}
