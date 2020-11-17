package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

/**
 * Gives mario coin when hit
 */
public class Coin extends Entity {

    private static final String ID = "coin";
    public Coin(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId(ID);
    }



}


