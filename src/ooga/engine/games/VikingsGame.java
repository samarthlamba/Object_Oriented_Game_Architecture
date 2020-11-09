package ooga.engine.games;

import java.util.Collection;

import ooga.engine.entities.weapon.Arrow;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class VikingsGame extends Game{

  private static final int ARROW_WIDTH = 10;
  private static final int ARROW_HEIGHT = 3;
  private static final double ARROW_VELOCITY = -30;
  private static final double UPWARDS_VELOCITY = -20;
  public VikingsGame(Collection<Obstacle> obstacles,
                     Collection<Entity> entities, double timeElapsed) {
    super(obstacles, entities, timeElapsed);
  }

  public boolean hasFinished(){
    return false;
  }

  @Override
  public void shoot(){
    Entity entity = super.findMainPlayer();
    double arrowStartX = entity.getCenterX() - entity.getEntityWidth()/2;
    double arrowStartY = entity.getMaxY() - entity.getEntityHeight()/2;
    double arrowVelocity = ARROW_VELOCITY;
    if(entity.getFacing()) {
      arrowStartX = entity.getCenterX() + entity.getEntityWidth()/2;
      arrowVelocity *= NEGATIVE_DIRECTION;
    }
    Arrow arrow = new Arrow(ARROW_WIDTH, ARROW_HEIGHT, arrowStartX, arrowStartY);
    arrow.setVelocityX(arrowVelocity);
    arrow.setVelocityY(UPWARDS_VELOCITY);
    entities.add(arrow);
  }


}
