package ooga.engine.entities.weapon;

import ooga.engine.entities.Entity;

public class Arrow extends Weapon {
    private final static String ID = "arrow";
    public Arrow(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId(ID);
    }


}
