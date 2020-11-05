
package ooga.engine.games;

import javafx.scene.Node;
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
    private double jumpMaxHeight = 10;
    private double xForceEntity = 0;
    private double yForceEntity = 0;
    private double massEntity;
    private double elapsedTime;
    private double moveVelocity = 10;
    // private double massObstacle;


//add 'is finished' to confirm if the game has been finished

    // check solidity aspect of obstacle by having boolean that is see through

    public Game(Collection<Obstacle> obstacles, Collection<Entity> entities, double timeElapsed) {
        this.obstacles = obstacles;
        this.entities = entities;
        this.dt = timeElapsed;
        elapsedTime = timeElapsed;
        jumpInitialVelocity = calculateJumpVelocity();
    }

    public boolean hasFinished() {
        return true;
    }

    private double calculateJumpVelocity() {

        return NEGATIVE_DIRECTION * Math.sqrt((jumpMaxHeight * GRAVITY));
    }

    public Collection<Obstacle> getBackground() {
        return obstacles;
    }

    @Override
    public void updateLevel() {
        System.out.println("stepped123");
        updateEntity();
    }

    public Collection<Entity> getEntities() {
        updateEntity();
        return entities;
    }

    private double getFinalVelocity() {
        //v_final = v_initial + acceleration*time;
        return initialVelocityY - GRAVITY * elapsedTime;
    }

    public void updateEntity() {
        System.out.println("stepped12324");
        if (jump) {
            elapsedTime += dt;
        }
        for (Entity entity : entities) {
            updatePosition(entity);
            gravityForce();
            collisionForce(entity);
            moveEnemy(entity);
            updatePosition(entity);
            System.out.println("force" + yForceEntity);
            xForceEntity = 0;
            yForceEntity = 0;
        }
    }

    private void moveEnemy(Entity entity) {
        if (entity.getId().equals("enemy")) {
            System.out.println(yForceEntity);
            if (yForceEntity == 0) {
                entity.setVelocityX(moveVelocity);
                entity.update();

            } else {
                moveVelocity *= -1;
                entity.setVelocityX(moveVelocity);
                entity.update();
            }
        }
    }

    private void collisionForce(Entity entity) {
        for (Obstacle obstacle : obstacles) {
            collisions(entity, obstacle.getNodeObject());
        }
        playerEnemyCollision(entity);
    }


    private void playerEnemyCollision(Entity entity) {
        if (entity.getId().equals("player")) {
            for (Entity e : entities) {
                if (entityCollision(entity, e)) {
                    if (entityTopCollision(entity, e)) {
                        e.setHitpoints(e.getHitpoints() - 1);
                    } else {
                        entity.setHitpoints(entity.getHitpoints() - 1);
                    }
                }
            }
        }
    }

    private boolean entityCollision(Entity player, Entity entity) {
        if (entity.getId().equals("enemy")) {
            return player.getNode().getBoundsInParent().intersects(entity.getNode().getBoundsInParent());
        }
        return true;
    }

    private boolean entityTopCollision(Entity player, Entity entity) {
        return entity.getNode().getBoundsInParent().getMinY() < player.getNode().getBoundsInParent().getMaxY() &&
                entity.getNode().getBoundsInParent().getMinY() > player.getNode().getBoundsInParent().getMinY() &&
                !checkCornersEntityX(player, entity);
    }

    private boolean checkCornersEntityX(Entity player, Entity entity) {
        return areEqualDouble(entity.getNode().getBoundsInParent().getMaxX(), player.getNode().getBoundsInParent().getMinX(), 1) ||
                areEqualDouble(entity.getNode().getBoundsInParent().getMinX(), player.getNode().getBoundsInParent().getMaxX(), 1);
    }

    private double newYPosition(Entity entity) {
        return entity.getMaxY() + entity.getVelocityY() * elapsedTime + yForceEntity * elapsedTime * elapsedTime;
    }

    private double newXPosition(Entity entity) {
        return entity.getCenterX() + entity.getVelocityX() * elapsedTime + xForceEntity * elapsedTime * elapsedTime;
    }


 /*   private double getXForceEntity(Entity entity){
        double changeInX = entity.getPreviousX() - entity.getCenterX();
        return (changeInX - entity.getVelocityX() * elapsedTime) / (elapsedTime * elapsedTime);
    }*/

    private double getYForceEntity(Entity entity) {
        //make previous array or attribuute of entity
        double changeInY = entity.getPreviousY() - entity.getMaxY();
        return (changeInY - entity.getVelocityY() * elapsedTime) / (elapsedTime * elapsedTime);
    }

    private void updatePosition(Entity entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setPreviousY(entity.getMaxY());
        entity.setMaxY(newYPosition(entity));
        entity.setCenterX(newXPosition(entity));
    }

    private void gravityForce() {
        yForceEntity += GRAVITY;
    }

    private void collisions(Entity entity, Node object) {
        if (object.getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
            obstacleTopCollision(entity, object);
            obstacleBottomCollision(entity, object);
            obstacleRightCollision(entity, object);
            obstacleLeftCollision(entity, object);
        }
    }


    private boolean checkCornersY(Entity entity, Node object) {
        return areEqualDouble(object.getBoundsInParent().getMinY(), entity.getNode().getBoundsInParent().getMaxY(), 1) ||
                areEqualDouble(object.getBoundsInParent().getMaxY(), entity.getNode().getBoundsInParent().getMinY(), 1);
    }

    private boolean checkCornersX (Entity entity, Node object){
            return areEqualDouble(object.getBoundsInParent().getMaxX(), entity.getNode().getBoundsInParent().getMinX(), 1) ||
                    areEqualDouble(object.getBoundsInParent().getMinX(), entity.getNode().getBoundsInParent().getMaxX(), 1);
    }

    private void obstacleLeftCollision (Entity entity, Node object){
        if (leftCollision(entity, object)) {
                System.out.println("left");
                xForceEntity -= MOVE_FORCE;
                entity.setCenterX(object.getBoundsInParent().getMaxX() + entity.getEntityWidth() / 2);
        }
    }

    private boolean leftCollision (Entity entity, Node object){
            return object.getBoundsInParent().getMaxX() > entity.getNode().getBoundsInParent().getMinX() &&
                    object.getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent().getMaxX() &&
                    xForceEntity < 0 && !checkCornersY(entity, object);
    }

    private void obstacleRightCollision (Entity entity, Node object){
            if (rightCollision(entity, object)) {
                System.out.println("right");
                xForceEntity += MOVE_FORCE;
                entity.setCenterX(object.getBoundsInParent().getMinX() - entity.getEntityWidth() / 2);
            }

    }

    private boolean rightCollision (Entity entity, Node object){
            return object.getBoundsInParent().getMinX() < entity.getNode().getBoundsInParent().getMaxX() &&
                    object.getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent().getMinX() &&
                    xForceEntity > 0 && !checkCornersY(entity, object);
    }

    private void obstacleBottomCollision (Entity entity, Node object){
        if (bottomCollision(entity, object)) {
            System.out.println("bottom");
            entity.setMaxY(object.getBoundsInParent().getMaxY() + entity.getEntityHeight());
        }
    }

        private boolean bottomCollision (Entity entity, Node object){
            return object.getBoundsInParent().getMaxY() > entity.getNode().getBoundsInParent().getMinY() &&
                    object.getBoundsInParent().getMaxY() < entity.getNode().getBoundsInParent().getMaxY() &&
                    !checkCornersX(entity, object);
        }

        private void obstacleTopCollision (Entity entity, Node object){
            if (topCollision(entity, object)) {
                entity.setMaxY(object.getBoundsInParent().getMinY());
                yForceEntity += NEGATIVE_DIRECTION * GRAVITY;
                System.out.println("top");
                entity.setVelocityY(0);
                elapsedTime = dt;
                jump = false;
            }
        }

        private boolean topCollision (Entity entity, Node object){
            return object.getBoundsInParent().getMinY() < entity.getNode().getBoundsInParent().getMaxY() &&
                    object.getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent().getMinY() &&
                    !checkCornersX(entity, object);
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
        public void UP (Entity entity){
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

        public void LEFT (Entity entity){
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

        public void RIGHT (Entity entity){
            entity.setPreviousX(entity.getCenterX());
            xForceEntity += MOVE_FORCE;
        }

        //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
        public boolean areEqualDouble ( double a, double b, int precision){
            return Math.abs(a - b) <= Math.pow(10, -precision);
        }


}


