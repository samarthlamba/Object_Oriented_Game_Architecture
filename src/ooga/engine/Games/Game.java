package ooga.engine.Games;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

import java.util.Collection;

public abstract class Game implements GamePlay {
    private static final double GRAVITY = 9.8;
    public static final double HALF = .5;
    public static final double NO_INITIAL_VELOCITY = 0;
    public static final double JUMP_TO_SCREEN_HEIGHT_RATIO = .3;
    private Bounds dimensions;
    private Collection<Obstacle> obstacles;
    private Collection<Entity> entities;
    private Collection<Obstacle> currentObstacles;
    private double timeElapsed;
    private double jumpInitialVelocity;



    public Game(int screenWidth, int screenHeight, Collection<Obstacle> obstacles, Collection<Entity> entities, double timeElapsed){
        this.dimensions = new BoundingBox(0, 0, screenWidth, screenHeight);
        this.obstacles = obstacles;
        this.entities = entities;
        this.timeElapsed = timeElapsed;
        this.currentObstacles = obstacles;
        jumpInitialVelocity = calculateJumpVelocity(screenHeight);
    }

    private double calculateJumpVelocity(int screenHeight){
        double jumpMaxHeight = screenHeight * JUMP_TO_SCREEN_HEIGHT_RATIO;
        return Math.sqrt((jumpMaxHeight * GRAVITY) / HALF);
    }

    public Collection<Obstacle> getBackground(){
        for(Obstacle obstacle : obstacles){
            if(inScreenBounds(obstacle)){
                currentObstacles.add(obstacle);
            }
            else{
                currentObstacles.remove(obstacle);
            }
        }
        return currentObstacles;
    }

    private boolean inScreenBounds(Node object){
        return object.getBoundsInParent().getMaxX() >= dimensions.getMinX()
                || object.getBoundsInParent().getMinX() <= dimensions.getMaxX();
    }

    public void updateLevel(){

    }

    public Collection<Entity> getEntities(){
        updateEntity();
        return entities;
    }

    private void updateEntity(){
        for(Entity entity : entities) {
            for (Obstacle obstacle : currentObstacles) {
               applyGravity(entity, obstacle, NO_INITIAL_VELOCITY);
            }
        }
    }

    private double gravity(double initialVelocity, double timeElapsed, double height){
        return height + initialVelocity * timeElapsed + HALF * GRAVITY * timeElapsed * timeElapsed;
    }

    private void applyGravity(Entity entity, Obstacle obstacle, double initialVelocity){
        if(!obstacle.hasCollided(entity)){
            //determine if entity collides with top of obstacle
            entity.setY(gravity(initialVelocity, timeElapsed, entity.getY()));
        }
    }

    private void applyJump(Entity entity, Obstacle obstacle){
        applyGravity(entity, obstacle, jumpInitialVelocity);
    }


}
