package ooga.engine.entities.player;

import ooga.engine.entities.Entity;

public abstract class Player extends Entity {

  private int speed = 0;
  private final static String ID = "player";

  public Player(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
    setId(ID);
  }

  /*public int getID(){
    return this.ID;
  }*/

/*  public void moveLeft(){
    setX(getX()-speed);
  }

  public void moveRight(){
    setX(getX()+ speed);
  }

  public void changeSpeed(int speed){
    this.speed = speed;
  }*/

}
