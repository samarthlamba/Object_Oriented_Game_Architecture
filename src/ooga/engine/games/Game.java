
package ooga.engine.games;

import javafx.scene.Node;

import ooga.engine.entities.player.Player;
import ooga.engine.games.beans.GameBean;

import ooga.engine.entities.Entity;
import ooga.engine.entities.Movable;
import ooga.engine.entities.MovableBounds;
import ooga.engine.obstacles.Unmovable;
import ooga.view.GamePlayScreen;
import ooga.view.UpdateObjectsOnScreen;

import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.round;

public abstract class Game implements GamePlay {
    //public static final double GRAVITY = 800;
    //We could probably change these to ENUMS
    public static final double NEGATIVE_DIRECTION = -1;
    private final double gravity;
    private final double moveForce;
    public static final double MOVE_FORCE = 50000; //TODO change to 10
    private final Player player;
    private int jumpMax;
    protected Collection<Unmovable> obstacles;
    protected Collection<Movable> entities;
    private double dt;
    private double initialVelocityX = 0;
    private boolean jump = false;
    private double jumpMaxHeight = 10;
    private double massMovable;
    private double moveVelocity = 10;
    private boolean objectAtCorner;
    private int enemyDirection = -1;
    private Set<String> collisionTypes = Set.of("right", "left", "top", "bottom");
    protected Collisions handleCollisions;
    protected int totalPoints = 0;
    protected UpdateObjectsOnScreen viewable = new GamePlayScreen();
    protected Collection<MovableBounds> entitiesToAdd = new ArrayList<>();
    protected Collection<MovableBounds> entitiesToRemove = new ArrayList<>();



//add 'is finished' to confirm if the game has been finished

    // check solidity aspect of obstacle by having boolean that is see through


    public Game(Player player,Collection<Unmovable> obstacles, Collection<Movable> entities, double timeElapsed, GameBean bean) {
        this.gravity = bean.getGravity();
        this.moveForce = bean.getMoveForce();
        this.obstacles = obstacles;
        this.entities = entities;
        this.jumpMax = bean.getJumpMax();
        this.player = player;
        handleCollisions = new Collisions();
      /*  for (Movable entity : entities) {
            entity.setTimeElapsedY(timeElapsed);
            entity.setTimeElapsedX(timeElapsed);
        }*/
        this.dt = timeElapsed;
    }

    public boolean isWon(){
        return player.hasWon();
    }

    public boolean isLost() {
        return !player.getStatusAlive();
    }

    public Collection<Node> getBackground() {
        Collection<Node> nodeObstacles = new ArrayList<>();
        for(Unmovable obstacle : obstacles){
            nodeObstacles.add(obstacle.getNode());
        }
        return nodeObstacles;
    }


    public void updateLevel() {
        updateMovable();
    }

    public int getPoints(){
        return totalPoints;
    }

    public Collection<? extends MovableBounds> getEntities() {
        return entities;
    }

    protected void updateMovable() {
        // System.out.println("stepped12324");
        for (Movable entity : entities) {
            moveMovable(entity);
        }
        viewable.remove(entitiesToRemove);
        entities.removeAll(entitiesToRemove);
        entitiesToRemove.clear();
        viewable.spawn(entitiesToAdd);
        entitiesToAdd.clear();
        //removeMovable();
    }

    protected void moveMovable(Movable entity) {
      /*  if (entity.getTimeElapsedY() < .35) {
            entity.setTimeElapsedY(entity.getTimeElapsedY() + entity.getTimeElapsedX());
        }*/
        if (entity.isJump()) {
            entity.setVelocityY(entity.getVelocityY()+100);
           // entity.setJump(true);
        }
        gravityForce(entity);
        obstacleCollision(entity);
        entityCollision(entity);
        moveEnemy(entity);
        updatePosition(entity);
        entity.setYForce(0);
        entity.setXForce(0);
        removeEntity(entity);
    }

    protected void removeEntity(Movable entity) {
        if(!entity.getStatusAlive()){
            entitiesToRemove.add(entity);
            setPoints(entity);
        }
    }

    public void setPoints(Movable entity){}

    protected void removeMovable() {
        entities.removeIf(e -> !e.getStatusAlive());
    }


    protected void moveEnemy(Movable entity) {
        if (entity.getId().equals("enemy")) {
            // System.out.println("prev " + entity.getPreviousY() + " now " + entity.getMaxY());
            if (entity.getPreviousY() != entity.getMaxY()) {
                entity.setMaxY(entity.getPreviousY());
                entity.setCenterX(entity.getPreviousX());
                entity.setVelocityX(entity.getVelocityX() * -1);
                // double c = entity.getMaxY();
            }
        }

    }

    private void obstacleCollision(Movable entity) {
        for (Unmovable obstacle : obstacles) {
            Collideable object = obstacle.getCollideable();
            collisions(entity, object);
        }
       /* if(!entity.getId().equals("player")){
            collisions(findMainPlayer(), entity);
        }*/
    }

    private void entityCollision(Movable entity){
        for(Movable e : entities){
            if(entity != e){
                collisions(entity, (Collideable) e);
            }
        }
    }


    protected boolean checkCornersMovableX(Movable player, Movable entity) {
        return areEqualDouble(entity.getNode().getBoundsInParent().getMaxX(), player.getNode().getBoundsInParent().getMinX(), 1) ||
                areEqualDouble(entity.getNode().getBoundsInParent().getMinX(), player.getNode().getBoundsInParent().getMaxX(), 1);
    }

    private double newYPosition(Movable entity) {
        double change = entity.getMaxY() + entity.getVelocityY() * dt + entity.getYForce() * dt* dt;
         return entity.getMaxY() + entity.getVelocityY() * dt + entity.getYForce() * dt * dt;
    }

    private double newXPosition(Movable entity) {
        return entity.getCenterX() + entity.getVelocityX() * dt + entity.getXForce() * dt * dt;
    }

    private void updatePosition(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
        double c = entity.getMaxY();
        entity.setPreviousY(entity.getMaxY());
        entity.setMaxY(newYPosition(entity));
        entity.setCenterX(newXPosition(entity));
    }

    private void gravityForce(Movable entity) {
        if(entity.hasGravity()) {
            entity.setYForce(entity.getYForce() + gravity);
        }
    }

    protected void collisions(Movable entity, Collideable object) {
        if (object.getNode().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
            handleCollisions.collisions((Entity) entity, object);
        }
    }


    public Movable getActivePlayer() {
        return player;
    }

    public void moveRight() {
        RIGHT(player);
    }

    public void moveLeft() {
        LEFT(player);
    }

    public void moveUp() {
        UP(player);
    }


    public void UP(Movable entity) {
        if(!entity.isJump()) {
            entity.setJump(true);
            entity.setVelocityY(jumpMax);
            entity.setMaxY(entity.getMaxY() - 2);
        }
    }


    public void LEFT(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setXForce(-MOVE_FORCE);
        entity.setFacing(false);
    }


    public void RIGHT(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setXForce(MOVE_FORCE);
        entity.setFacing(true);
    }

    public void playerAction(){}


    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    protected boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }

    public void setDisplay(UpdateObjectsOnScreen gamePlayScreen) {
        viewable = gamePlayScreen;
    }

}


