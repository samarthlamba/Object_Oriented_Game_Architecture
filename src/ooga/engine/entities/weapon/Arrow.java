package ooga.engine.entities.weapon;

public class Arrow extends Weapon {
    public Arrow(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("arrow");
    }

}
