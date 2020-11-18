package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

public class Question extends Entity {
  private static final String ID = "question";
  public Question(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    this.setMaxY(initialY);
    setGenerateCoins(true);
    setId(ID);
  }

  @Override
  public boolean hasGravity(){
        return false;
    }


}
