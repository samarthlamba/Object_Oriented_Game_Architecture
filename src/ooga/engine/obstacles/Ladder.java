package ooga.engine.obstacles;

public class Ladder extends Obstacle {
    private final static String ID = "ladder";

    public Ladder(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
        super(obstacleWidth, obstacleHeight, initialX, initialY);
        setId(ID);
    }


}