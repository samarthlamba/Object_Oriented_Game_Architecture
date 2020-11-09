package ooga.engine.games;

import javafx.scene.Node;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;
import ooga.engine.obstacles.Obstacle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Collisions {
    private Collection<Obstacle> obstacles;
    private Collection<Entity> entities;
    private Set<String> collisionTypes = Set.of("right", "left", "top", "bottom");

    public Collisions(Collection<Obstacle> obstacles, Collection<Entity> entities) {
        this.obstacles = obstacles;
        this.entities = entities;

    }

    public void collisions(Entity entity, Node object) {
        List<String> collisionSide = new ArrayList<>();
        for (String side : collisionTypes) {
            try {
                Class gameSuperClass = this.getClass();
                Method findCollisionSide = gameSuperClass.getDeclaredMethod(side + "Collision", Entity.class, Node.class);
                if ((boolean) findCollisionSide.invoke(this, entity, object)) {
                    collisionSide.add(side);
                }

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace(); //TODO: handle error better
            }
        }

        for (String side : collisionSide) {
            try {
                String classPathName = getClassPath(object);
                Class collision = Class.forName(classPathName);
                Method actionOnCollision = collision.getDeclaredMethod(side + "Collideable", Entity.class);
                actionOnCollision.invoke(object, entity);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace(); //TODO: handle error better
            }
        }
    }

    private String getClassPath(Node object) {
        String[] className = object.getClass().getName().split("\\.");
        if (className[2].equals("obstacles")) {
            className[3] = "Collideable";
        } else {
            className[3] = "Entity";
            className = Arrays.copyOf(className, className.length - 1);
        }
        return String.join(".", className);
    }

    protected boolean entityCollision(Entity player, Entity e) {
        if (e.getId().equals("enemy")) {
            return player.getNode().getBoundsInParent().intersects(e.getNode().getBoundsInParent());
        }
        return false;
    }

    private boolean rightCollision(Entity entity, Node object) {
        return object.getBoundsInParent().getMinX() < entity.getNode().getBoundsInParent().getMaxX() &&
                object.getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent().getMinX() &&
                entity.getXForce() >= 0 && !checkCornersY(entity, object);
    }

    private boolean leftCollision(Entity entity, Node object) {
        return object.getBoundsInParent().getMaxX() > entity.getNode().getBoundsInParent().getMinX() &&
                object.getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent().getMaxX() &&
                entity.getXForce() <= 0 && !checkCornersY(entity, object);
    }

    private boolean bottomCollision(Entity entity, Node object) {
        return object.getBoundsInParent().getMaxY() > entity.getNode().getBoundsInParent().getMinY() &&
                object.getBoundsInParent().getMaxY() < entity.getNode().getBoundsInParent().getMaxY() &&
                !checkCornersX(entity, object);
    }

    private boolean topCollision(Entity entity, Node object) {
        return object.getBoundsInParent().getMinY() < entity.getNode().getBoundsInParent().getMaxY() &&
                object.getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent().getMinY() &&
                !checkCornersX(entity, object);
    }

    private boolean checkCornersY(Entity entity, Node object) {
        return areEqualDouble(object.getBoundsInParent().getMinY(), entity.getNode().getBoundsInParent().getMaxY(), 0) ||
                areEqualDouble(object.getBoundsInParent().getMaxY(), entity.getNode().getBoundsInParent().getMinY(), 0);
    }

    private boolean checkCornersX(Entity entity, Node object) {
        return areEqualDouble(object.getBoundsInParent().getMaxX(), entity.getNode().getBoundsInParent().getMinX(), 0) ||
                areEqualDouble(object.getBoundsInParent().getMinX(), entity.getNode().getBoundsInParent().getMaxX(), 0);
    }

    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    public boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }


}
