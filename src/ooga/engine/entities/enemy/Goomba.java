package ooga.engine.entities.enemy;

import ooga.engine.entities.Entity;

public class Goomba extends Enemy {
    public static final int VELOCITY = 200;
    public static final int GOOMBA_HEALTH = 1;
    public static final int HEALTH_PENALTY = -20;
    private static final String OBJECT_NAME = "Goomba";
    public Goomba(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        this.setVelocityX(VELOCITY);
        this.setHitpoints(GOOMBA_HEALTH);
    }


    @Override
    public void leftCollideable(Entity entity) {
        invokeMethod(entity, OBJECT_NAME, "left");
    }




    @Override
    public void rightCollideable(Entity entity) {

        invokeMethod(entity, OBJECT_NAME, "right");
    }

    @Override
    public void bottomCollideable(Entity entity) {

        invokeMethod(entity, OBJECT_NAME, "bottom");
    }

    @Override
    public void topCollideable(Entity entity) {

        invokeMethod(entity, OBJECT_NAME, "top");
    }


}


