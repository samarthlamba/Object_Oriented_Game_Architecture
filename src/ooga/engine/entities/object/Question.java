package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

public class Question extends Entity {

    public Question(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        this.setMaxY(initialY);
        setId("question");
    }

    @Override
    public void leftCollideable(Entity entity) {
        if (entity.getId() == "player"){
            setHitpoints(0);
        }
    }

    @Override
    public void rightCollideable(Entity entity) {
        if (entity.getId() == "player"){
            setHitpoints(0);
        }
    }

    @Override
    public void bottomCollideable(Entity entity) {
        if (entity.getId() == "player"){
            setHitpoints(0);
        }
    }

    @Override
    public void topCollideable(Entity entity) {
        if (entity.getId() == "player"){
            setHitpoints(0);
        }
    }
}
