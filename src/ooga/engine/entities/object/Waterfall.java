package ooga.engine.entities.object;

import javafx.scene.Node;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.List;

/**
 * Vikings obstacle that is collideable from top only and whose "flow" is blocked by non-active viking
 */
public class Waterfall extends Entity {

  public Waterfall(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId("waterfall");
  }

  @Override
  public boolean hasGravity(){
    return false;
  }

/*  public void leftCollideable(Entity entity) {
    entityDeath(entity, "arrow");
    leftObstacle(entity, "player");
    leftObstacle(entity, "waterfall");
    thisDeath(entity, "playerobstacle");
  }


  public void rightCollideable(Entity entity) {
    entityDeath(entity, "arrow");
    rightObstacle(entity, "player");
    rightObstacle(entity, "waterfall");
    thisDeath(entity, "playerobstacle");
  }

  public void bottomCollideable(Entity entity) {
    entityDeath(entity, "arrow");
    bottomObstacle(entity, "player");
    bottomObstacle(entity, "waterfall");
    thisDeath(entity, "playerobstacle");
  }

  public void topCollideable(Entity entity) {
    entityDeath(entity, "arrow");
    topObstacle(entity, "player");
    topObstacle(entity, "waterfall");
    thisDeath(entity, "playerobstacle");
  }*/

}
