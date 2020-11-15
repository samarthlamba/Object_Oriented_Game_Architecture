package ooga.engine.obstacles;

import ooga.engine.entities.Entity;

public class Floor extends Obstacle{

    private static final double MOVE_FORCE = 1000;
    private static final double NEGATIVE_DIRECTION = -1;
    public static final double GRAVITY = 59900;
    private final static String ID = "floor";
    public Floor(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
        super(obstacleWidth, obstacleHeight, initialX, initialY);
        setId(ID);
    }
}
