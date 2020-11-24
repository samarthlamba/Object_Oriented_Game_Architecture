package ooga.engine.obstacles;

/**
 * Goal obstacle causes player to win if collided with  (defined by property file)
 */
public class Goal extends Obstacle {

  private static final String ID = "goal";

  public Goal(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId(ID);
  }


}
