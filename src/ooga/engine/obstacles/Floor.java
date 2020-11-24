package ooga.engine.obstacles;

/**
 * used in all games
 * Collides on all sides nothing passes through  (defined by property file)
 * Used in mario (same as wall collisions)
 */
public class Floor extends Obstacle {

  private final static String ID = "floor";

  public Floor(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId(ID);
  }
}
