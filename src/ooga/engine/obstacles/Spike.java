package ooga.engine.obstacles;

public class Spike extends Obstacle {
    private final static String ID = "spike";

    public Spike(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
        super(obstacleWidth, obstacleHeight, initialX, initialY);
        setId(ID);
    }



}
