
package ooga.engine.games;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Moveables;
import ooga.engine.obstacles.Collideable;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.round;

public abstract class Game implements GamePlay {
    public static final double GRAVITY = 800;
    public static final double NEGATIVE_DIRECTION = -1;
    public static final double NO_INITIAL_VELOCITY = 0;
    public static final double NO_FORCE = 0;
    public static final double MOVE_FORCE = 50000; //TODO change to 10
    private Collection<Collideable> obstacles;
    private Collection<Moveables> entities;
    private double dt;
    private double jumpInitialVelocity;
    private double initialVelocityY;
    private double initialVelocityX = 0;
    private boolean jump = false;
    // private int screenHeight;
    private double jumpMaxHeight = 10;
    private double massMoveables;
    private double moveVelocity = 10;
    private boolean objectAtCorner;
    private int enemyDirection =-1;
    private Set<String> collisionTypes = Set.of("right", "left", "top", "bottom");
    Collisions handleCollisions;
    // private double massCollideable;


//add 'is finished' to confirm if the game has been finished

    // check solidity aspect of obstacle by having boolean that is see through

    public Game(Collection<Collideable> obstacles, Collection<Moveables> entities, double timeElapsed) {
        this.obstacles = obstacles;
        this.entities = entities;
        handleCollisions = new Collisions(obstacles, entities);
        for(Moveables entity : entities){
            entity.setTimeElapsedY(timeElapsed);
            entity.setTimeElapsedX(timeElapsed);
        }
        this.dt = timeElapsed;
        jumpInitialVelocity = calculateJumpVelocity();
    }

    public boolean hasFinished() {
        return true;
    }

    private double calculateJumpVelocity() {

        return NEGATIVE_DIRECTION * Math.sqrt((jumpMaxHeight * GRAVITY));
    }

    public Collection<Collideable> getBackground() {
        return obstacles;
    }

    @Override
    public void updateLevel() {
       // System.out.println("stepped123");
        updateMoveables();
    }

    public Collection<Moveables> getEntities() {
        updateMoveables();
        return entities;
    }

    public void updateMoveables() {
       // System.out.println("stepped12324");
        for (Moveables entity : entities) {
            if (entity.isJump() && entity.getTimeElapsedY() < .35) {
                entity.setTimeElapsedY(entity.getTimeElapsedY() + entity.getTimeElapsedX());
            }
            if(entity.getId().equals("player")) {
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
    }





    public void moveEnemy(Moveables entity) {
        if(entity.getId().equals("enemy")){
           // System.out.println("prev " + entity.getPreviousY() + " now " + entity.getMaxY());
            if(entity.getPreviousY() != entity.getMaxY()){
                entity.setMaxY(entity.getPreviousY());
                entity.setCenterX(entity.getPreviousX());
                entity.setVelocityX(entity.getVelocityX()*-1);
                // double c = entity.getMaxY();
            }
        }

    }

    public void collisionForce(Moveables entity) {
        for (Collideable obstacle : obstacles) {
            Node object = obstacle.getNodeObject();
            collisions(entity, object);
        }
        playerEnemyCollision(entity);
    }

    protected abstract void playerEnemyCollision(Moveables entity);


    protected boolean entityCollision(Moveables player, Moveables e) {
        if (e.getId().equals("enemy")) {
            return player.getNode().getBoundsInParent().intersects(e.getNode().getBoundsInParent());
        }
        return false;
    }

    protected boolean checkCornersMoveablesX(Moveables player, Moveables entity) {
        return areEqualDouble(entity.getNode().getBoundsInParent().getMaxX(), player.getNode().getBoundsInParent().getMinX(), 1) ||
                areEqualDouble(entity.getNode().getBoundsInParent().getMinX(), player.getNode().getBoundsInParent().getMaxX(), 1);
    }

    private double newYPosition(Moveables entity) {
        double change = entity.getMaxY() + entity.getVelocityY() * entity.getTimeElapsedY() + entity.getYForce() * entity.getTimeElapsedY() * entity.getTimeElapsedY();
        return entity.getMaxY() + entity.getVelocityY() * entity.getTimeElapsedY() + entity.getYForce() * entity.getTimeElapsedY() * entity.getTimeElapsedY();
    }

    private double newXPosition(Moveables entity) {
        return entity.getCenterX() + entity.getVelocityX() * entity.getTimeElapsedX() + entity.getXForce() * entity.getTimeElapsedX() * entity.getTimeElapsedX();
    }

    private void updatePosition(Moveables entity) {
        entity.setPreviousX(entity.getCenterX());
        double c = entity.getMaxY();
        entity.setPreviousY(entity.getMaxY());
        entity.setMaxY(newYPosition(entity));
        entity.setCenterX(newXPosition(entity));
    }

    private void gravityForce(Moveables entity) {
        entity.setYForce(entity.getYForce() + GRAVITY);
    }


    public void collisions(Moveables entity, Node object) {
        if (object.getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
            handleCollisions.collisions(entity, object);
        }
    }

    private Moveables findMainPlayer() {
        for (Moveables entity : entities) {
            if (entity.getId().equals("player")) {
                return entity;
            }
        }
        throw new RuntimeException("No main player found");
    }

    public void moveRight() {
        Moveables entity = findMainPlayer();
        RIGHT(entity);
    }

    public void moveLeft() {
        Moveables entity = findMainPlayer();
        LEFT(entity);
    }

    public void moveUp() {
        Moveables entity = findMainPlayer();
        UP(entity);
    }

        public void UP (Moveables entity){
            entity.setJump(true);
            entity.setVelocityY(entity.getJumpMax());
            entity.setMaxY(entity.getMaxY() - 2);
        }


        public void LEFT (Moveables entity){
            entity.setPreviousX(entity.getCenterX());
            entity.setXForce(entity.getXForce() - MOVE_FORCE);

        }


        public void RIGHT (Moveables entity){
            entity.setPreviousX(entity.getCenterX());
            entity.setXForce(entity.getXForce() + MOVE_FORCE);
        }

        //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
        public boolean areEqualDouble ( double a, double b, int precision){
            return Math.abs(a - b) <= Math.pow(10, -precision);
        }

}


