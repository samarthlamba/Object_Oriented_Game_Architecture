package ooga.engine.obstacles;

public class Wall extends Obstacle {
  private final static String ID = "wall";

  public Wall(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId(ID);
  }
}