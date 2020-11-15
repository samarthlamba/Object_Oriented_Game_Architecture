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
    public static final int COIN_SIZE = 50;

    public Coin(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        this.setHitpoints(COIN_HEALTH);
        setWidth(COIN_SIZE);
        setHeight(COIN_SIZE);
        setId("coin");
    }

    @Override
    public void leftCollideable(Entity entity) {
       invokeMethod(entity, "Coin", "left");
    }




    @Override
    public void rightCollideable(Entity entity) {

        invokeMethod(entity, "Coin", "right");
    }

    @Override
    public void bottomCollideable(Entity entity) {

        invokeMethod(entity, "Coin", "right");
    }

    @Override
    public void topCollideable(Entity entity) {

        invokeMethod(entity, "Coin", "right");
    }

}


