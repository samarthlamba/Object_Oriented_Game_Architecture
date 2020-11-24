package ooga.engine.entities;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.games.Collideable;
import ooga.engine.games.GamePropertyFileReader;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Abstract class handles states of entity, position, and collisions
 */
public abstract class Entity extends Rectangle implements Collideable, Movable {
    private static final double NEGATIVE_DIRECTION = -1;
    public static final int HEALTH_PENALTY = -1;
    public static final int COLLISION_OFFSET = 1;
    public static final int JUMP_INITIAL_OFFSET = 2;
    public static final int APPLY_Y_VELOCITY = -2600;
    public static final int SPINNING_VELOCITY = 700;
    public static final int DELAY_BUFFER = 4;
    private final int entityWidth;
    private final int entityHeight;
    private int currentHitpoints = 3;
    private final Node nodeObject;
    private double speed = 0;
    private double previousX;
    private double previousY;
    private double jumpCapacity = 0;
    private double xForce = 0;
    private double yForce = 0;
    boolean status_Alive = true;
    private boolean facing = true;
    private boolean jump = false;
    private double normalForce = 0;
    private boolean moving = false;
    private int delay = 0;
    private boolean percolate = false;
    private boolean source = false;
    private boolean shoots = false;
    private boolean makesCoins = false;
    private boolean specialAction = false;
    private boolean shrunk = false;

    public Entity(int objectWidth,int objectHeight,  double initialX, double initialY) {
        this.entityWidth = objectWidth;
        this.entityHeight = objectHeight;
        nodeObject = new Rectangle(initialX, initialY, objectWidth, objectHeight);
        this.previousX = initialX + objectWidth / 2;
        this.previousY = initialY + objectHeight;
        setX(initialX);
        setY(initialY);
        setWidth(objectWidth);
        setHeight(objectHeight);
        this.setCenterX(initialX + objectWidth / 2);
        this.setMaxY(initialY + objectHeight);
    }

    /**
     * Returns node object of Entity
     * @return Node
     */
    public Node getNode() {
        return this;
    }

    /**
     * Gets status if alive
     * @return returns true if alive, false if not alive
     */
    public boolean getStatusAlive(){
        return this.status_Alive;
    }

    /**
     * hasWon is default false since only player should be able to set to true
     * @return false (can be overridden)
     */
    public boolean hasWon(){
        return false;
    }

    /**
     * setWon to boolean value finished
     * @param finished boolean
     */
    public void setWon(boolean finished){
    }

    /**
     * returns the x velocity of entity
     * @return speed double
     */
    public double getVelocityX(){
        return speed;
    }

    /**
     * Returns the y velocity of entity
     * @return jumpCapacity double
     */
    public double getVelocityY(){
        return jumpCapacity;
    }

    /**
     * Returns number of hitpoints left
     * @return hitpoints
     */
    public int getHealth(){
        return getHitpoints();
    }

    /**
     * Set x velocity of entity
     * @param x speed
     */
    public void setVelocityX(double x){
        this.speed = x;
    }

    /**
     * set y velocity of entity
     * @param y speed
     */
    public void setVelocityY(double y){
        this.jumpCapacity = y;
    }

    /**
     * Set center value x value of the node
     * @param inputX center value
     */
    public void setCenterX(double inputX){
        nodeObject.setLayoutX(inputX - nodeObject.getLayoutBounds().getCenterX());
        setX(inputX - entityWidth /2);
    }

    /**
     * Set max y value (furthest down value on screen)
     * @param inputY max y value
     */
    public void setMaxY(double inputY){
        nodeObject.setLayoutY(inputY - nodeObject.getLayoutBounds().getMaxY());
        this.setY(inputY - this.getHeight());
    }

    /**
     * set hitpoints and sets Alive status accordingly
     * @param hitpoints int amount of health left
     */
    public void setHitpoints(int hitpoints){
        currentHitpoints=hitpoints;
        if (currentHitpoints <= 0){
            status_Alive = false;
        }
    }

    /**
     * Return how much health remaining
     * @return current hitpoints
     */
    public int getHitpoints(){
        return currentHitpoints;
    }

    /**
     * Previous x value
     * @param previous x
     */
    public void setPreviousX(double previous){
        previousX = previous;
    }

    /**
     * get previous x value of entity
     * @return previous x
     */
    public double getPreviousX(){
        return previousX;
    }

    /**
     * set previous y value of entity
     * @param previous previous y
     */
    public void setPreviousY(double previous){
        previousY = previous;
    }

    /**
     * get previous y value of entity
     * @return previous y value
     */
    public double getPreviousY(){
        return previousY;
    }

    /**
     * get the center value in parent of entity
     * @return center value of node object
     */
    public double getCenterX(){
        return nodeObject.getBoundsInParent().getCenterX();
    }

    /**
     * get width of entity
     * @return double width of entity
     */
    public double getEntityWidth(){
        return entityWidth;
    }

    /**
     * get height of entity
     * @return double height of entity
     */
    public double getEntityHeight(){
        return entityHeight;
    }

    /**
     * ge max y
     * @return max y value in parent
     */
    public double getMaxY(){
        return nodeObject.getBoundsInParent().getMaxY();
    }

    /**
     * set the force on entity in x direction
     * @param force x force
     */
    public void setXForce(double force){
        xForce = force;
    }

    /**
     * set the force on entity in y direction
     * @param force y force
     */
    public void setYForce(double force){
        yForce = force;
    }

    /**
     * get the force on entity in x direction
     * @return xForce
     */
    public double getXForce(){
        return xForce;
    }

    /**
     * get the force on entity in y direction
     * @return yForce
     */
    public double getYForce(){
        return yForce;
    }

    /**
     * entity by default has gravity
     * @return true boolean has gravity
     */
    public boolean hasGravity(){
        return true;
    }

    /**
     * Returns facing right for true and left for false
     * @return boolean
     */
    public boolean getFacing(){
        return facing;
    }

    /**
     * If it is jumping state
     * @return boolean
     */
    public boolean isJump(){
        return jump;
    }

    /**
     * Set if state is jumping
     * @param isJump jumping
     */
    public void setJump(boolean isJump){
        jump = isJump;
    }

    /**
     * get if special action is active
     * @return boolean
     */
    public boolean getSpecialAction(){
        return specialAction;
    }

    /**
     * set special action to active (true) or not active (false)
     * @param specialAction boolean
     */
    public void setSpecialAction(boolean specialAction){
        this.specialAction = specialAction;
    }

    /**
     * Set normal force so gravity and normal cancel
     * @param gravity double value
     */
    public void setNormalForce(double gravity){
        this.normalForce = gravity;
    }

    /**
     * set direction entity facing
     * @param direction direction right true and left is false
     */
    public void setFacing(boolean direction){
        facing = direction;
    }

    /**
     * set horizontal movement set velocity
     * and set state to true
     * @param moving boolean
     * @param velocity double
     */
    public void setHorizontalMovement(boolean moving, double velocity){
        this.moving = moving;
        setVelocityX(velocity);
    }

    /**
     * get Horizonal movement
     * @return moving
     */
    public boolean getHorizontalMovement(){
        return moving;
    }

    /**
     * set if entity should be shooting
     * @param shoots boolean
     */
    public void setShoots(boolean shoots){
        this.shoots = shoots;
    }

    /**
     * check if entity state is shooting
     * @return boolean
     */
    public boolean doesShoot(){
        return shoots;
    }

    /**
     * does entity percolate (water fall attributes)
     * @return boolean
     */
    public boolean isPercolate(){
        return percolate;
    }

    /**
     * set entity to percolate
     * @param percolate boolean
     */
    public void setPercolate(boolean percolate){
        this.percolate = percolate;
    }

    /**
     * set source of percolation (top)
     * @param source boolean
     */
    public void setSource(boolean source){
        this.source = source;
    }

    /**
     * get if source of percolation
     * @return boolean
     */
    public boolean isSource(){
        return source;
    }

    /**
     * generate coins attribute
     * @return boolean
     */
    public boolean doesGenerateCoins(){
        return makesCoins;
    }

    /**
     * set to generate coins
     * @param makesCoins boolean
     */
    public void setGenerateCoins(boolean makesCoins){
        this.makesCoins = makesCoins;
    }

    /**
     * entity has shrunk (prevents infinitly small)
     * @return boolean
     */
    public boolean hasShrunk(){return shrunk; }

    /**
     * set entity to has shrunk
     * @param shrunk boolean
     */
    public void setShrunk(boolean shrunk){ this.shrunk = shrunk; }

    /**
     * left collision occurs, so invoke proper action for that collision interaction
     * @param entity
     */
    public void leftCollideable(Entity entity) {
        invokeMethod(entity, "left");
    }

    /**
     * right collision occurs, so invoke proper action for that collision interaction
     * @param entity
     */
    public void rightCollideable(Entity entity) {
        invokeMethod(entity, "right");
    }

    /**
     * bottom collision occurs, so invoke proper action for that collision interaction
     * @param entity
     */
    public void bottomCollideable(Entity entity) {
        invokeMethod(entity, "bottom");
    }

    /**
     * top collision occurs, so invoke proper action for that collision interaction
     * @param entity
     */
    public void topCollideable(Entity entity) {
        invokeMethod(entity, "top");
    }


    /**
     * invokeMethod reads in collision action from property file to call appropriate method
     * on entity based on side of collision, and type of entity collided with
     * Default is no collision if no input/improper input in property file found
     * @param entity entity collided with this entity
     * @param collisionName collision side
     */
    protected void invokeMethod(Entity entity, String collisionName){
        try {
            GamePropertyFileReader reader = new GamePropertyFileReader(this.getClass().getSimpleName());
            Iterator methods = reader.getMethods(collisionName).iterator();
            Iterator parameter = reader.getParameters(collisionName).iterator();

            while (methods.hasNext() && parameter.hasNext()) {

                Class current = this.getClass().getSuperclass();
                while (current != Entity.class) {
                    current = current.getSuperclass();
                }
                Method x = current.getDeclaredMethod((String) methods.next(), Entity.class, String.class);
                x.setAccessible(true);
                String input = (String) parameter.next();
                x.invoke(this, entity, input);
            }
        } catch (Exception e) {
            return;
        }
    }

    protected void entityDeath(Entity entity, String object) {
        if (entity.getId().equals(object)) {
            entity.setHitpoints(0);
        }
    }

    protected void thisDeath(Entity entity, String object) {
        if (entity.getId().equals(object)) {
            this.setHitpoints(0);
        }
    }

    protected void applyY(Entity entity, String object) {
        if(entity.getId().equals(object)) {
            entity.setJump(true);
            entity.setVelocityY(APPLY_Y_VELOCITY);
            entity.setMaxY(entity.getMaxY() - JUMP_INITIAL_OFFSET);
        }
    }

    protected void spinning(Entity entity, String object){
        delay++;
        if(entity.getId().equals(object) && delay > DELAY_BUFFER){
            delay = 0;
            if(getVelocityX() == 0) {
                setSpecialAction(true);
                setHorizontalMovement(true, SPINNING_VELOCITY);
            }
            else{
                setSpecialAction(false);
                setHorizontalMovement(false, 0);
                entity.setVelocityX(0);
            }
        }
    }

    protected void healthPenaltyWithDelay(Entity entity, String object) {
        delay++;
        if (entity.getId().equals(object) && delay > DELAY_BUFFER) {
            delay = 0;
            entity.setHitpoints(entity.getHitpoints() + HEALTH_PENALTY);
        }
    }

    protected void healthPenaltyOnObject(Entity entity, String object) {
        if (entity.getId().equals(object)) {
            entity.setHitpoints(entity.getHitpoints() + HEALTH_PENALTY);
        }
    }

    protected void topDeathMakeCoins(Entity entity, String object){
        if (entity.getId().equals(object) && entity.getVelocityY() > 0) {
            this.setHitpoints(0);
            setGenerateCoins(true);
        }
    }


    protected void leftObstacle(Entity entity, String object){
        if (entity.getId().equals(object)) {
            entity.setXForce(0);
            entity.setCenterX(entity.getCenterX() + COLLISION_OFFSET);
            entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
        }
    }

    protected void rightObstacle(Entity entity, String object){
        if (entity.getId().equals(object)) {
            entity.setXForce(0);
            entity.setCenterX(entity.getCenterX() - COLLISION_OFFSET);
            entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
        }
    }

    protected void bottomObstacle(Entity entity, String object){
        if (entity.getId().equals(object)) {
            entity.setVelocityY(0);
        }
    }

    protected void topObstacle(Entity entity, String object){
        if (entity.getId().equals(object)) {
            entity.setMaxY(getBoundsInParent().getMinY());
            entity.setYForce(entity.getYForce() + NEGATIVE_DIRECTION * normalForce);
            entity.setVelocityY(0);
            entity.setJump(false);
        }
    }


}
