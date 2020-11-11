package ooga.engine.entities.weapon;

import ooga.engine.entities.Entity;

public abstract class Weapon extends Entity{
    public Weapon(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
    }

    @Override
    public boolean hasGravity(){
        return false;
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

    @Override
    public void topCollideable(Entity entity) {
        if (entity.getId() == "player"){
            entity.setHitpoints(0);
        }
    }
}
