package ooga.engine.entities.object;

import ooga.engine.entities.Entity;
import ooga.engine.entities.MethodNotFound;
import ooga.engine.games.GamePropertyFileReader;

import java.lang.reflect.Method;
import java.util.Collection;

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
        GamePropertyFileReader reader = new GamePropertyFileReader("Coin");
        Collection<String> methods = reader.getMethods("left");
        for (String k : methods) {
            try {
                Method x = this.getClass().getSuperclass().getDeclaredMethod(k, Entity.class, String.class);
                x.setAccessible(true);
                x.invoke(this, entity, "player");
            } catch (Exception e) {
                throw new MethodNotFound("Could not find reflected method from property file");
            }
        }
    }


    @Override
    public void rightCollideable(Entity entity) {
        GamePropertyFileReader reader = new GamePropertyFileReader("Coin");
        Collection<String> methods = reader.getMethods("right");
        for (String k : methods) {
            try {
                Method x = this.getClass().getSuperclass().getDeclaredMethod(k, Entity.class, String.class);
                x.setAccessible(true);
                x.invoke(this, entity, "player");
            } catch (Exception e) {
                throw new MethodNotFound("Could not find reflected method from property file");
            }
        }
    }

    @Override
    public void bottomCollideable(Entity entity) {
        GamePropertyFileReader reader = new GamePropertyFileReader("Coin");
        Collection<String> methods = reader.getMethods("bottom");
        for (String k : methods) {
            try {
                Method x = this.getClass().getSuperclass().getDeclaredMethod(k, Entity.class, String.class);
                x.setAccessible(true);
                x.invoke(this, entity, "player");
            } catch (Exception e) {
                throw new MethodNotFound("Could not find reflected method from property file");
            }
        }
    }

    @Override
    public void topCollideable(Entity entity) {
        GamePropertyFileReader reader = new GamePropertyFileReader("Coin");
        Collection<String> methods = reader.getMethods("top");
        Collection<String> parameters = reader.getParameters("top");
        for (String k : methods) {
            try {
                Method x = this.getClass().getSuperclass().getDeclaredMethod(k, Entity.class, String.class);
                x.setAccessible(true);
                x.invoke(this, entity, "player");
            } catch (Exception e) {
                throw new MethodNotFound("Could not find reflected method from property file");
            }
        }
    }

}


