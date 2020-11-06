package ooga.engine.games;

import java.util.Collection;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Moveables;
import ooga.engine.obstacles.Collideable;
import ooga.engine.obstacles.Obstacle;

public interface GamePlay {
    void updateLevel();
    Collection<Collideable> getBackground();
    Collection<Moveables> getEntities();
    void moveRight();
    void moveLeft();
    void moveUp();

    void updateMoveables();
}
