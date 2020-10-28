package ooga.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import ooga.engine.Games.Game;
import ooga.engine.Games.MarioGame;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Mario;
import ooga.engine.obstacles.Obstacle;
import ooga.engine.obstacles.Wall;
import org.junit.jupiter.api.Test;

public class GameFactoryTest {
  private static final GameFactory factory = new GameFactory();

  @Test
  public void testFactoryConstructsProperGame() {
    Game gameFromLoader = factory.makeCorrectGame("testFile.csv");
    assertTrue(gameFromLoader instanceof MarioGame);
    Collection<Entity> entitiesFromGame = gameFromLoader.getEntities();
    Collection<Obstacle> obstaclesFromGame = gameFromLoader.getBackground();
    assertEquals(1,entitiesFromGame.size());
    for(Entity each : entitiesFromGame) {
      assertTrue(each instanceof Mario);
    }
    assertEquals(3,obstaclesFromGame.size());
    for(Obstacle each : obstaclesFromGame) {
      assertTrue(each instanceof Wall);
    }
  }

  @Test
  public void testFactoryThrowsExceptionWithBadSymbol() {
    try{
      factory.makeCorrectGame("testFileBadSymbols.csv");
    } catch (Exception e) {
      assertTrue(e instanceof FactoryException);
      assertEquals("Unable to build game from testFileBadSymbols.csv: Symbol 2 not present in this game",e.getMessage());
    }
  }

  @Test
  public void testFactoryThrowsExceptionIfFileNotFound() {
    try {
      factory.makeCorrectGame("notAFile.csv");
    } catch (Exception e) {
      assertTrue(e instanceof FactoryException);
      assertEquals("Unable to build game from notAFile.csv: Could not find file with name notAFile.csv",e.getMessage());
    }
  }

  @Test
  public void testFactoryThrowsExceptionOnEmptyFile() {
    try {
      factory.makeCorrectGame("testEmptyFile.csv");
    } catch (Exception e) {
      assertTrue(e instanceof FactoryException);
      assertEquals("Unable to build game from testEmptyFile.csv: Empty file",e.getMessage());
    }
  }

}
