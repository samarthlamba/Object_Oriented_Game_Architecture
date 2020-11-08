package ooga.engine.entities;

public class Mario extends Player {

    private static final int FULL_HEALTH = 100;

    public Mario(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setHitpoints(FULL_HEALTH);
    }

    @Override
    public void topMoveables(Moveables entity) {
        if (entity.getId() == "enemy") {
            entity.setHitpoints(entity.getHitpoints() - 1);
        }
    }
}

