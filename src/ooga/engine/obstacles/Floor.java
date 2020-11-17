package ooga.engine.obstacles;

import ooga.engine.entities.Entity;

public class Floor extends Obstacle{
    
    private final static String ID = "floor";
    public Floor(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
        super(obstacleWidth, obstacleHeight, initialX, initialY);
        setId(ID);
    }
}
