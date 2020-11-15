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
        Method x = this.getClass().getSuperclass().getDeclaredMethod(k, Entity.class, String.class);
        x.setAccessible(true);
        System.out.println(x.toString());
        x.invoke(this, entity, "player");
      }
      catch (Exception e){        System.out.println(e);

        System.out.println("afasdasdasdzasdfbgds");
        return;
      }
    }
    if(this.getHitpoints() == 0){

      System.out.println("tedsad123");
    }
  }


  @Override
  public void rightCollideable(Entity entity) {
    GamePropertyFileReader reader = new GamePropertyFileReader("Coin");
    Collection<String> methods = reader.getMethods("right");
    System.out.println("tedsad");
    for (String k: methods) {
      try {
        Method x = this.getClass().getSuperclass().getDeclaredMethod(k, Entity.class, String.class);
        System.out.println(x.toString());
        x.setAccessible(true);
        x.invoke(this, entity, "player");
        System.out.println("helloooo");
      }
      catch (Exception e){     e.printStackTrace();
        return;
      }
    }
    if(this.getHitpoints() == 0){

      System.out.println("tedsad123");
    }
  }

  @Override
  public void bottomCollideable(Entity entity) {
    GamePropertyFileReader reader = new GamePropertyFileReader("Coin");
    Collection<String> methods = reader.getMethods("bottom");
    System.out.println("tedsad");
    for (String k: methods) {
      try {
        Method x = this.getClass().getSuperclass().getDeclaredMethod(k, Entity.class, String.class);
        System.out.println(x.toString());
        x.setAccessible(true);
        x.invoke(this, entity, "player");
      }
      catch (Exception e){
        return;
      }
    }
    if(this.getHitpoints() == 0){

      System.out.println("tedsad123");
    }
  }

  @Override
  public void topCollideable(Entity entity) {
    GamePropertyFileReader reader = new GamePropertyFileReader("Coin");
    Collection<String> methods = reader.getMethods("top");
    Collection<String> parameters = reader.getParameters("top");
    System.out.println("tedsad");
    for (String k: methods) {
      try {
        Method x = this.getClass().getSuperclass().getDeclaredMethod(k, Entity.class, String.class);
        System.out.println(x.toString());
        x.setAccessible(true);
        x.invoke(this, entity, "player");
      }
      catch (Exception e){
        return;
      }
    }
    if(this.getHitpoints() == 0){

      System.out.println("tedsad123");
    }
  }

  }


