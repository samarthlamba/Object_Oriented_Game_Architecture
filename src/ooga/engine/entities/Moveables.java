package ooga.engine.entities;

import javafx.scene.Node;

 public interface Moveables {

   Node getNode();

  // abstract int getID();

   double getVelocityX();

   double getVelocityY();

   double getJumpMax();

  /*  double mass(){
     return 5;
   }*/
   void setVelocityX(double x);

   void setVelocityY(double y);

   void setCenterX(double inputX);

   abstract void update();

   void setMaxY(double inputY);

   void setHitpoints(int hitpoints);

   int getHitpoints();

   void setPreviousX(double previous);

   double getPreviousX();

   void setPreviousY(double previous);

   double getPreviousY();

 /*  double getX(){
      return nodeObject.getLayoutX();
  }*/

   double getCenterX();

   double getEntityWidth();

   double getEntityHeight();

   double getMaxY();

   String getId();

   double getYForce();

   double getXForce();

   void setXForce(double force);

   void setYForce(double force);

  double getTimeElapsedX();

  double getTimeElapsedY();

  void setTimeElapsedY(double time);

  void setTimeElapsedX(double time);

  boolean isJump();

  void setJump(boolean jump);

  //true = right

}
