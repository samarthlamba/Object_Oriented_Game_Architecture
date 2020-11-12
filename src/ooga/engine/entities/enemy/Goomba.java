package ooga.engine.entities.enemy;

import ooga.engine.entities.Entity;

public class Goomba extends Enemy {
    public static final int VELOCITY = 200;
    public static final int GOOMBA_HEALTH = 1;
    public static final int HEALTH_PENALTY = -20;
    public Goomba(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        this.setVelocityX(VELOCITY);
        this.setHitpoints(GOOMBA_HEALTH);
    }


    @Override
    public void leftCollideable(Entity entity) {
        healthPenaltyOnObject(entity, "player");
        thisDeath(entity, "player");
    }

    @Override
    public void rightCollideable(Entity entity) {
        healthPenaltyOnObject(entity, "player");
        thisDeath(entity, "player");
    }

    @Override
    public void bottomCollideable(Entity entity) {
        healthPenaltyOnObject(entity, "player");
        thisDeath(entity, "player");
    }


    @Override
    public void topCollideable(Entity entity) {
        thisDeath(entity, "player");
    }



}


