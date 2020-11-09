package ooga.engine.entities;

import javafx.scene.Node;

public class Arrow extends Entity{
    public Arrow(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("arrow");
    }

    @Override
    public Node getNodeObject() {
        return null;
    }
    @Override
    public void leftCollideable(Entity entity) {
        if (entity.getId() == "player"){
            entity.setHitpoints(0);
        }
    }

    @Override
    public void rightCollideable(Entity entity) {
        if (entity.getId() == "player"){
            entity.setHitpoints(0);
        }
    }

    @Override
    public void bottomCollideable(Entity entity) {
        if (entity.getId() == "player"){
            entity.setHitpoints(0);
        }
    }
}
