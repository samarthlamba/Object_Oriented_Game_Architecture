package ooga.engine.entities.enemy;

import ooga.engine.entities.Entity;
import ooga.engine.entities.enemy.Enemy;

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
        if (entity.getId() == "player"){
            entity.setHitpoints(entity.getHitpoints() + HEALTH_PENALTY);
        }
    }

    @Override
    public void rightCollideable(Entity entity) {
        if (entity.getId() == "player"){
            entity.setHitpoints(entity.getHitpoints() + HEALTH_PENALTY);
        }
    }

    @Override
    public void bottomCollideable(Entity entity) {
        if (entity.getId() == "player"){
            entity.setHitpoints(entity.getHitpoints() + HEALTH_PENALTY);
        }
    }

    @Override
    public void topCollideable(Entity entity) {
        if (entity.getId() == "player" && entity.getYForce() > 300) {
            setHitpoints(0);
            entity.setHitpoints(entity.getHitpoints() - HEALTH_PENALTY);
        }
    }

}

