package ooga.engine.entities.enemy;

import ooga.engine.entities.Entity;

public class Goomba extends Enemy {
    public static final int VELOCITY = 200;
    public static final int GOOMBA_HEALTH = 1;
    public static final int HEALTH_PENALTY = -20;
    private static final String OBJECT_NAME = "Goomba";
    public Goomba(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setHorizontalMovement(true, VELOCITY);
        this.setHitpoints(GOOMBA_HEALTH);
    }



}


