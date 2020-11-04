package ooga.engine.games;

import java.util.Collection;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class MetroidGame extends Game{

  public MetroidGame(Collection<Obstacle> obstacles,
      Collection<Entity> entities, double timeElapsed) {
    super(obstacles, entities, timeElapsed);
  }
}
