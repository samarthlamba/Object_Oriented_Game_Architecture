package ooga.engine.entities;

import javafx.scene.Node;

public class Bullet extends Entity{
    public Bullet(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("bullet");
    }

    @Override
    public boolean hasGravity(){
        return false;
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
