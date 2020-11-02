
package ooga.engine.games;

import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

import java.util.Collection;

public abstract class Game implements GamePlay {
    public static final double GRAVITY = 9.8;
    public static final double NEGATIVE_DIRECTION = -1;
    public static final double NO_INITIAL_VELOCITY = 0;
    public static final double NO_FORCE = 0;
    public static final double MOVE_FORCE = 10;
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

        return NEGATIVE_DIRECTION * Math.sqrt((jumpMaxHeight * GRAVITY));
    }

    public Collection<Obstacle> getBackground(){
        return obstacles;
    }

    public void updateLevel(){
        updateEntity();
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
            gravityForce();
            for (Obstacle obstacle : obstacles) {
                collisionForce(entity, obstacle);
                updatePosition(entity);
            }
            xForceEntity = 0;
            yForceEntity = 0;
        }
    }

    private double newYPosition (Entity entity){
        return entity.getMaxY() + entity.getVelocityY() * elapsedTime + yForceEntity * elapsedTime * elapsedTime;
    }

    private double newXPosition (Entity entity){
        return entity.getCenterX() + entity.getVelocityX() * elapsedTime + xForceEntity * elapsedTime * elapsedTime;
    }

    private double getXForceEntity(Entity entity){
        double changeInX = entity.getPreviousX() - entity.getCenterX();
        return (changeInX - entity.getVelocityX() * elapsedTime) / (elapsedTime * elapsedTime);
    }

    private double getYForceEntity(Entity entity){
        //make previous array or attribuute of entity
        double changeInY = entity.getPreviousY() - entity.getMaxY();
        return (changeInY - entity.getVelocityY() * elapsedTime) / (elapsedTime * elapsedTime);
    }

    private void updatePosition(Entity entity){
        if(jump){
            elapsedTime += dt;
        }
        entity.setPreviousX(entity.getCenterX());
        entity.setPreviousY(entity.getMaxY());
        entity.setMaxY(newYPosition(entity));
        entity.setCenterX(newXPosition(entity));
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

    private boolean checkCorners(Entity entity, Obstacle obstacle){
        return obstacle.getNodeObject().getBoundsInParent().getMinY() != entity.getNode().getBoundsInParent().getMaxY() &&
                obstacle.getNodeObject().getBoundsInParent().getMaxY() != entity.getNode().getBoundsInParent().getMinY();
    }

    private void obstacleLeftCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMaxX() > entity.getNode().getBoundsInParent().getMinX() &&
                obstacle.getNodeObject().getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent().getMaxX() &&
                xForceEntity < 0  && checkCorners(entity, obstacle)){
            System.out.println("left");
            xForceEntity -= MOVE_FORCE;
            entity.setCenterX(obstacle.getNodeObject().getBoundsInParent().getMaxX() + entity.getEntityWidth()/2);
        }

    }

    private void obstacleRightCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMinX() < entity.getNode().getBoundsInParent().getMaxX() &&
                obstacle.getNodeObject().getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent().getMinX() &&
                xForceEntity > 0 && checkCorners(entity, obstacle)){
            System.out.println("right");
            xForceEntity += MOVE_FORCE;
            entity.setCenterX(obstacle.getNodeObject().getBoundsInParent().getMinX() - entity.getEntityWidth()/2);
        }

    }

    private void obstacleBottomCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMaxY() > entity.getNode().getBoundsInParent().getMinY() &&
                obstacle.getNodeObject().getBoundsInParent().getMaxY() < entity.getNode().getBoundsInParent().getMaxY()) {
            System.out.println("bottom");
            //yForceEntity -= getYForceEntity(entity);
        }
    }

    private void obstacleTopCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMinY() < entity.getNode().getBoundsInParent().getMaxY() &&
                obstacle.getNodeObject().getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent().getMinY()) {
            entity.setMaxY(obstacle.getNodeObject().getBoundsInParent().getMinY());
            yForceEntity += NEGATIVE_DIRECTION * GRAVITY;
            System.out.println("top");
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
        entity.setPreviousX(entity.getCenterX());
        //entity.moveLeft();
        //initialVelocityX = NEGATIVE_DIRECTION * X_VELOCITY;
        //entity.setVelocityX(-20);
        xForceEntity -= MOVE_FORCE;

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
        entity.setPreviousX(entity.getCenterX());
       // entity.moveRight();
        //initialVelocityX = X_VELOCITY;
        xForceEntity += MOVE_FORCE;
    }

    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    public boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }


}

