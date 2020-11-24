package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

public class Question extends Entity {

  /**
   * Used in mario has random coin generation property
   */
  private static final String ID = "question";

  public Question(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    this.setMaxY(initialY);
    setGenerateCoins(true);
    setId(ID);
  }

  /**
   * Does not have gravity force acting on entity
   * @return boolean false no gravity force experienced
   */
  @Override
  public boolean hasGravity() {
    return false;
  }


}
