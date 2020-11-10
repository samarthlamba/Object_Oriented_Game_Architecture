package ooga.engine.entities.weapon;

import ooga.engine.entities.Entity;

public class Bullet extends Weapon {
    public Bullet(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("bullet");
    }

    @Override
    public void leftCollideable(Entity entity) {
        if (entity.getId().equals("enemy")){
            entity.setHitpoints(0);
        }
    }

    @Override
    public void rightCollideable(Entity entity) {
        if (entity.getId().equals("enemy")){
            entity.setHitpoints(0);
        }
    }

    @Override
    public void bottomCollideable(Entity entity) {
        if (entity.getId().equals("enemy")){
            entity.setHitpoints(0);
        }
    }

    @Override
    public void topCollideable(Entity entity) {
        if (entity.getId().equals("enemy")){
            entity.setHitpoints(0);
        }
    }
}
