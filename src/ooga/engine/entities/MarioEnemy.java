package ooga.engine.entities;

public class MarioEnemy extends Enemy{
    public static final int VELOCITY = 200;
    public static final int GOOMBA_HEALTH = 1;
    public static final int HEALTH_PENALTY = -20;
    public MarioEnemy(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        this.setVelocityX(VELOCITY);
        this.setHitpoints(GOOMBA_HEALTH);
    }

    @Override
    public void leftMoveables(Moveables entity) {
        if (entity.getId() == "player"){
            entity.setHitpoints(entity.getHitpoints()+HEALTH_PENALTY);
        }
    }

    @Override
    public void rightMoveables(Moveables entity) {
        if (entity.getId() == "player"){
            entity.setHitpoints(entity.getHitpoints()+HEALTH_PENALTY);
        }
    }

    @Override
    public void bottomMoveables(Moveables entity) {
        if (entity.getId() == "player"){
            entity.setHitpoints(entity.getHitpoints()+HEALTH_PENALTY);
        }
    }

    }


