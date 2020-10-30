package ooga.engine.entities;

public abstract class Player extends Entity{

  private static final int ID = 0;
  public Player(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
  }
  public int getID(){
    return this.ID;
  }

}
