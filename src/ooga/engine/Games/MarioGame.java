package ooga.engine.Games;

import java.util.Collection;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class MarioGame extends Game{

  public MarioGame(Collection<Obstacle> obstacleCollection,Collection<Entity> entityCollection,
      double timeElapsed) {
    super(obstacleCollection,entityCollection,timeElapsed);
  }

  @Override
  public void updateLevel() {
    System.out.println("we're doin it! we're updating the level!");
  }
}
