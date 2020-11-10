package ooga.engine.obstacles;

import javafx.scene.Node;
import ooga.engine.entities.Entity;

public class Spike extends Obstacle {

    private static final double NEGATIVE_DIRECTION = -1;
    public Spike(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
        super(obstacleWidth, obstacleHeight, initialX, initialY);
        setId("spike");
    }

    public void leftCollideable(Entity entity) {
        entity.setXForce(0);
        entity.setCenterX(getBoundsInParent().getMaxX() + entity.getEntityWidth()/2);
        entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
        entity.setHitpoints(0);
    }


    public void rightCollideable(Entity entity) {
        entity.setXForce(0);
        entity.setCenterX(getBoundsInParent().getMinX() - entity.getEntityWidth()/2);
        entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
        entity.setHitpoints(0);
    }

    public void bottomCollideable(Entity entity) {
        entity.setYForce(GRAVITY);
        entity.setVelocityY(0);
        entity.setHitpoints(0);
        //entity.setJump(false);
    }

    public void topCollideable(Entity entity) {
        entity.setMaxY(getBoundsInParent().getMinY());
        entity.setYForce(entity.getYForce() + NEGATIVE_DIRECTION * GRAVITY);
        entity.setTimeElapsedY(entity.getTimeElapsedX());
        entity.setVelocityY(0);
        entity.setJump(false);
        entity.setHitpoints(0);
    }

}
