package ooga.engine.games;

import javafx.scene.Node;
import ooga.engine.entities.Moveables;
import ooga.engine.obstacles.Collideable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Collisions {
    private Collection<Collideable> obstacles;
    private Collection<Moveables> entities;
    private Set<String> collisionTypes = Set.of("right", "left", "top", "bottom");

    public Collisions(Collection<Collideable> obstacles, Collection<Moveables> entities) {
        this.obstacles = obstacles;
        this.entities = entities;

    }

    public void collisions(Moveables entity, Node object) {
        List<String> collisionSide = new ArrayList<>();
        for (String side : collisionTypes) {
            try {
                Class gameSuperClass = this.getClass();
                Method findCollisionSide = gameSuperClass.getDeclaredMethod(side + "Collision", Moveables.class, Node.class);
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
                System.out.println(classPathName);
                Class collision = Class.forName(classPathName);
                Method actionOnCollision = collision.getDeclaredMethod(side + classPathName.split("\\.")[3], Moveables.class);
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
            className[3] = "Moveables";
        }
        return String.join(".", className);
    }

    private boolean rightCollision(Moveables entity, Node object) {
        return object.getBoundsInParent().getMinX() < entity.getNode().getBoundsInParent().getMaxX() &&
                object.getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent().getMinX() &&
                entity.getXForce() > 0 && !checkCornersY(entity, object);
    }

    private boolean leftCollision(Moveables entity, Node object) {
        return object.getBoundsInParent().getMaxX() > entity.getNode().getBoundsInParent().getMinX() &&
                object.getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent().getMaxX() &&
                entity.getXForce() < 0 && !checkCornersY(entity, object);
    }

    private boolean bottomCollision(Moveables entity, Node object) {
        return object.getBoundsInParent().getMaxY() > entity.getNode().getBoundsInParent().getMinY() &&
                object.getBoundsInParent().getMaxY() < entity.getNode().getBoundsInParent().getMaxY() &&
                !checkCornersX(entity, object);
    }

    private boolean topCollision(Moveables entity, Node object) {
        return object.getBoundsInParent().getMinY() < entity.getNode().getBoundsInParent().getMaxY() &&
                object.getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent().getMinY() &&
                !checkCornersX(entity, object);
    }

    private boolean checkCornersY(Moveables entity, Node object) {
        return areEqualDouble(object.getBoundsInParent().getMinY(), entity.getNode().getBoundsInParent().getMaxY(), 1) ||
                areEqualDouble(object.getBoundsInParent().getMaxY(), entity.getNode().getBoundsInParent().getMinY(), 1);
    }

    private boolean checkCornersX(Moveables entity, Node object) {
        return areEqualDouble(object.getBoundsInParent().getMaxX(), entity.getNode().getBoundsInParent().getMinX(), 1) ||
                areEqualDouble(object.getBoundsInParent().getMinX(), entity.getNode().getBoundsInParent().getMaxX(), 1);
    }

    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    public boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }


}
