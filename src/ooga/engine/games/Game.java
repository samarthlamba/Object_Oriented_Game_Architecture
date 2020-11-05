
package ooga.engine.games;

import javafx.scene.Node;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Moveables;
import ooga.engine.obstacles.Collideable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.round;

public abstract class Game implements GamePlay {
    public static final double GRAVITY = 9.8;
    public static final double NEGATIVE_DIRECTION = -1;
    public static final double NO_INITIAL_VELOCITY = 0;
    public static final double NO_FORCE = 0;
    public static final double MOVE_FORCE = 10;
    private Collection<Collideable> obstacles;
    private Collection<Moveables> entities;
    private double dt;
    private double jumpInitialVelocity;
    private double initialVelocityY;
    private double initialVelocityX = 0;
    private boolean jump = false;
    // private int screenHeight;
    private double jumpMaxHeight = 10;
    private double xForceMoveables = 0;
    private double yForceMoveables = 0;
    private double massMoveables;
    private double elapsedTime;
    private double moveVelocity = 10;
    private boolean objectAtCorner;
    private int enemyDirection =-1;
    // private double massCollideable;


//add 'is finished' to confirm if the game has been finished

    // check solidity aspect of obstacle by having boolean that is see through

    public Game(Collection<Collideable> obstacles, Collection<Moveables> entities, double timeElapsed) {
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

    public Collection<Collideable> getBackground() {
        return obstacles;
    }

    @Override
    public void updateLevel() {
        System.out.println("stepped123");
        updateMoveables();
    }

    public Collection<Moveables> getEntities() {
        updateMoveables();
        return entities;
    }

    public void updateMoveables() {
        System.out.println("stepped12324");
        if (jump) {
            elapsedTime += dt;
        }
        for (Moveables entity : entities) {
            gravityForce();
            collisionForce(entity);
            moveEnemy(entity);
            updatePosition(entity);
            System.out.println("force" + yForceMoveables);
            xForceMoveables = 0;
            yForceMoveables = 0;
        }
    }

    private void moveEnemy(Moveables entity) {
        if(entity.getId().equals("enemy")){
            System.out.println("prev " + entity.getPreviousY() + " now " + entity.getMaxY());
            if(entity.getPreviousY() != entity.getMaxY()){
                entity.setMaxY(entity.getPreviousY());
                entity.setCenterX(entity.getPreviousX());
                entity.setVelocityX(entity.getVelocityX()*-1);
               // double c = entity.getMaxY();
            }

            System.out.println(entity.getVelocityX());


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
        return entity.getMaxY() + entity.getVelocityY() * elapsedTime + yForceMoveables * elapsedTime * elapsedTime;
    }

    private double newXPosition(Moveables entity) {
        return entity.getCenterX() + entity.getVelocityX() * elapsedTime + xForceMoveables * elapsedTime * elapsedTime;
    }


 /*   private double getXForceMoveables(Moveables entity){
        double changeInX = entity.getPreviousX() - entity.getCenterX();
        return (changeInX - entity.getVelocityX() * elapsedTime) / (elapsedTime * elapsedTime);
    }*/
    private double getYForceMoveables(Moveables entity){
        //make previous array or attribuute of entity
        double changeInY = entity.getPreviousY() - entity.getMaxY();
        return (changeInY - entity.getVelocityY() * elapsedTime) / (elapsedTime * elapsedTime);
    }

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

    private void gravityForce() {
        yForceMoveables += GRAVITY;
    }
    

    private void collisions(Moveables entity, Collideable obstacle) {
        if (obstacle.getNodeObject().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
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
                    Method myObjMethod = this.getClass().getSuperclass().getDeclaredMethod(side + collisionTypes.get(side), Moveables.class, Node.class);
                    myObjMethod.invoke(this, entity, object);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean rightCollision (Moveables entity, Node object){
        return object.getBoundsInParent().getMinX() < entity.getNode().getBoundsInParent().getMaxX() &&
                object.getBoundsInParent().getMinX() > entity.getNode().getBoundsInParent().getMinX() &&
                xForceMoveables > 0 && !checkCornersY(entity, object);
    }

    private boolean leftCollision (Moveables entity, Node object){
        return object.getBoundsInParent().getMaxX() > entity.getNode().getBoundsInParent().getMinX() &&
                object.getBoundsInParent().getMaxX() < entity.getNode().getBoundsInParent().getMaxX() &&
                xForceMoveables < 0 && !checkCornersY(entity, object);
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

    private void leftStandard(Moveables entity, Node object) {
        System.out.println("left");
        xForceMoveables -= MOVE_FORCE;
        entity.setCenterX(object.getBoundsInParent().getMaxX() + entity.getEntityWidth() / 2);
    }


    private void rightStandard(Moveables entity, Node object) {
        System.out.println("right");
        xForceMoveables += MOVE_FORCE;
        entity.setCenterX(object.getBoundsInParent().getMinX() - entity.getEntityWidth() / 2);
    }

    private void bottomStandard(Moveables entity, Node object) {
        System.out.println("bottom");
        entity.setMaxY(object.getBoundsInParent().getMaxY() + entity.getEntityHeight());
    }

    private void topStandard(Moveables entity, Node object) {
        entity.setMaxY(object.getBoundsInParent().getMinY());
        yForceMoveables += NEGATIVE_DIRECTION * GRAVITY;
        System.out.println("top");
        entity.setVelocityY(0);
        elapsedTime = dt;
        jump = false;
    }

    private void rightNoCollision(Moveables entity, Node object){}
    private void leftNoCollision(Moveables entity, Node object){}
    private void topNoCollision(Moveables entity, Node object){}
    private void bottonNoCollision(Moveables entity, Node object){}
    

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
        System.out.println("topcol");

    }


        public void UP (Moveables entity){
            jump = true;
            entity.setVelocityY(entity.getJumpMax());
            entity.update();
        }


        public void LEFT (Moveables entity){
            entity.setPreviousX(entity.getCenterX());
            xForceMoveables -= MOVE_FORCE;

        }


        public void RIGHT (Moveables entity){
            entity.setPreviousX(entity.getCenterX());
            xForceMoveables += MOVE_FORCE;
        }

        //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
        public boolean areEqualDouble ( double a, double b, int precision){
            return Math.abs(a - b) <= Math.pow(10, -precision);
        }

}


