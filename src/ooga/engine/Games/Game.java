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
    private Bounds dimensions;
    private Collection<Obstacle> obstacles;
    private Collection<Entity> entities;
    private Collection<Obstacle> currentObstacles;
    private double timeElapsed;

    public Game(int screenWidth, int screenHeight, Collection<Obstacle> obstacles, Collection<Entity> entities, double timeElapsed){
        this.dimensions = new BoundingBox(0, 0, screenWidth, screenHeight);
        this.obstacles = obstacles;
        this.entities = entities;
        this.timeElapsed = timeElapsed;
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
        return entities;
    }

    private void updateEntity(){
        for(Entity entity : entities) {
            for (Obstacle obstacle : currentObstacles) {
                if(!obstacle.hasCollided(entity)){
                   //determine if entity collides with top of obstacle or side
                    applyGravity(entity);
                }
            }
        }
    }

    private double gravity(double initialVelocity, double timeElapsed, double height){
        return height + initialVelocity * timeElapsed + HALF * GRAVITY * timeElapsed * timeElapsed;
    }

    private void applyGravity(Entity entity){
        entity.setY(gravity(NO_INITIAL_VELOCITY, timeElapsed, entity.getY()));
    }



}
