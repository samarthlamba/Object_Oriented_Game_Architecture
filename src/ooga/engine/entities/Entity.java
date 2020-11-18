package ooga.engine.entities;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.games.Collideable;
import ooga.engine.games.GamePropertyFileReader;
import java.lang.reflect.Method;
import java.util.Iterator;

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

    public Node getNode() {
        return this;
    }

    public boolean getStatusAlive(){
        return this.status_Alive;
    }

    public boolean hasWon(){
        return false;
    }

    public void setWon(boolean finished){
    }

    public double getVelocityX(){
        return speed;
    }

    public double getVelocityY(){
        return jumpCapacity;
    }

    public int getHealth(){
        return getHitpoints();
    }

    public void setVelocityX(double x){
        this.speed = x;
    }

    public void setVelocityY(double y){
        this.jumpCapacity = y;
    }

    public void setCenterX(double inputX){
        nodeObject.setLayoutX(inputX - nodeObject.getLayoutBounds().getCenterX());
        setX(inputX - entityWidth /2);
    }

    public void setMaxY(double inputY){
        nodeObject.setLayoutY(inputY - nodeObject.getLayoutBounds().getMaxY());
        this.setY(inputY - this.getHeight());
    }

    public void setHitpoints(int hitpoints){
        currentHitpoints=hitpoints;
        if (currentHitpoints <= 0){
            status_Alive = false;
        }
    }

    public int getHitpoints(){
        return currentHitpoints;
    }

    public void setPreviousX(double previous){
        previousX = previous;
    }

    public double getPreviousX(){
        return previousX;
    }

    public void setPreviousY(double previous){
        previousY = previous;
    }

    public double getPreviousY(){
        return previousY;
    }

    public double getCenterX(){
        return nodeObject.getBoundsInParent().getCenterX();
    }

    public double getEntityWidth(){
        return entityWidth;
    }

    public double getEntityHeight(){
        return entityHeight;
    }

    public double getMaxY(){
        return nodeObject.getBoundsInParent().getMaxY();
    }

    public void setXForce(double force){
        xForce = force;
    }

    public void setYForce(double force){
        yForce = force;
    }

    public double getXForce(){
        return xForce;
    }

    public double getYForce(){
        return yForce;
    }

    public boolean hasGravity(){
        return true;
    }

    public boolean getFacing(){
        return facing;
    }

    public boolean isJump(){
        return jump;
    }

    public void setJump(boolean isJump){
        jump = isJump;
    }

    public boolean getSpecialAction(){
        return specialAction;
    }

    public void setSpecialAction(boolean specialAction){
        this.specialAction = specialAction;
    }

    public void setNormalForce(double gravity){
        this.normalForce = gravity;
    }

    public void setFacing(boolean direction){
        facing = direction;
    }

    public void setHorizontalMovement(boolean moving, double velocity){
        this.moving = moving;
        setVelocityX(velocity);
    }

    public boolean getHorizontalMovement(){
        return moving;
    }

    public void setShoots(boolean shoots){
        this.shoots = shoots;
    }

    public boolean doesShoot(){
        return shoots;
    }

    public boolean isPercolate(){
        return percolate;
    }

    public void setPercolate(boolean percolate){
        this.percolate = percolate;
    }

    public void setSource(boolean source){
        this.source = source;
    }

    public boolean isSource(){
        return source;
    }

    public boolean doesGenerateCoins(){
        return makesCoins;
    }

    public void setGenerateCoins(boolean makesCoins){
        this.makesCoins = makesCoins;
    }

    public boolean hasShrunk(){return shrunk; }

    public void setShrunk(boolean shrunk){ this.shrunk = shrunk; }

    public void leftCollideable(Entity entity) {
        invokeMethod(entity, "left");
    }

    public void rightCollideable(Entity entity) {
        invokeMethod(entity, "right");
    }

    public void bottomCollideable(Entity entity) {
        invokeMethod(entity, "bottom");
    }

    public void topCollideable(Entity entity) {
        invokeMethod(entity, "top");
    }


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
