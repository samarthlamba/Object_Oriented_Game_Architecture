package ooga.engine.entities.enemy;

/**
 * Lost Vikings Basic Enemy
 * has shooting ability (shoots arrows at random intervals)
 */
public class Goblin extends Enemy {

  public Goblin(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
    setShoots(true);
  }


}
