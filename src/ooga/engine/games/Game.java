
package ooga.engine.games;

import java.util.stream.Collectors;
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
    public static final double NEGATIVE_DIRECTION = -1;
    public static final double NORMAL_FORCE_OFFSET = 100;
    public static final double JUMP_VELOCITY_INCREMENT = 100;
    public static final double JUMP_INITIAL_OFFSET = 2;
    public static final double ZERO_FORCE = 0;
    public static final double POWER = 10;
    private final double gravity;
    private final double moveForce;
    private final Player player;
    private int jumpMax;
    protected Collection<Unmovable> obstacles;
    protected Collection<Movable> entities;
    private double dt;
    protected Collisions handleCollisions;
    protected int totalPoints = 0;
    protected UpdateObjectsOnScreen viewable = new GamePlayScreen();
    protected Collection<MovableBounds> entitiesToAdd = new ArrayList<>();
    protected Collection<MovableBounds> entitiesToRemove = new ArrayList<>();



//add 'is finished' to confirm if the game has been finished

    // check solidity aspect of obstacle by having boolean that is see through


    public Game(Player player,Collection<Unmovable> obstacles, Collection<Movable> entities, double timeElapsed, GameBean bean) {
        this.obstacles = obstacles;
        this.entities = entities;
        this.gravity = bean.getGravity();
        this.moveForce = bean.getMoveForce();
        this.jumpMax = bean.getJumpMax();
        this.player = player;
        handleCollisions = new Collisions();
        this.dt = timeElapsed;
        normalForce(entities, obstacles);
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

    private void normalForce(Collection<Movable> entities, Collection<Unmovable> obstacles){
        for(Movable entity : entities){
            entity.setNormalForce(gravity - NORMAL_FORCE_OFFSET);
        }
        for(Unmovable obstacle : obstacles){
            obstacle.setNormalForce(gravity - NORMAL_FORCE_OFFSET);
        }
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
        for (Movable entity : entities) {
            moveMovable(entity);
        }
        viewable.remove(entitiesToRemove);
        entities.removeAll(entitiesToRemove);
        entitiesToRemove.clear();
        viewable.spawn(entitiesToAdd);
        entitiesToAdd.clear();
    }

    protected void moveMovable(Movable entity) {
        if (entity.isJump()) {
            entity.setVelocityY(entity.getVelocityY() + JUMP_VELOCITY_INCREMENT);
        }
        gravityForce(entity);
        obstacleCollision(entity);
        entityCollision(entity);
        moveEnemy(entity);
        updatePosition(entity);
        entity.setYForce(ZERO_FORCE);
        entity.setXForce(ZERO_FORCE);
        removeEntity(entity);
    }

    protected void removeEntity(Movable entity) {
        if(!entity.getStatusAlive()){
            entitiesToRemove.add(entity);
            setPoints(entity);
        }
    }

    public abstract void setPoints(Movable entity);

  /*  protected void removeMovable() {
        entities.removeIf(e -> !e.getStatusAlive());
    }*/


    protected void moveEnemy(Movable entity) {
        if (entity.getId().equals("enemy")) {
            if (entity.getPreviousY() != entity.getMaxY()) {
                entity.setMaxY(entity.getPreviousY());
                entity.setCenterX(entity.getPreviousX());
                entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
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


  /*  protected boolean checkCornersMovableX(Movable player, Movable entity) {
        return areEqualDouble(entity.getNode().getBoundsInParent().getMaxX(), player.getNode().getBoundsInParent().getMinX(), 1) ||
                areEqualDouble(entity.getNode().getBoundsInParent().getMinX(), player.getNode().getBoundsInParent().getMaxX(), 1);
    }*/

    private double newYPosition(Movable entity) {
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
            entity.setMaxY(entity.getMaxY() - JUMP_INITIAL_OFFSET);
        }
    }


    public void LEFT(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setXForce(NEGATIVE_DIRECTION * moveForce);
        entity.setFacing(false);
    }


    public void RIGHT(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setXForce(moveForce);
        entity.setFacing(true);
    }

    public void playerAction(){}

    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    protected boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(POWER, -precision);
    }

    public void setDisplay(UpdateObjectsOnScreen gamePlayScreen) {
        viewable = gamePlayScreen;
    }

}


