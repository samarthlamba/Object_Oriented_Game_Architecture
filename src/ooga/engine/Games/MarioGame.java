package ooga.engine.Games;

import java.util.Collection;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class MarioGame extends Game{

  public MarioGame(Collection<Entity> entityCollection,
      Collection<Obstacle> obstacleCollection) {
    super(entityCollection, obstacleCollection);
  }
}
