package ooga.engine.games;

import java.util.Collection;

import ooga.engine.entities.Entity;
import ooga.engine.entities.Moveable;
import ooga.engine.obstacles.Collideable;
import ooga.engine.obstacles.Obstacle;

public class VikingsGame extends Game{

  public VikingsGame(Collection<Obstacle> obstacles,
                     Collection<Entity> entities, double timeElapsed) {
    super(obstacles, entities, timeElapsed);
  }

  public boolean hasFinished(){
    return false;
  }


}
