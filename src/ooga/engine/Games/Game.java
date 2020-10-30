package ooga.engine.Games;

import javafx.scene.input.KeyCode;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

import java.util.Collection;

public abstract class Game implements GamePlay {
    public static final double GRAVITY = 9.8;
    public static final double NEGATIVE_DIRECTION = -1;
    public static final double HALF = .5;
    public static final double NO_INITIAL_VELOCITY = 0;
    public static final double NO_FORCE = 0;
    public static final double JUMP_TO_SCREEN_HEIGHT_RATIO = .3;
    public static final double X_VELOCITY = 10;
    private Collection<Obstacle> obstacles;
    private Collection<Entity> entities;
    private double dt;
    private double jumpInitialVelocity;
    private double initialVelocityY;
    private double initialVelocityX = 0;
    private boolean jump = false;
    private int screenHeight;
    private double xForceEntity = 0;
    private double yForceEntity = 0;
    private double massEntity;
    private double massObstacle;
    private double previousEntityX;
    private double previousEntityY;



    public Game(int screenHeight, Collection<Obstacle> obstacles, Collection<Entity> entities, double timeElapsed){
        this.obstacles = obstacles;
        this.entities = entities;
        this.dt = timeElapsed;
        this.screenHeight = screenHeight;
        jumpInitialVelocity = calculateJumpVelocity(screenHeight);
    }

    private double calculateJumpVelocity(int screenHeight){
        double jumpMaxHeight = screenHeight * JUMP_TO_SCREEN_HEIGHT_RATIO;
        return NEGATIVE_DIRECTION * Math.sqrt((jumpMaxHeight * GRAVITY) / HALF);
    }

    public Collection<Obstacle> getBackground(){
        return obstacles;
    }

    public void updateLevel(){

    }

    public Collection<Entity> getEntities(){
        updateEntity();
        return entities;
    }

    private void updateEntity(){
        for(Entity entity : entities) {
            for (Obstacle obstacle : obstacles) {
                UP(entity);
                LEFT(entity);
                RIGHT(entity);
                massEntity = entity.getMass();
                massObstacle = obstacle.getMass();
                gravityForce();
                collisionForce(entity, obstacle);
                updatePosition(entity);
            }
        }
    }

    private double newYPosition (double height){
        return height + initialVelocityY * dt + HALF * yForceEntity * dt * dt / massEntity;
    }

    private double newXPosition (double xPos){
        return xPos + initialVelocityX * dt + HALF * xForceEntity * dt * dt / massEntity;
    }

    private double getXForceEntity(Entity entity){
        double changeInX = previousEntityX - entity.getX();
        return (changeInX - initialVelocityX * dt) / (HALF * dt * dt / massEntity);
    }

    private double getYForceEntity(Entity entity){
        double changeInY = previousEntityY - entity.getY();
        return (changeInY - initialVelocityY * dt) / (HALF * dt * dt / massEntity);
    }

    private void updatePosition(Entity entity){
        previousEntityX = entity.getX();
        previousEntityY = entity.getY();
        entity.setY(newYPosition(entity.getY()));
        entity.setX(newXPosition(entity.getX()));
    }

    private void gravityForce(){
        yForceEntity += massEntity * GRAVITY;
    }

    private void collisionForce(Entity entity, Obstacle obstacle){
        if(obstacle.intersects(entity.getBoundsInParent())){
            if(obstacle.getLayoutBounds().getMinY() < entity.getLayoutBounds().getMaxY()) {
                yForceEntity += NEGATIVE_DIRECTION * massEntity * GRAVITY;
                initialVelocityY = 0;
            }

            if(obstacle.getLayoutBounds().getMaxY() > entity.getLayoutBounds().getMaxY()) {
               yForceEntity -= getYForceEntity(entity);
            }

            if(obstacle.getLayoutBounds().getMaxX() > entity.getLayoutBounds().getMinY()){
                xForceEntity -= getXForceEntity(entity);
            }

            if(obstacle.getLayoutBounds().getMinX() < entity.getLayoutBounds().getMaxX()){
                xForceEntity -= getXForceEntity(entity);
            }
        }
    }


//CODE BELOW
//keypress should be in display called method through reflection
    private void UP(Entity entity){
        entity.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.UP){
                initialVelocityY = jumpInitialVelocity;
            }
        });
    }

    private void LEFT(Entity entity){
        entity.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.LEFT){
                initialVelocityX = NEGATIVE_DIRECTION * X_VELOCITY;
            }
        });
    }

    private void RIGHT(Entity entity){
        entity.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.RIGHT){
                initialVelocityX = X_VELOCITY;
            }
        });
    }



}
