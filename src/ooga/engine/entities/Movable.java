package ooga.engine.entities;

 public interface Movable extends MovableBounds {
     boolean getStatusAlive();

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

     double getTimeElapsedX();

     double getTimeElapsedY();

     void setTimeElapsedY(double time);

     void setTimeElapsedX(double time);

     boolean hasGravity();

     void setJump(boolean isJump);

     void setFacing(boolean facing); //TODO: take out once key moved
}
