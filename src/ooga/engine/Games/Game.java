package ooga.engine.games;

import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

import java.util.Collection;

public abstract class Game implements GamePlay {
    public static final double GRAVITY = -9.8;
    public static final double NEGATIVE_DIRECTION = -1;
    public static final double HALF = .5;
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



    public Game(Collection<Obstacle> obstacles, Collection<Entity> entities, double timeElapsed){
        this.obstacles = obstacles;
        this.entities = entities;
        this.dt = timeElapsed;
        elapsedTime = timeElapsed;
        jumpInitialVelocity = calculateJumpVelocity();
    }

    private double calculateJumpVelocity(){
        //double jumpMaxHeight = screenHeight * JUMP_TO_SCREEN_HEIGHT_RATIO;
        //get jumpMaxHeight from entity
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
                initialVelocityX = 0;
            }
        }
    }

    private double newYPosition (double height, double velocity){

        return height + velocity * elapsedTime + HALF * yForceEntity * elapsedTime * elapsedTime;
        //return height + initialVelocityY * dt + HALF * yForceEntity * dt * dt / massEntity;
    }

    private double newXPosition (double xPos, double velocity){
       // System.out.println(xPos + velocity * dt + HALF * xForceEntity * dt * dt);
        return xPos + velocity * elapsedTime + HALF * xForceEntity * elapsedTime * elapsedTime;
        //return xPos + initialVelocityX * dt + HALF * xForceEntity * dt * dt / massEntity;
    }

    private double getXForceEntity(Entity entity){
        //make previous array or attribuute of entity
        double changeInX = entity.getPreviousX() - entity.getX();
        return (changeInX - entity.getVelocityX() * elapsedTime) / (HALF * elapsedTime * elapsedTime);
    }

    private double getYForceEntity(Entity entity){
        //make previous array or attribuute of entity
        double changeInY = entity.getPreviousY() - entity.getY();
        return (changeInY - entity.getVelocityY() * elapsedTime) / (HALF * elapsedTime * elapsedTime);
    }

    private void updatePosition(Entity entity){
        if(jump){
            elapsedTime += dt;
        }
        entity.setPreviousX(entity.getX());
        entity.setPreviousY(entity.getY());
        entity.setY(newYPosition(entity.getY(), entity.getVelocityY()));
        entity.setX(newXPosition(entity.getX(), entity.getVelocityX()));
       // System.out.println(entity.getX());
    }

    private void gravityForce(){
        yForceEntity += GRAVITY;
    }

    private void collisionForce(Entity entity, Obstacle obstacle){
        if(obstacle.getNodeObject().intersects(entity.getNode().getBoundsInParent())){
            obstacleTopCollision(entity, obstacle);
            obstacleBottomCollision(entity, obstacle);
            obstacleRightCollision(entity, obstacle);
            obstacleLeftCollision(entity, obstacle);
        }
    }

    private void obstacleLeftCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getLayoutBounds().getMinX() < entity.getNode().getLayoutBounds().getMaxX()){
            xForceEntity -= getXForceEntity(entity);
        }

    }

    private void obstacleRightCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getLayoutBounds().getMaxX() > entity.getNode().getLayoutBounds().getMinY()){
            xForceEntity -= getXForceEntity(entity);
        }

    }

    private void obstacleBottomCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getLayoutBounds().getMaxY() > entity.getNode().getLayoutBounds().getMinY()) {
            yForceEntity += getYForceEntity(entity);
            //yForceEntity -= getYForceEntity(entity);
        }
    }

    private void obstacleTopCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getLayoutBounds().getMinY() < entity.getNode().getLayoutBounds().getMaxY()) {
            yForceEntity -= NEGATIVE_DIRECTION * GRAVITY;
            entity.setVelocityY(0);
            elapsedTime = dt;
            jump = false;
            //initialVelocityY = 0;
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
        entity.setVelocityX(-10);
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
        entity.setVelocityX(10);
    }

    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    public boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }


}

