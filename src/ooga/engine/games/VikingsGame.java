package ooga.engine.games;

import java.util.Collection;
import ooga.engine.entities.Moveables;
import ooga.engine.games.beans.VikingsBean;
import ooga.engine.obstacles.Collideable;

public class VikingsGame extends Game{

  public VikingsGame(Collection<Collideable> obstacles,
                     Collection<Moveables> entities, double timeElapsed, VikingsBean bean) {
    super(obstacles, entities, timeElapsed,bean);
  }

  @Override
  protected void playerEnemyCollision(Moveables entity) {

  }
}
