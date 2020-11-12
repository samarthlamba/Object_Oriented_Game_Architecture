package ooga.engine.entities.weapon;

import ooga.engine.entities.Entity;

public class Bullet extends Weapon {
    public Bullet(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("bullet");
    }

    @Override
    public void leftCollideable(Entity entity) {
        dead(entity, entity.getId().equals("enemy"));
    }

    @Override
    public void rightCollideable(Entity entity) {
        dead(entity, entity.getId().equals("enemy"));
    }

    @Override
    public void bottomCollideable(Entity entity) {
        dead(entity, entity.getId().equals("enemy"));
    }

    @Override
    public void topCollideable(Entity entity) {
        dead(entity, entity.getId().equals("enemy"));
    }

}
