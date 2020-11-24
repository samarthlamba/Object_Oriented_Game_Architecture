package ooga.engine.obstacles;

/**
 * Powerup in mario that shrinks mario to smaller size when hit
 *  (defined by property file)
 */
public class MarioShrinker extends Obstacle {

  private final static String ID = "shrinker";

  public MarioShrinker(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId(ID);
  }


}
