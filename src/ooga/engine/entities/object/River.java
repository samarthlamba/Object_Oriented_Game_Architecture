package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

public class River extends Entity {
    public River(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("river");
    }
}
