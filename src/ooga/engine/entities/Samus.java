package ooga.engine.entities;

/**
 * Metroid implementation of Player
 */
public class Samus extends Player{

  public Samus(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
    setId("samus");
  }

}
