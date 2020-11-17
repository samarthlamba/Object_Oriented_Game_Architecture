package ooga.engine.entities.object;

import ooga.engine.entities.Entity;
import ooga.engine.entities.MethodNotFound;
import ooga.engine.games.GamePropertyFileReader;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

/**
 * Gives mario coin when hit
 */
public class Coin extends Entity {

    public static final int COIN_HEALTH = 1;
    private static final String OBJECT_NAME = "Coin";
    public Coin(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        this.setHitpoints(COIN_HEALTH);
        setId("coin");
    }



}


