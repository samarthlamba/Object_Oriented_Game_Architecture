package ooga.engine;

import java.util.Collection;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public interface GamePlay {
    void updateLevel();

    Collection<Obstacle> getBackground();

    Collection<Entity> getEntities();
}