package ooga.engine.entities.weapon;

import ooga.engine.entities.Entity;

public class Arrow extends Weapon {
    public Arrow(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("arrow");
    }

    @Override
    public void leftCollideable(Entity entity) {
        entityDeath(entity, "player");
    }


    @Override
    public void rightCollideable(Entity entity) {
        entityDeath(entity, "player");
    }

    @Override
    public void bottomCollideable(Entity entity) {
        entityDeath(entity, "player");
    }

    @Override
    public void topCollideable(Entity entity) {
        entityDeath(entity, "player");
    }

}
