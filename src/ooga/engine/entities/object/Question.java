package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

public class Question extends Entity {

    public Question(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        this.setMaxY(initialY);
        setId("question");
    }

    @Override
    public boolean hasGravity(){
        return false;
    }

    @Override
    public void leftCollideable(Entity entity) {
        thisDeath(entity, "player");
    }

    @Override
    public void rightCollideable(Entity entity) {
        thisDeath(entity, "player");
    }

    @Override
    public void bottomCollideable(Entity entity) {
        thisDeath(entity, "player");
    }

    @Override
    public void topCollideable(Entity entity) {
        thisDeath(entity, "player");
    }

}
