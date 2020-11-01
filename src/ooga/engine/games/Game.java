
package ooga.engine.games;

import javafx.scene.input.KeyCode;
import ooga.engine.games.GamePlay;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

import java.util.Collection;

public abstract class Game implements GamePlay {
    public static final double GRAVITY = 9.8;
    public static final double NEGATIVE_DIRECTION = -1;
    public static final double NO_INITIAL_VELOCITY = 0;
    public static final double NO_FORCE = 0;
    public static final double JUMP_TO_SCREEN_HEIGHT_RATIO = .3;
    private Collection<Obstacle> obstacles;
    private Collection<Entity> entities;
    private double dt;
    private double jumpInitialVelocity;
    private double initialVelocityY;
    private double initialVelocityX = 0;
    private boolean jump = false;
    // private int screenHeight;
    private double jumpMaxHeight = 100;
    private double xForceEntity = 0;
    private double yForceEntity = 0;
    private double massEntity;
    private double elapsedTime;
    // private double massObstacle;

//add 'is finished' to confirm if the game has been finished

  // check solidity aspect of obstacle by having boolean that is see through

    public Game(Collection<Obstacle> obstacles, Collection<Entity> entities, double timeElapsed){
        this.obstacles = obstacles;
        this.entities = entities;
        this.dt = timeElapsed;
        elapsedTime = timeElapsed;
        jumpInitialVelocity = calculateJumpVelocity();
    }

    public boolean hasFinished(){
        return true;
    }

    private double calculateJumpVelocity(){
        //double jumpMaxHeight = screenHeight * JUMP_TO_SCREEN_HEIGHT_RATIO;
        //get jumpMaxHeight from entity
        return NEGATIVE_DIRECTION * Math.sqrt((jumpMaxHeight * GRAVITY));
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

    private double getFinalVelocity(){
        //v_final = v_initial + acceleration*time;
        return initialVelocityY- GRAVITY* elapsedTime;
    }

    public void updateEntity(){
        for(Entity entity : entities) {
            //System.out.println(yForceEntity);
            //System.out.println(entity.getVelocityY());
            xForceEntity = 0;
            yForceEntity = 0;
            gravityForce();
            for (Obstacle obstacle : obstacles) {
                collisionForce(entity, obstacle);
                updatePosition(entity);
                entity.setVelocityX(0);
            }
        }
    }

    private double newYPosition (Entity entity){
        return entity.getY() + entity.getVelocityY() * elapsedTime + yForceEntity * elapsedTime * elapsedTime;
    }

    private double newXPosition (Entity entity){
        return entity.getX() + entity.getVelocityX() * elapsedTime + xForceEntity * elapsedTime * elapsedTime;
    }

    private double getXForceEntity(Entity entity){
        //make previous array or attribuute of entity
        double changeInX = entity.getPreviousX() - entity.getX();
        return (changeInX - entity.getVelocityX() * elapsedTime) / (elapsedTime * elapsedTime);
    }

    private double getYForceEntity(Entity entity){
        //make previous array or attribuute of entity
        double changeInY = entity.getPreviousY() - entity.getY();
        return (changeInY - entity.getVelocityY() * elapsedTime) / (elapsedTime * elapsedTime);
    }

    private void updatePosition(Entity entity){
        if(jump){
            elapsedTime += dt;
        }
        entity.setPreviousX(entity.getX());
        entity.setPreviousY(entity.getY());
        entity.setY(newYPosition(entity));
        entity.setX(newXPosition(entity));
    }

    private void gravityForce(){
        yForceEntity += GRAVITY;
    }

    private void collisionForce(Entity entity, Obstacle obstacle){
        if(obstacle.getNodeObject().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())){
            obstacleTopCollision(entity, obstacle);
            obstacleBottomCollision(entity, obstacle);
            obstacleRightCollision(entity, obstacle);
            obstacleLeftCollision(entity, obstacle);
        }
    }

    private void obstacleLeftCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMinX() < entity.getNode().getBoundsInParent().getMaxX()){
            xForceEntity += getXForceEntity(entity);
        }

    }

    private void obstacleRightCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMaxX() > entity.getNode().getBoundsInParent().getMinY()){
            xForceEntity -= getXForceEntity(entity);
        }

    }

    private void obstacleBottomCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMaxY() > entity.getNode().getBoundsInParent().getMinY() &&
                obstacle.getNodeObject().getBoundsInParent().getMaxY() < entity.getNode().getBoundsInParent().getMaxY()) {
            entity.setY(obstacle.getNodeObject().getBoundsInParent().getMaxY());
            System.out.println("bottom");
            yForceEntity += getYForceEntity(entity);
        }
    }

    private void obstacleTopCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMinY() < entity.getNode().getBoundsInParent().getMaxY() &&
                obstacle.getNodeObject().getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent().getMinY()) {
            entity.setY(obstacle.getNodeObject().getBoundsInParent().getMinY());
            yForceEntity += NEGATIVE_DIRECTION * GRAVITY;
            entity.setVelocityY(0);
            elapsedTime = dt;
            jump = false;
        }
    }


    //CODE BELOW
//keypress should be in display called method through reflection
 /*   private void UP(Entity entity){
        entity.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.UP){ // needd to move out so we can control double jumps
                initialVelocityY = jumpInitialVelocity;
            }
        });
    }*/
    public void UP(Entity entity){
        jump = true;
        entity.setVelocityY(entity.getJumpMax());
        //initialVelocityY = jumpInitialVelocity;
    }

  /*  private void LEFT(Entity entity){
        entity.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.LEFT){
                initialVelocityX = NEGATIVE_DIRECTION * X_VELOCITY;
            }
        });
    }*/

    public void LEFT(Entity entity){
        entity.setPreviousX(entity.getX());
        //entity.moveLeft();
        //initialVelocityX = NEGATIVE_DIRECTION * X_VELOCITY;
        entity.setVelocityX(-20);
    }

  /*  private void RIGHT(Entity entity){
        entity.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.RIGHT){
                initialVelocityX = X_VELOCITY;
            }
        });
    }
   */

    public void RIGHT(Entity entity){
        entity.setPreviousX(entity.getX());
       // entity.moveRight();
        //initialVelocityX = X_VELOCITY;
        entity.setVelocityX(20);
    }

    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    public boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }


}

