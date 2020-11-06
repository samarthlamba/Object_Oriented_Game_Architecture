
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
    private boolean leftOver = false;
    private boolean rightOver = false;
    // private double massCollideable;


//add 'is finished' to confirm if the game has been finished

    // check solidity aspect of obstacle by having boolean that is see through

    public Game(Collection<Collideable> obstacles, Collection<Moveables> entities, double timeElapsed) {
        this.obstacles = obstacles;
        this.entities = entities;
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
            if (entity.isJump() && entity.getTimeElapsedY() < .3) {
                entity.setTimeElapsedY(entity.getTimeElapsedY() + entity.getTimeElapsedX());
                System.out.println(entity.getTimeElapsedY());
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

    private void enemyDirection(Moveables entity){
        if(!leftOver && rightOver){
            entity.setVelocityX(Math.abs(entity.getVelocityX())*1);
        }
        if(!rightOver && leftOver){
            entity.setVelocityX(Math.abs(entity.getVelocityX())*-1);
        }

        //System.out.println("status " + leftOver + "     " +  rightOver);
        leftOver = false;
        rightOver = false;
    }

    private void simulateFall(Moveables entity, Collideable object){
        Rectangle simulate = new Rectangle(entity.getNode().getBoundsInParent().getMinX(), entity.getMaxY(), 0.1, 0.1);
        if (simulate.intersects(object.getNodeObject().getBoundsInParent())){
            leftOver = true;

        }
        simulate = new Rectangle(entity.getNode().getBoundsInParent().getMaxX(), entity.getMaxY(),0.1, 0.1);
        if (simulate.intersects(object.getNodeObject().getBoundsInParent())) {
            rightOver = true;
        }
        simulate = new Rectangle(entity.getNode().getBoundsInParent().getMaxX()+1, entity.getMaxY(),0.1, 0.1);

    }


    private void moveEnemy(Moveables entity) {
        enemyDirection(entity);
        if(entity.getId().equals("enemy")){
           // System.out.println("prev " + entity.getPreviousY() + " now " + entity.getMaxY());
            if(entity.getPreviousY() != entity.getMaxY()){
                entity.setMaxY(entity.getPreviousY());
                entity.setCenterX(entity.getPreviousX());
                entity.setVelocityX(entity.getVelocityX()*-1);
                // double c = entity.getMaxY();
            }

            // double c = entity.getMaxY();


           // System.out.println(entity.getVelocityX());


        }

    }

    private void collisionForce(Moveables entity) {
        for (Collideable obstacle : obstacles) {
            collisions(entity, obstacle);
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


 /*   private double getXForceMoveables(Moveables entity){
        double changeInX = entity.getPreviousX() - entity.getCenterX();
        return (changeInX - entity.getVelocityX() * elapsedTime) / (elapsedTime * elapsedTime);
    }
    private double getYForceMoveables(Moveables entity){
        //make previous array or attribuute of entity
        double changeInY = entity.getPreviousY() - entity.getMaxY();
        return (changeInY - entity.getVelocityY() * entity.getTimeElapsedY() ) / (entity.getTimeElapsedY()  * entity.getTimeElapsedY()) ;
    }*/

    private void updatePosition(Moveables entity) {
        entity.setPreviousX(entity.getCenterX());
        double c = entity.getMaxY();
        entity.setPreviousY(entity.getMaxY());
        entity.setMaxY(newYPosition(entity));
        entity.setCenterX(newXPosition(entity));
        if(!areEqualDouble(entity.getPreviousY(), entity.getMaxY(), 1)){
            ///////;
            double b = entity.getMaxY();
        }
    }

    private void gravityForce(Moveables entity) {
        entity.setYForce(entity.getYForce() + GRAVITY);
    }
    

    private void collisions(Moveables entity, Collideable obstacle) {
        if (obstacle.getNodeObject().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
            if(entity.getId() == "enemy"){
                simulateFall(entity, obstacle);
            }
            List<String> collisionSide = new ArrayList<>();
            Node object = obstacle.getNodeObject();
            Map<String, String> collisionTypes = obstacle.getCollisionRules();
            for(String side : collisionTypes.keySet()){
                try {
                    Method myObjMethod = this.getClass().getSuperclass().getDeclaredMethod(side + "Collision", Moveables.class, Node.class);
                    if((boolean) myObjMethod.invoke(this, entity, object)){
                        collisionSide.add(side);
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            for(String side : collisionSide){
                try {
                    Method myObjMethod = Class.forName("ooga.engine.obstacles.Collideable").getDeclaredMethod(side + "Collideable", Moveables.class);
                    myObjMethod.invoke(obstacle, entity);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                  e.printStackTrace();
                }
            }
        }
    }

    private boolean rightCollision (Moveables entity, Node object){
        return object.getBoundsInParent().getMinX() < entity.getNode().getBoundsInParent().getMaxX() &&
                object.getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent().getMinX() &&
                entity.getXForce() > 0 && !checkCornersY(entity, object);
    }

    private boolean leftCollision (Moveables entity, Node object){
        return object.getBoundsInParent().getMaxX() > entity.getNode().getBoundsInParent().getMinX() &&
                object.getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent().getMaxX() &&
                entity.getXForce() < 0 && !checkCornersY(entity, object);
    }

    private boolean bottomCollision (Moveables entity, Node object){
        return object.getBoundsInParent().getMaxY() > entity.getNode().getBoundsInParent().getMinY() &&
                object.getBoundsInParent().getMaxY() < entity.getNode().getBoundsInParent().getMaxY() &&
                !checkCornersX(entity, object);
    }

    private boolean topCollision (Moveables entity, Node object){
        return object.getBoundsInParent().getMinY() < entity.getNode().getBoundsInParent().getMaxY() &&
                object.getBoundsInParent().getMinY() > entity.getNode().getBoundsInParent().getMinY() &&
                !checkCornersX(entity, object);
    }


    private boolean checkCornersY(Moveables entity, Node object) {
        return areEqualDouble(object.getBoundsInParent().getMinY(), entity.getNode().getBoundsInParent().getMaxY(), 1) ||
                areEqualDouble(object.getBoundsInParent().getMaxY(), entity.getNode().getBoundsInParent().getMinY(), 1);
    }

    private boolean checkCornersX (Moveables entity, Node object){
            return areEqualDouble(object.getBoundsInParent().getMaxX(), entity.getNode().getBoundsInParent().getMinX(), 1) ||
                    areEqualDouble(object.getBoundsInParent().getMinX(), entity.getNode().getBoundsInParent().getMaxX(), 1);
    }


    private void checkIfOnEdge(Moveables entity, Collideable object){
        //if (entity.getNode().getBoundsInParent().getMinX() ){
            //check if a obstacle is close by with similar x and y. If there is then its fine, else it is about to fall. ceheck for both corners. min of entity and max of obstalce, vs max of osbtalc, vs min for entity
        }


    private boolean cornerOverEdge(Moveables entity, Node object) {
        return (entity.getNode().getBoundsInParent().getMaxX() > object.getBoundsInParent().getMaxX() &&
                entity.getNode().getBoundsInParent().getMinX() > object.getBoundsInParent().getMinX());
    }

    private boolean overEdge(Moveables entity, Node object){
        double temp = object.getBoundsInParent().getMaxX();
        double temp2 =  entity.getNode().getBoundsInParent().getMaxX();
        double temp3 = object.getBoundsInParent().getMinX();
        double temp4 = entity.getNode().getBoundsInParent().getMinX();
        return entity.getNode().getBoundsInParent().intersects(object.getBoundsInParent()) && ((object.getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent().getMaxX()) ||
                (object.getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent().getMinX()));
    }

    private void obstacleTopCollisionMaybeWork(Moveables entity, Node object) {
       // System.out.println("topcol");

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
            System.out.println("up");
            entity.setVelocityY(entity.getJumpMax());
            entity.setMaxY(entity.getMaxY() - 5);
            System.out.println(entity.getMaxY());
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


