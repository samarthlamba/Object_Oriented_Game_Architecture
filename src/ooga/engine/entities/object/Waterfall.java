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


}
