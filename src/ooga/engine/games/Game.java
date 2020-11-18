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
import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract class Game is part of our game inheritance hierarchy that allows concert implementations
 * of specific games to be made through subclasses
 */
public abstract class Game implements GamePlay {
    public static final double NEGATIVE_DIRECTION = -1;
    public static final double NORMAL_FORCE_OFFSET = 100;
    public static final double JUMP_VELOCITY_INCREMENT = 100;
    public static final double JUMP_INITIAL_OFFSET = 2;
    public static final double ZERO_FORCE = 0;
    private final static int KILL_PLAYER = 0;
    public static final double POWER = 10;
    private final double gravity;
    private final double moveForce;
    private final Player player;
    private final int jumpMax;
    protected Collection<Unmovable> obstacles;
    protected Collection<Movable> entities;
    private final double dt;
    protected Collisions handleCollisions;
    protected int totalPoints = 0;
    protected UpdateObjectsOnScreen viewable = new GamePlayScreen();
    protected Collection<MovableBounds> entitiesToAdd = new ArrayList<>();
    protected Collection<MovableBounds> entitiesToRemove = new ArrayList<>();
    protected int specialActionDelayFlag = 0;
    private double lowestPoint = 0;
    private final int fallDeathOffset;

    /**
     * Game constructor initializes variables through parameter input and constant values retrieved from the beans
     * @param player main player entity
     * @param obstacles collection of Unmovable obstacles
     * @param entities collection of Movable entities
     * @param timeElapsed 1 / frame rate
     * @param bean bean constants
     */
    public Game(Player player,Collection<Unmovable> obstacles, Collection<Movable> entities, double timeElapsed, GameBean bean) {
        this.obstacles = obstacles;
        this.entities = entities;
        this.gravity = bean.getGravity();
        this.moveForce = bean.getMoveForce();
        this.jumpMax = bean.getJumpMax();
        this.fallDeathOffset = bean.getFallDeathOffset();
        this.player = player;
        handleCollisions = new Collisions();
        this.dt = timeElapsed;
        normalForce(entities, obstacles);
        findSceneLowestY();
    }

    /**
     * Check if player flag hasWon is set to true
     * Used in driver to check if game won/which splash screen to display
     * @return boolean true or false
     */
    public boolean isWon(){
        return player.hasWon();
    }

    /**
     * Check if player is not alive (alive status is false)
     * Used in driver to check if game lost/which splash screen to display
     * @return boolean true or false
     */
    public boolean isLost() {
        return !player.getStatusAlive();
    }

    /**
     * Passes nodes of the (unmoving/unchanging) obstacles that should appear on the screen
     * to the Display
     * @return Collection of nodes
     */
    public Collection<Node> getBackground() {
        Collection<Node> nodeObstacles = new ArrayList<>();
        for(Unmovable obstacle : obstacles){
            nodeObstacles.add(obstacle.getNode());
        }
        return nodeObstacles;
    }

    private void findSceneLowestY(){
        for(Unmovable obstacle : obstacles){
            double yPosition = obstacle.getNode().getBoundsInParent().getMaxY();
            if(yPosition > lowestPoint){
                lowestPoint = yPosition;
            }
        }
    }

    protected void fallingDeath() {
        Movable player = getActivePlayer();
        if (player.getMaxY() > lowestPoint + fallDeathOffset) {
            player.setHitpoints(KILL_PLAYER);
        }
    }

    /**
     * Run every step to update all objects on screen
     */
    public void updateLevel() {
        updateMovable();
    }

    /**
     * Return point values (game depended based on goal)
     * @return integer total points
     */
    public int getPoints(){
        return totalPoints;
    }

    /**
     * Pass collection of MovableBounds that are all objects whose states can be changed
     * from what was initially loaded in. This is passed to display for initial loading in of values.
     * @return Collection of MovableBounds
     */
    public Collection<? extends MovableBounds> getEntities() {
        return entities;
    }

    protected void normalForce(Collection<Movable> entities, Collection<Unmovable> obstacles){
        for(Movable entity : entities){
            entity.setNormalForce(gravity - NORMAL_FORCE_OFFSET);
        }
        for(Unmovable obstacle : obstacles){
            obstacle.setNormalForce(gravity - NORMAL_FORCE_OFFSET);
        }
    }

    protected void updateMovable() {
        normalForce(entities, obstacles);
        for (Movable entity : entities) {
            moveMovable(entity);
        }
        fallingDeath();
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

        if(specialActionDelayFlag > 100 && entity.getId().equals("player")){
            entity.setSpecialAction(false);
        }
        specialActionDelayFlag++;
        gravityForce(entity);
        obstacleCollision(entity);
        entityCollision(entity);
        autoEntityMovement(entity);
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

    protected abstract void setPoints(Movable entity);

    protected void autoEntityMovement(Movable entity) {
        if (entity.getHorizontalMovement()) {
            if (entity.getPreviousY() != entity.getMaxY()) {
                entity.setMaxY(entity.getPreviousY());
                entity.setCenterX(entity.getPreviousX());
                entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
            }
        }
    }

    private void obstacleCollision(Movable entity) {
        for (Unmovable obstacle : obstacles) {
            Collideable object = obstacle.getCollideable();
            collisions(entity, object);
        }
    }

    private void entityCollision(Movable entity){
        for(Movable e : entities){
            if(entity != e){
                collisions(entity, (Collideable) e);
            }
        }
    }

    private double newYPosition(Movable entity) {
         return entity.getMaxY() + entity.getVelocityY() * dt + entity.getYForce() * dt * dt;
    }

    private double newXPosition(Movable entity) {
        return entity.getCenterX() + entity.getVelocityX() * dt + entity.getXForce() * dt * dt;
    }

    private void updatePosition(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
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


    private void UP(Movable entity) {
        if(!entity.isJump()) {
            entity.setJump(true);
            entity.setVelocityY(jumpMax);
            entity.setMaxY(entity.getMaxY() - JUMP_INITIAL_OFFSET);
        }
    }


    private void LEFT(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setXForce(NEGATIVE_DIRECTION * moveForce);
        entity.setFacing(false);
    }


    private void RIGHT(Movable entity) {
        entity.setPreviousX(entity.getCenterX());
        entity.setXForce(moveForce);
        entity.setFacing(true);
    }

    protected void playerAction(){}

    //https://stackoverflow.com/questions/356807/java-double-comparison-epsilon
    protected boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(POWER, -precision);
    }

    public void setDisplay(UpdateObjectsOnScreen gamePlayScreen) {
        viewable = gamePlayScreen;
    }

}


