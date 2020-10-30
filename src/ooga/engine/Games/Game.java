package ooga.engine.Games;

import java.util.Collection;
import ooga.engine.GamePlay;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public abstract class Game implements GamePlay {
  private Collection<Entity> entities;
  private Collection<Obstacle> obstacles;
  public Game(Collection<Entity> entityCollection, Collection<Obstacle> obstacleCollection) {
   this.entities = entityCollection;
   this.obstacles = obstacleCollection;
  }

  @Override
  public void updateLevel() {
  }

  @Override
  public Collection<Obstacle> getBackground() {
    return obstacles;
  }

  @Override
  public Collection<Entity> getEntities() {
    return entities;
  }


}
