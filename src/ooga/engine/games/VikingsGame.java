package ooga.engine.games;

import java.util.Collection;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Moveables;
import ooga.engine.obstacles.Collideable;
import ooga.engine.obstacles.Obstacle;

public class VikingsGame extends Game{

  public VikingsGame(Collection<Collideable> obstacles,
                     Collection<Moveables> entities, double timeElapsed) {
    super(obstacles, entities, timeElapsed);
  }


}
