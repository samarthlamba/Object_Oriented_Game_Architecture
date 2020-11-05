package ooga.engine.entities;


public abstract class Enemy extends Entity{
  private final static String ID = "enemy";

  public Enemy(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setId(ID);
  }

 /* public int getID(){
    return this.ID;
  }*/

}
