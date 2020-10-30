package ooga.engine.games;

import java.util.Collection;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class MarioGame extends Game{

  public MarioGame(Collection<Entity> entityCollection,
      Collection<Obstacle> obstacleCollection, double timeElapsed) {
    super(obstacleCollection, entityCollection,timeElapsed);
  }
}
