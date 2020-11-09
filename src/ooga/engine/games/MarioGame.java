package ooga.engine.games;

import java.util.Collection;

import ooga.engine.entities.Moveables;
import ooga.engine.games.beans.MarioBean;
import ooga.engine.obstacles.Collideable;

public class MarioGame extends Game {
  private Collection<Collideable> obstacles;
  private Collection<Moveables> entities;

  public MarioGame(Collection<Collideable> obstacleCollection, Collection<Moveables> entityCollection,
                   double timeElapsed, MarioBean bean) {
    super(obstacleCollection, entityCollection, timeElapsed,bean);
    entities = entityCollection;
    obstacles = obstacleCollection;

  }

    @Override
    protected void playerEnemyCollision(Moveables entity) {
      if (entity.getId().equals("player")) {
        for (Moveables e : entities) {
          if (entityCollision(entity, e)) {
            System.out.println(entityCollision(entity, e));
            if (entityTopCollision(entity, e)) {
              e.setHitpoints(e.getHitpoints() - 1);
            } else {
              entity.setHitpoints(entity.getHitpoints() - 1);
            }
          }
        }
      }
    }

  private boolean entityTopCollision(Moveables player, Moveables entity) {
    return entity.getNode().getBoundsInParent().getMinY() < player.getNode().getBoundsInParent().getMaxY() &&
            entity.getNode().getBoundsInParent().getMinY() > player.getNode().getBoundsInParent().getMinY() &&
            !checkCornersMoveablesX(player, entity);
  }

}

