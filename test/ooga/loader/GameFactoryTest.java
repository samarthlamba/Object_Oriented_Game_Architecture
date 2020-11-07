package ooga.loader;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;

import ooga.engine.entities.Moveables;
import ooga.engine.games.Game;
import ooga.engine.games.MarioGame;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Mario;
import ooga.engine.obstacles.Collideable;
import ooga.engine.obstacles.Obstacle;
import ooga.engine.obstacles.Wall;
import org.junit.jupiter.api.Test;

public class GameFactoryTest {
  private static final GameFactory factory = new GameFactory();

  @Test
  public void testFactoryConstructsProperGame() {
    Game gameFromLoader = factory.makeCorrectGame("TestFile");
    assertTrue(gameFromLoader instanceof MarioGame);
    Collection<Moveables> entitiesFromGame = gameFromLoader.getEntities();
    Collection<Collideable> obstaclesFromGame = gameFromLoader.getBackground();
    assertEquals(1,entitiesFromGame.size());
    for(Moveables each : entitiesFromGame) {
      assertTrue(each instanceof Mario);
    }
    assertEquals(3,obstaclesFromGame.size());
    for(Collideable each : obstaclesFromGame) {
      assertTrue(each instanceof Wall);
    }
  }

  @Test
  public void testFactoryThrowsExceptionWithBadSymbol() {
    try{
      factory.makeCorrectGame("testFileBadSymbols");
    } catch (Exception e) {
      assertTrue(e instanceof FactoryException);
      assertEquals("Unable to build game from testFileBadSymbols: Symbol 2 not present in this game",e.getMessage());
    }
  }

  @Test
  public void testFactoryThrowsExceptionIfFileNotFound() {
    try {
      factory.makeCorrectGame("notAFile");
    } catch (Exception e) {
      assertTrue(e instanceof FactoryException);
      assertEquals("Unable to build game from notAFile: Could not find file with name notAFile.csv",e.getMessage());
    }
  }

  @Test
  public void testFactoryThrowsExceptionOnEmptyFile() {
    try {
      factory.makeCorrectGame("testEmptyFile");
    } catch (Exception e) {
      assertTrue(e instanceof FactoryException);
      assertEquals("Unable to build game from testEmptyFile: Empty file",e.getMessage());
    }
  }

  @Test
  public void testFactoryUsesDefaultsIfNoPropertiesExistsForLevel() {
    try {
      Game game = factory.makeCorrectGame("MarioLevel2");
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testMalformedBeanThrowsError() {
    try {
      factory.makeCorrectGame("MalformedBean");
    } catch (Exception e) {
      assertTrue(e instanceof FactoryException);
      assertEquals("Unable to build game from MalformedBean: Error making Bean",e.getMessage());
    }
  }

}
