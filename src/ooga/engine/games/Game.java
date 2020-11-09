
package ooga.engine.games;

import javafx.scene.Node;
import ooga.engine.entities.Bullet;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Moveable;
import ooga.engine.obstacles.Collideable;
import ooga.engine.obstacles.Obstacle;

import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.round;

public abstract class Game implements GamePlay {
    public static final double GRAVITY = 800;
    public static final double NEGATIVE_DIRECTION = -1;
    public static final double NO_INITIAL_VELOCITY = 0;
    public static final double NO_FORCE = 0;
    public static final double MOVE_FORCE = 50000; //TODO change to 10
    private static final int BULLET_WIDTH = 10;
    private static final int BULLET_HEIGHT = 3;
    private static final double BULLET_VELOCITY = -30;
    Collection<Obstacle> obstacles;
    Collection<Entity> entities;
    private double dt;
    private double jumpInitialVelocity;
    private double initialVelocityY;
    private double initialVelocityX = 0;
    private boolean jump = false;
    // private int screenHeight;
    private double jumpMaxHeight = 10;
    private double massEntity;
    private double moveVelocity = 10;
    private boolean objectAtCorner;
    private int enemyDirection = -1;
    private Set<String> collisionTypes = Set.of("right", "left", "top", "bottom");
    Collisions handleCollisions;


//add 'is finished' to confirm if the game has been finished

    // check solidity aspect of obstacle by having boolean that is see through


    public Game(Collection<Obstacle> obstacles, Collection<Entity> entities, double timeElapsed) {
        this.obstacles = obstacles;
        this.entities = entities;
        handleCollisions = new Collisions(obstacles, entities);
        for (Entity entity : entities) {
            entity.setTimeElapsedY(timeElapsed);
            entity.setTimeElapsedX(timeElapsed);
        }
        this.dt = timeElapsed;
        jumpInitialVelocity = calculateJumpVelocity();
    }

    public abstract boolean hasFinished();

    private double calculateJumpVelocity() {

        return NEGATIVE_DIRECTION * Math.sqrt((jumpMaxHeight * GRAVITY));
    }

    public Collection<? extends Collideable> getBackground() {
        return obstacles;
    }

    @Override
    public void updateLevel() {
        // System.out.println("stepped123");
        updateEntity();
    }

    public Collection<? extends Moveable> getEntities() {
        updateEntity();
        return entities ;
    }

    public void updateEntity() {

        // System.out.println("stepped12324");
        for (Entity entity : entities) {
            if (entity.isJump() && entity.getTimeElapsedY() < .35) {
                entity.setTimeElapsedY(entity.getTimeElapsedY() + entity.getTimeElapsedX());
            }
            if (entity.getId().equals("player")) {
                entity.setJump(true);
            }
            gravityForce(entity);
            collisionForce(entity);
            moveEnemy(entity);
            updatePosition(entity);
            // System.out.println("force" + entity.getYForce());
            entity.setYForce(0);
            entity.setXForce(0);

        }

        entities.removeIf(e -> e.getStatusAlive() == false);
    }


    public void moveEnemy(Entity entity) {
        if (entity.getId().equals("enemy")) {
            // System.out.println("prev " + entity.getPreviousY() + " now " + entity.getMaxY());
            if (entity.getPreviousY() != entity.getMaxY()) {
                entity.setMaxY(entity.getPreviousY());
                entity.setCenterX(entity.getPreviousX());
                entity.setVelocityX(entity.getVelocityX() * -1);
                // double c = entity.getMaxY();
            }
        }

    }

    public void collisionForce(Entity entity) {
        for (Obstacle obstacle : obstacles) {
            Node object = obstacle.getNodeObject();
            collisions(entity, object);
        }
    }


    protected boolean checkCornersEntityX(Entity player, Entity entity) {
        return areEqualDouble(entity.getNode().getBoundsInParent().getMaxX(), player.getNode().getBoundsInParent().getMinX(), 1) ||
                areEqualDouble(entity.getNode().getBoundsInParent().getMinX(), player.getNode().getBoundsInParent().getMaxX(), 1);
    }

    private double newYPosition(Entity entity) {
        double change = entity.getMaxY() + entity.getVelocityY() * entity.getTimeElapsedY() + entity.getYForce() * entity.getTimeElapsedY() * entity.getTimeElapsedY();
        return entity.getMaxY() + entity.getVelocityY() * entity.getTimeElapsedY() + entity.getYForce() * entity.getTimeElapsedY() * entity.getTimeElapsedY();
    }

    private double newXPosition(Entity entity) {
        return entity.getCenterX() + entity.getVelocityX() * entity.getTimeElapsedX() + entity.getXForce() * entity.getTimeElapsedX() * entity.getTimeElapsedX();
    }

    private void updatePosition(Entity entity) {
        entity.setPreviousX(entity.getCenterX());
        double c = entity.getMaxY();
        entity.setPreviousY(entity.getMaxY());
        entity.setMaxY(newYPosition(entity));
        entity.setCenterX(newXPosition(entity));
    }

    private void gravityForce(Entity entity) {
        if(entity.hasGravity()) {
            entity.setYForce(entity.getYForce() + GRAVITY);
        }
    }


    public void collisions(Entity entity, Node object) {
        if (object.getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
            handleCollisions.collisions(entity, object);
        }
    }

    Entity findMainPlayer() {
        for (Entity entity : entities) {
            if (entity.getId().equals("player")) {
                return entity;
            }
        }
        throw new RuntimeException("No main player found");
    }

    public void moveRight() {
        Entity entity = findMainPlayer();
        RIGHT(entity);
    }

    public void moveLeft() {
        Entity entity = findMainPlayer();
        LEFT(entity);
    }

    public void moveUp() {
        Entity entity = findMainPlayer();
        UP(entity);
    }

    public void UP(Entity entity) {
        entity.setJump(true);
        entity.setVelocityY(entity.getJumpMax());
        entity.setMaxY(entity.getMaxY() - 2);
    }


    public void LEFT(Entity entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setXForce(entity.getXForce() - MOVE_FORCE);
        entity.setFacing(false);
    }


    public void RIGHT(Entity entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setXForce(entity.getXForce() + MOVE_FORCE);
        entity.setFacing(true);
    }


    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    public boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }

}


