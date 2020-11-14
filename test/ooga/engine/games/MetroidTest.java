package ooga.engine.games;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ooga.loader.GameFactory;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Test;

public class MetroidTest extends DukeApplicationTest {
  private final GameFactory gameFactory = new GameFactory();

  @Test
  public void testHasFinishedFalseIfEnemiesPresent() {
    Game metroid1 = gameFactory.makeCorrectGame("MetroidLevel1");
    assertFalse(metroid1.isWon());
  }

  @Test
  public void testHasFinishedTrueIfNoEnemies() {
    Game noEnemies = gameFactory.makeCorrectGame("testNoEnemies");
    assertTrue(noEnemies.isWon());
  }

}
