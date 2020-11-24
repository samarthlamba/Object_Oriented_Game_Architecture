package ooga.engine.entities;

/**
 * Interface that is passed to game engine that only includes methods it needs extends
 * MovableBounds (interface being passed to view) since overlaps
 * Only gives game access to methods necessary
 * Implemented by Entity
 */
 public interface Movable extends MovableBounds {

     double getVelocityX();

     double getVelocityY();

     void setVelocityX(double x);

     void setVelocityY(double y); //TODO: take out once key moved

     void setCenterX(double inputX);

     void setMaxY(double inputY);

     void setPreviousX(double previous);

     double getPreviousX();

     void setPreviousY(double previous);

     double getPreviousY();

     double getEntityWidth();

     double getEntityHeight();

     void setXForce(double force);

     void setYForce(double force);

     double getXForce();

     double getYForce();

     boolean hasGravity();

     int getHitpoints();

     void setJump(boolean isJump);

     boolean isJump();

     void setFacing(boolean facing); //TODO: take out once key moved

     boolean getStatusAlive();

     boolean hasWon();

     void setHitpoints(int hitpoints);

     void setSpecialAction(boolean action);

     void setNormalForce(double gravity);

     boolean getHorizontalMovement();

     boolean isPercolate();

     boolean isSource();

     boolean doesShoot();

     boolean doesGenerateCoins();
}
