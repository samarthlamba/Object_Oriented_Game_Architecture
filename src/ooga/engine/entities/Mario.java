package ooga.engine.entities;

import javafx.scene.Node;

public class Mario extends Player {

    private static final int FULL_HEALTH = 100;

    public Mario(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setHitpoints(FULL_HEALTH);
    }

    @Override
    public Node getNodeObject() {
        return null;
    }

    @Override
    public void topCollideable(Moveables entity) {
        if (entity.getId() == "enemy") {
            entity.setHitpoints(entity.getHitpoints() - 1);
        }
    }
}

