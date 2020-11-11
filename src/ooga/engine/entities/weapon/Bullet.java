package ooga.engine.entities.weapon;

public class Bullet extends Weapon {
    public Bullet(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("bullet");
    }

    @Override
    public boolean hasGravity(){
        return true;
    }

}
