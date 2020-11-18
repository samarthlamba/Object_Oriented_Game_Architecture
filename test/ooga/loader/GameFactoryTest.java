package ooga.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;

import javafx.scene.Node;
import ooga.engine.entities.Movable;
import ooga.engine.entities.MovableBounds;
import ooga.engine.games.Game;
import ooga.engine.games.MarioGame;
import ooga.engine.entities.player.Mario;
import ooga.engine.obstacles.Wall;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Test;

public class GameFactoryTest extends DukeApplicationTest {
  private static final GameFactory factory = new GameFactory();

  @Test
  public void testFactoryConstructsProperGame() throws FactoryException {
    Game gameFromLoader = factory.makeCorrectGame("TestFile");
    assertTrue(gameFromLoader instanceof MarioGame);
    Collection<MovableBounds> entitiesFromGame = (Collection<MovableBounds>) gameFromLoader.getEntities();
    Collection<Node> obstaclesFromGame = (Collection<Node>) gameFromLoader.getBackground();
    Movable player = gameFromLoader.getActivePlayer();
    assertEquals(1,entitiesFromGame.size());
    for(MovableBounds each : entitiesFromGame) {
      assertTrue(each instanceof Mario);
    }
    assertTrue(player instanceof Mario);
    assertTrue(entitiesFromGame.contains(player));
    assertEquals(3,obstaclesFromGame.size());
    for(Node each : obstaclesFromGame) {
      assertTrue(each instanceof Wall);
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
  public void testFactoryThrowsExceptionIfNoPlayer() {
    try {
      factory.makeCorrectGame("testNoPlayer");
    } catch (Exception e) {
      assertTrue(e instanceof FactoryException);
      assertEquals("Unable to build game from testNoPlayer: No player found",e.getMessage());
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

  @Test
  public void testEveryMarioLevelLoads() {
    try {
      factory.makeCorrectGame("MarioLevel1");
      factory.makeCorrectGame("MarioLevel2");
      factory.makeCorrectGame("MarioLevel3");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

  }

  @Test
  public void testEveryMetroidLevelLoads() {
    try {
      factory.makeCorrectGame("MetroidLevel1");
      factory.makeCorrectGame("MetroidLevel2");
      factory.makeCorrectGame("MetroidLevel3");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void testEveryVikingsLevelLoads() {
    try {
      factory.makeCorrectGame("VikingsLevel1");
      factory.makeCorrectGame("VikingsLevel2");
      factory.makeCorrectGame("VikingsLevel3");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void testMakeRandomMarioGameDoesNotThrowException() {
    try {
      factory.makeRandomGame("Mario");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void testMakeRandomMetroidGameDoesNotThrowException() {
    try {
      factory.makeRandomGame("Metroid");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void testMakeRandomVikingsGameDoesNotThrowException() {
    try {
      factory.makeRandomGame("Vikings");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void testMakeRandomGameWithoutDefinitionThrowsException() {
    try{
      factory.makeRandomGame("BadGame");
    } catch (Exception e) {
      assertTrue(e instanceof FactoryException);
      assertEquals("Error building random level for game BadGame", e.getMessage());
    }
  }


}
