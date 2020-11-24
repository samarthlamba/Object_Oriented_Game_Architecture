package ooga.engine.games;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Movable;

/**
 * Collision manager class that determines if a collision has taken place, which side a collision
 * occurs on, and what action to take as a result.
 */
public class Collisions {

  private static final int PRECISION = 0;
  private final Set<String> collisionTypes = Set.of("right", "left", "top", "bottom");

  /**
   * collisions takes in a Entity and Collideable (which is implemented by both Entity and Obstacle)
   * this means that this single method can handle both entity, entity collisions and entity, obstacle collisions.
   * First reflection finds all of the sides a collision takes place
   * Second reflection loops through these sides and calls method that will preform correct action
   * on entity as a result of collision
   * @param entity Entity involved in the collision
   * @param object Collideable which is implemented by Enitity and Obstacle. It is involved in the collision
   */
  public void collisions(Entity entity, Collideable object) {
    List<String> collisionSide = new ArrayList<>();
    for (String side : collisionTypes) {
      try {
        Class gameSuperClass = this.getClass();
        Method findCollisionSide = gameSuperClass
            .getDeclaredMethod(side + "Collision", Entity.class, Collideable.class);
        if ((boolean) findCollisionSide.invoke(this, entity, object)) {
          collisionSide.add(side);
        }
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        throw new MethodNotFound("Error in collision reflection " + e);
      }
    }

    for (String side : collisionSide) {
      try {
        Class collision = Class.forName("ooga.engine.games.Collideable");
        Method actionOnCollision = collision.getDeclaredMethod(side + "Collideable", Entity.class);
        actionOnCollision.invoke(object, entity);
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
        throw new MethodNotFound("Error in collision action reflection " + e);
      }
    }
  }


  private boolean rightCollision(Entity entity, Collideable object) {
    return object.getNode().getBoundsInParent().getMinX() < entity.getNode().getBoundsInParent()
        .getMaxX() &&
        object.getNode().getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent()
            .getMinX() &&
        entity.getXForce() >= 0 && !checkCornersY(entity, object);
  }

  private boolean leftCollision(Entity entity, Collideable object) {
    return object.getNode().getBoundsInParent().getMaxX() > entity.getNode().getBoundsInParent()
        .getMinX() &&
        object.getNode().getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent()
            .getMaxX() &&
        entity.getXForce() <= 0 && !checkCornersY(entity, object);
  }

  private boolean bottomCollision(Entity entity, Collideable object) {
    return object.getNode().getBoundsInParent().getMaxY() > entity.getNode().getBoundsInParent()
        .getMinY() &&
        object.getNode().getBoundsInParent().getMaxY() < entity.getNode().getBoundsInParent()
            .getMaxY() &&
        !checkCornersX(entity, object);
  }

  private boolean topCollision(Entity entity, Collideable object) {
    return object.getNode().getBoundsInParent().getMinY() < entity.getNode().getBoundsInParent()
        .getMaxY() &&
        object.getNode().getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent()
            .getMinY() &&
        !checkCornersX(entity, object);
  }

  private boolean checkCornersY(Entity entity, Collideable object) {
    return areEqualDouble(object.getNode().getBoundsInParent().getMinY(),
        entity.getNode().getBoundsInParent().getMaxY(), PRECISION) ||
        areEqualDouble(object.getNode().getBoundsInParent().getMaxY(),
            entity.getNode().getBoundsInParent().getMinY(), PRECISION);
  }

  private boolean checkCornersX(Movable entity, Collideable object) {
    return areEqualDouble(object.getNode().getBoundsInParent().getMaxX(),
        entity.getNode().getBoundsInParent().getMinX(), PRECISION) ||
        areEqualDouble(object.getNode().getBoundsInParent().getMinX(),
            entity.getNode().getBoundsInParent().getMaxX(), PRECISION);
  }

  /**
   * Checks if two doubles are equal according to how precise one wants to be
   * @param a first double to be compared
   * @param b second double to be compared
   * @param precision precision to what decimal point do they need to be equal
   * @return returns true if equal upto precision, otherwise false
   */
  //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
  public boolean areEqualDouble(double a, double b, int precision) {
    return Math.abs(a - b) <= Math.pow(10, -precision);
  }


}
