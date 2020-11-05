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
  //true = right

}
