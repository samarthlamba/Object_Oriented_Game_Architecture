package ooga.engine.entities.object;

import ooga.engine.entities.Entity;
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
    System.out.println("tedsad");
    for (String k: methods) {
      try {
        Method x = this.getClass().getSuperclass().getDeclaredMethod(k);
      }
      catch (Exception e){
        return;
      }
    }
  }


  @Override
  public void rightCollideable(Entity entity) {
    thisDeath(entity, "player");
  }

  @Override
  public void bottomCollideable(Entity entity) {
    thisDeath(entity, "player");
  }

  @Override
  public void topCollideable(Entity entity) {
    thisDeath(entity, "player");
  }

  }


