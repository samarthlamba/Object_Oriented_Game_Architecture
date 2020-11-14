package ooga.engine.obstacles;

import javafx.scene.Node;
import ooga.engine.entities.Entity;

public class Spike extends Obstacle {

    private static final double NEGATIVE_DIRECTION = -1;
    public Spike(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
        super(obstacleWidth, obstacleHeight, initialX, initialY);
        setId("spike");
    }


    public void topCollideable(Entity entity) {
        entityDeath(entity);
    }


}
