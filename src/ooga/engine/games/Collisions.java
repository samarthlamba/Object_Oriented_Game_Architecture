package ooga.engine.games;

import ooga.engine.entities.Entity;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Movable;
import ooga.engine.obstacles.Obstacle;
import ooga.engine.obstacles.Obstacle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Collisions {
    private Set<String> collisionTypes = Set.of("right", "left", "top", "bottom");

    public void collisions(Entity entity, Collideable object) {
        List<String> collisionSide = new ArrayList<>();
        for (String side : collisionTypes) {
            try {
                Class gameSuperClass = this.getClass();
                Method findCollisionSide = gameSuperClass.getDeclaredMethod(side + "Collision", Entity.class, Collideable.class);
                if ((boolean) findCollisionSide.invoke(this, entity, object)) {
                    collisionSide.add(side);
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace(); //TODO: handle error better
            }
        }

        for (String side : collisionSide) {
            try {
                Class collision = Class.forName("ooga.engine.games.Collideable");
                Method actionOnCollision = collision.getDeclaredMethod(side + "Collideable", Entity.class);
                actionOnCollision.invoke(object, entity);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace(); //TODO: handle error better
            }
        }
    }


    private boolean rightCollision(Entity entity, Collideable object) {
        return object.getNode().getBoundsInParent().getMinX() < entity.getNode().getBoundsInParent().getMaxX() &&
                object.getNode().getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent().getMinX() &&
                entity.getXForce() >= 0 && !checkCornersY(entity, object);
    }

    private boolean leftCollision(Entity entity, Collideable object) {
        return object.getNode().getBoundsInParent().getMaxX() > entity.getNode().getBoundsInParent().getMinX() &&
                object.getNode().getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent().getMaxX() &&
                entity.getXForce() <= 0 && !checkCornersY(entity, object);
    }

    private boolean bottomCollision(Entity entity, Collideable object) {
        return object.getNode().getBoundsInParent().getMaxY() > entity.getNode().getBoundsInParent().getMinY() &&
                object.getNode().getBoundsInParent().getMaxY() < entity.getNode().getBoundsInParent().getMaxY() &&
                !checkCornersX(entity, object);
    }

    private boolean topCollision(Entity entity, Collideable object) {
        return object.getNode().getBoundsInParent().getMinY() < entity.getNode().getBoundsInParent().getMaxY() &&
                object.getNode().getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent().getMinY() &&
                !checkCornersX(entity, object);
    }

    private boolean checkCornersY(Entity entity, Collideable object) {
        return areEqualDouble(object.getNode().getBoundsInParent().getMinY(), entity.getNode().getBoundsInParent().getMaxY(), 0) ||
                areEqualDouble(object.getNode().getBoundsInParent().getMaxY(), entity.getNode().getBoundsInParent().getMinY(), 0);
    }

    private boolean checkCornersX(Movable entity, Collideable object) {
        return areEqualDouble(object.getNode().getBoundsInParent().getMaxX(), entity.getNode().getBoundsInParent().getMinX(), 0) ||
                areEqualDouble(object.getNode().getBoundsInParent().getMinX(), entity.getNode().getBoundsInParent().getMaxX(), 0);
    }

    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    public boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }


}
