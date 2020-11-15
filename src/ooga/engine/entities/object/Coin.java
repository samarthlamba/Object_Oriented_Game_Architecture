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

    private void invokeMethod(Entity entity, String objectName, String collisionName){
        GamePropertyFileReader reader = new GamePropertyFileReader(objectName);
        Iterator<String> methods = reader.getMethods(collisionName).iterator();
        Iterator<String> parameter = reader.getParameters(collisionName).iterator();
        String s = "player";
        while (methods.hasNext()&& parameter.hasNext()) {
            try {
                System.out.println(parameter.next());
                Method x = this.getClass().getSuperclass().getDeclaredMethod(methods.next(), Entity.class, String.class);
                x.setAccessible(true);
                x.invoke(this, entity, s);
                System.out.println(parameter.next());
            } catch (Exception e) {
                throw new MethodNotFound("Could not find reflected method from property file");
            }
        }
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


