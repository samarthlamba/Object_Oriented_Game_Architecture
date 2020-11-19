package ooga.engine.obstacles;

public class Goal extends Obstacle {

  private static final String ID = "goal";

  public Goal(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
    setId(ID);
  }


}
