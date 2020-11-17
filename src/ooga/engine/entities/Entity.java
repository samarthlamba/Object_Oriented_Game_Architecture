package ooga.engine.entities;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.games.Collideable;
import ooga.engine.games.GamePropertyFileReader;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Entity extends Rectangle implements Collideable, Movable {
    private static final double NEGATIVE_DIRECTION = -1;
    private final int SCENE_WIDTH;
    private final int SCENE_HEIGHT;
    public static final int HEALTH_PENALTY = -1;
    private int currentHitpoints = 3;
    private Node nodeObject;
    private double speed = 0;
    private double previousX;
    private double previousY;
    private double jumpCapacity = 0;
    private double xForce = 0;
    private double yForce = 0;
    boolean status_Alive = true;
    private boolean facing = true;
    private boolean jump = false;
    private boolean finished = false;
    private double normalForce = 0;

    public Entity(int objectWidth,int objectHeight,  double initialX, double initialY) {
        this.SCENE_WIDTH = objectWidth;
        this.SCENE_HEIGHT = objectHeight;
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
        this.finished = finished;
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
        setX(inputX - SCENE_WIDTH/2);
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
        return SCENE_WIDTH;
    }

    public double getEntityHeight(){
        return SCENE_HEIGHT;
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

    public void setNormalForce(double gravity){
        this.normalForce = gravity;
    }

    public void setFacing(boolean direction){
        facing = direction;
    }

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


    protected void entityDeath(Entity entity, String object) {
        if (entity.getId().equals(object)) {
            entity.setHitpoints(0);
        }
    }

    protected void invokeMethod(Entity entity, String collisionName){
        try {
            GamePropertyFileReader reader = new GamePropertyFileReader(this.getClass().getSimpleName());
            Iterator methods = reader.getMethods(collisionName).iterator();
            Iterator parameter = reader.getParameters(collisionName).iterator();

            while (methods != null && methods.hasNext() && parameter.hasNext()) {

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
    protected void thisDeath(Entity entity, String object) {
        if (entity.getId().equals(object)) {
            this.setHitpoints(0);
        }
    }

    protected void applyY(Entity entity, String object) {
        if(entity.getId().equals(object) && entity.getYForce() != 0) {
            entity.setJump(true);
            entity.setVelocityY(-2600);
            entity.setMaxY(entity.getMaxY() - 2);
        }
    }

    protected void healthPenaltyOnObject(Entity entity, String object) {
        if (entity.getId().equals(object)) {
            entity.setHitpoints(entity.getHitpoints() + HEALTH_PENALTY);
        }
    }


    protected void leftObstacle(Entity entity, String object){
        if (entity.getId().equals(object)) {
            entity.setXForce(0);
            entity.setCenterX(entity.getCenterX() + 1);
            entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
        }
    }

    protected void rightObstacle(Entity entity, String object){
        if (entity.getId().equals(object)) {
            entity.setXForce(0);
            entity.setCenterX(entity.getCenterX() - 1);
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
