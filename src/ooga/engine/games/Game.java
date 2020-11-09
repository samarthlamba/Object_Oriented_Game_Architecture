
package ooga.engine.games;

import javafx.scene.Node;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Movable;
import ooga.engine.entities.Bounds;
import ooga.engine.obstacles.Obstacle;
import ooga.view.GamePlayScreen;

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
    Collection<Movable> entities;
    private double dt;
    private double jumpInitialVelocity;
    private double initialVelocityY;
    private double initialVelocityX = 0;
    private boolean jump = false;
    // private int screenHeight;
    private double jumpMaxHeight = 10;
    private double massMovable;
    private double moveVelocity = 10;
    private boolean objectAtCorner;
    private int enemyDirection = -1;
    private Set<String> collisionTypes = Set.of("right", "left", "top", "bottom");
    Collisions handleCollisions;
    private int totalPoints = 0;
    private GamePlayScreen tempGamePlayScreen = new GamePlayScreen();


//add 'is finished' to confirm if the game has been finished

    // check solidity aspect of obstacle by having boolean that is see through


    public Game(Collection<Obstacle> obstacles, Collection<Movable> entities, double timeElapsed) {
        this.obstacles = obstacles;
        this.entities = entities;
        handleCollisions = new Collisions();
        for (Movable entity : entities) {
            entity.setTimeElapsedY(timeElapsed);
            entity.setTimeElapsedX(timeElapsed);
        }
        this.dt = timeElapsed;
    }

    public abstract boolean hasFinished();

    public Collection<? extends Node> getBackground() {
        return obstacles;
    }


    public void updateLevel() {
        // System.out.println("stepped123");
        updateMovable();
    }

    public Collection<? extends Bounds> getEntities() {
        updateMovable();
        return entities ;
    }

    protected void updateMovable() {
        // System.out.println("stepped12324");
        for (Movable entity : entities) {
            moveMovable(entity);
        }
        removeMovable();
    }

    protected void moveMovable(Movable entity) {
        if (entity.isJump() && entity.getTimeElapsedY() < .35) {
            entity.setTimeElapsedY(entity.getTimeElapsedY() + entity.getTimeElapsedX());
        }
        if (entity.getId().equals("player")) {
            entity.setJump(true);
        }
        gravityForce(entity);
        obstacleCollision(entity);
        entityCollision(entity);
        moveEnemy(entity);
        updatePosition(entity);
        // System.out.println("force" + entity.getYForce());
        entity.setYForce(0);
        entity.setXForce(0);
        if(!entity.getStatusAlive()){
            tempGamePlayScreen.remove(entity);
        }
    }

    protected void removeMovable() {
        entities.removeIf(e -> !e.getStatusAlive());
    }


    protected void moveEnemy(Movable entity) {
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

    public void obstacleCollision(Movable entity) {
        for (Node obstacle : obstacles) {
            Node object = obstacle;
            collisions(entity, (Collideable) object);
        }
       /* if(!entity.getId().equals("player")){
            collisions(findMainPlayer(), entity);
        }*/
    }

    public void entityCollision(Movable entity){
        for(Movable e : entities){
            if(entity != e){
                collisions(entity, (Collideable) e);
            }
        }
    }


    protected boolean checkCornersMovableX(Movable player, Movable entity) {
        return areEqualDouble(entity.getNode().getBoundsInParent().getMaxX(), player.getNode().getBoundsInParent().getMinX(), 1) ||
                areEqualDouble(entity.getNode().getBoundsInParent().getMinX(), player.getNode().getBoundsInParent().getMaxX(), 1);
    }

    private double newYPosition(Movable entity) {
        double change = entity.getMaxY() + entity.getVelocityY() * entity.getTimeElapsedY() + entity.getYForce() * entity.getTimeElapsedY() * entity.getTimeElapsedY();
        return entity.getMaxY() + entity.getVelocityY() * entity.getTimeElapsedY() + entity.getYForce() * entity.getTimeElapsedY() * entity.getTimeElapsedY();
    }

    private double newXPosition(Movable entity) {
        return entity.getCenterX() + entity.getVelocityX() * entity.getTimeElapsedX() + entity.getXForce() * entity.getTimeElapsedX() * entity.getTimeElapsedX();
    }

    private void updatePosition(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
        double c = entity.getMaxY();
        entity.setPreviousY(entity.getMaxY());
        entity.setMaxY(newYPosition(entity));
        entity.setCenterX(newXPosition(entity));
    }

    private void gravityForce(Movable entity) {
        if(entity.hasGravity()) {
            entity.setYForce(entity.getYForce() + GRAVITY);
        }
    }

    public void collisions(Movable entity, Collideable object) {
        if (object.getNode().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
            handleCollisions.collisions((Entity) entity, object);
        }
    }

    public Movable findMainPlayer() {
        for (Movable entity : entities) {
            if (entity.getId().equals("player")) {
                return entity;
            }
        }
        throw new RuntimeException("No main player found");
    }

    public void moveRight() {
        Movable entity = findMainPlayer();
        RIGHT(entity);
    }

    public void moveLeft() {
        Movable entity = findMainPlayer();
        LEFT(entity);
    }

    public void moveUp() {
        Movable entity = findMainPlayer();
        UP(entity);
    }

    public void shoot(){}

    public void UP(Movable entity) {
        entity.setJump(true);
        entity.setVelocityY(entity.getJumpMax());
        entity.setMaxY(entity.getMaxY() - 2);
    }


    public void LEFT(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setXForce(entity.getXForce() - MOVE_FORCE);
        entity.setFacing(false);
    }


    public void RIGHT(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setXForce(entity.getXForce() + MOVE_FORCE);
        entity.setFacing(true);
    }


    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    public boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }

}


