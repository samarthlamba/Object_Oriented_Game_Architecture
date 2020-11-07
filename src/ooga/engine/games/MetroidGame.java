package ooga.engine.games;

import java.util.Collection;
import ooga.engine.entities.Moveables;
import ooga.engine.games.beans.MetroidBean;
import ooga.engine.obstacles.Collideable;

public class MetroidGame extends Game{

  public MetroidGame(Collection<Collideable> obstacles,
                     Collection<Moveables> entities, double timeElapsed, MetroidBean bean) {
    super(obstacles, entities, timeElapsed,bean);
  }

  @Override
  protected void playerEnemyCollision(Moveables entity) {

  }
}
