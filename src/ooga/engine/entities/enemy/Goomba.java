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
        playerHealthPenalty(entity, "player");
    }

    @Override
    public void rightCollideable(Entity entity) {
        playerHealthPenalty(entity, "player");
    }

    @Override
    public void bottomCollideable(Entity entity) {
        playerHealthPenalty(entity, "player");
    }


    @Override
    public void topCollideable(Entity entity) {
        enemyHeathPenalty(entity, "player");
    }



}


