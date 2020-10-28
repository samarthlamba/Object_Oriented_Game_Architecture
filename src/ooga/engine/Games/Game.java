package ooga.engine.Games;

import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

import java.util.Collection;

public abstract class Game implements GamePlay {
    public Collection<Obstacle> getBackground(){
        return null;
    }

    public Collection<Entity> getEntities(){
        return null;
    }

}
