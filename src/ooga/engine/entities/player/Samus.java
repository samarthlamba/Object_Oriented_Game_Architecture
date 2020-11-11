package ooga.engine.entities.player;

import ooga.engine.entities.player.Player;

/**
 * Metroid implementation of Player
 */
public class Samus extends Player {

  public Samus(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
    setId("player");
  }

}
