package ooga.engine.obstacles;

/**
 * Obstacle in metroid
 * kills main player when touches top spikes  (defined by property file)
 */
public class Spike extends Obstacle {

  private final static String ID = "spike";

  public Spike(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId(ID);
  }


}
