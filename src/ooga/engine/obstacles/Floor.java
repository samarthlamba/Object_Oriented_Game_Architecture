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
    @Override
    public void leftCollideable(Entity entity) {
        removeWeapon(entity);
        entity.setXForce(0);
        entity.setCenterX(getBoundsInParent().getMaxX() + entity.getEntityWidth()/2);
        entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
    }

    @Override
    public void rightCollideable(Entity entity) {
        removeWeapon(entity);
        entity.setXForce(0);
        entity.setCenterX(getBoundsInParent().getMinX() - entity.getEntityWidth()/2);
        entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
    }
    @Override
    public void bottomCollideable(Entity entity) {
        // System.out.println("bottom");
        //entity.setMaxY(getBoundsInParent().getMaxY() + entity.getEntityHeight());
        removeWeapon(entity);
        entity.setYForce(GRAVITY);
        entity.setVelocityY(0);
        //entity.setJump(false);
    }
    @Override
    public void topCollideable(Entity entity) {
        removeWeapon(entity);
        entity.setMaxY(getBoundsInParent().getMinY());
        entity.setYForce(entity.getYForce() + NEGATIVE_DIRECTION * GRAVITY);
        entity.setVelocityY(0);
        entity.setJump(false);
    }
}
