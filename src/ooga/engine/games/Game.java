
package ooga.engine.games;

import javafx.scene.input.KeyCode;
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
    private boolean objectAtCorner;
    private int enemyDirection =-1;
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
    @Override
    public void updateLevel(){
        System.out.println("stepped123");
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
        System.out.println("stepped12324");
        for(Entity entity : entities) {

            for (Obstacle obstacle : obstacles) {
                collisionForce(entity, obstacle);
                updatePosition(entity);
            }
            gravityForce();
            if(entity.getID() == 1){
                System.out.println("yforce" + yForceEntity);
                System.out.println(objectAtCorner);

                entity.setVelocityX(entity.getVelocityX()*enemyDirection);
                entity.update();

            }

            if(entity.getID() == 0){
                for(Entity e : entities){
                    if(entityCollision(entity, e)){
                        //player dead
                        //enemy dead if top collision
                    }
                }
            }
            objectAtCorner = false;
            xForceEntity = 0;
            yForceEntity = 0;
        }
        System.out.println(entities.size());
    }

    private boolean entityCollision(Entity player, Entity entity){
        if(entity.getID() != 0) {
            return player.getNode().getBoundsInParent().intersects(entity.getNode().getBoundsInParent());
        }
        return true;
    }

    private double newYPosition (Entity entity){
        return entity.getMaxY() + entity.getVelocityY() * elapsedTime + yForceEntity * elapsedTime * elapsedTime;
    }

    private double newXPosition (Entity entity){
        return entity.getCenterX() + entity.getVelocityX() * elapsedTime + xForceEntity * elapsedTime * elapsedTime;
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


    private boolean checkCornersY(Entity entity, Obstacle obstacle){
        return areEqualDouble(obstacle.getNodeObject().getBoundsInParent().getMinY(), entity.getNode().getBoundsInParent().getMaxY(), 1) ||
                areEqualDouble(obstacle.getNodeObject().getBoundsInParent().getMaxY(), entity.getNode().getBoundsInParent().getMinY(),1);
    }

    private boolean checkCornersX(Entity entity, Obstacle obstacle){
        return areEqualDouble(obstacle.getNodeObject().getBoundsInParent().getMaxX(), entity.getNode().getBoundsInParent().getMinX(), 1) ||
                areEqualDouble(obstacle.getNodeObject().getBoundsInParent().getMinX(), entity.getNode().getBoundsInParent().getMaxX(), 1);
    }

    private boolean cornerOverEdge(Entity entity, Obstacle obstacle) {
        return (entity.getNode().getBoundsInParent().getMaxX() > obstacle.getNodeObject().getBoundsInParent().getMaxX() &&
                entity.getNode().getBoundsInParent().getMinX() > obstacle.getNodeObject().getBoundsInParent().getMinX());

    }

    private boolean overEdge(Entity entity, Obstacle obstacle){
        double temp = obstacle.getNodeObject().getBoundsInParent().getMaxX();
        double temp2 =  entity.getNode().getBoundsInParent().getMaxX();
        double temp3 = obstacle.getNodeObject().getBoundsInParent().getMinX();
        double temp4 = entity.getNode().getBoundsInParent().getMinX();
        return entity.getNode().getBoundsInParent().intersects(obstacle.getNodeObject().getBoundsInParent()) && ((obstacle.getNodeObject().getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent().getMaxX()) ||
                (obstacle.getNodeObject().getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent().getMinX()));
    }

    private void obstacleLeftCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMaxX() > entity.getNode().getBoundsInParent().getMinX() &&
                obstacle.getNodeObject().getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent().getMaxX() &&
                xForceEntity < 0  && !checkCornersY(entity, obstacle)){
            System.out.println("left");
            xForceEntity -= MOVE_FORCE;
            entity.setCenterX(obstacle.getNodeObject().getBoundsInParent().getMaxX() + entity.getEntityWidth()/2);
        }

    }

    private void checkIfOnEdge(Entity entity, Obstacle obstacle){
        //if (entity.getNode().getBoundsInParent().getMinX() ){
            //check if a obstacle is close by with similar x and y. If there is then its fine, else it is about to fall. ceheck for both corners. min of entity and max of obstalce, vs max of osbtalc, vs min for entity
        }


    private void obstacleRightCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMinX() < entity.getNode().getBoundsInParent().getMaxX() &&
                obstacle.getNodeObject().getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent().getMinX() &&
                xForceEntity > 0 && !checkCornersY(entity, obstacle)){
            System.out.println("right");
            xForceEntity += MOVE_FORCE;
            entity.setCenterX(obstacle.getNodeObject().getBoundsInParent().getMinX() - entity.getEntityWidth()/2);
        }

    }

    private void obstacleBottomCollision(Entity entity, Obstacle obstacle) {
        if(obstacle.getNodeObject().getBoundsInParent().getMaxY() > entity.getNode().getBoundsInParent().getMinY() &&
                obstacle.getNodeObject().getBoundsInParent().getMaxY() < entity.getNode().getBoundsInParent().getMaxY() &&
                !checkCornersX(entity, obstacle)) {
            System.out.println("bottom");
            entity.setMaxY(obstacle.getNodeObject().getBoundsInParent().getMaxY() + entity.getEntityHeight());
        }
    }

    private void obstacleTopCollision(Entity entity, Obstacle obstacle) {
        System.out.println("topcol");
        obstacleTopCollisionMaybeWork(entity, obstacle);
        if(obstacle.getNodeObject().getBoundsInParent().getMinY() < entity.getNode().getBoundsInParent().getMaxY() &&
                obstacle.getNodeObject().getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent().getMinY() &&
                !checkCornersX(entity, obstacle)) {
            entity.setMaxY(obstacle.getNodeObject().getBoundsInParent().getMinY());
            yForceEntity += NEGATIVE_DIRECTION * GRAVITY;
            System.out.println("top");
            entity.setVelocityY(0);
            elapsedTime = dt;
            jump = false;
        }



    }
    private void obstacleTopCollisionMaybeWork(Entity entity, Obstacle obstacle) {
        System.out.println("topcol");
        checkCornerForFalling(entity, obstacle);

    }
    private void checkCornerForFalling(Entity entity, Obstacle obstacle) {
        if (objectAtCorner != true) {
            System.out.println(overEdge(entity, obstacle) + "   123");
            if (obstacle.getNodeObject().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {


                if (enemyDirection == -1 && obstacle.getNodeObject().getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent().getMinY() &&
                        !checkCornersX(entity, obstacle)) {
                    enemyDirection = 1;

                }
                else if (enemyDirection == 1 && obstacle.getNodeObject().getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent().getMinY() &&
                        areEqualDouble(obstacle.getNodeObject().getBoundsInParent().getMaxX(), entity.getNode().getBoundsInParent().getMaxX(), 1)){
                    enemyDirection = -1;
                }

                System.out.println(!cornerOverEdge(entity, obstacle) + "   123");
            }
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
        xForceEntity += MOVE_FORCE;
    }

    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    public boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }


}

