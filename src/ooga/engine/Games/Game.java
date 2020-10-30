package ooga.engine.Games;

import java.util.Collection;
import ooga.engine.GamePlay;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public abstract class Game implements GamePlay {
  private Collection<Entity> entities;
  private Collection<Obstacle> obstacles;
  private final double timeElapsed;
  public Game(Collection<Obstacle> obstacleCollection,Collection<Entity> entityCollection,double timeElapsed ) {
   this.entities = entityCollection;
   this.obstacles = obstacleCollection;
   this.timeElapsed = timeElapsed;
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
