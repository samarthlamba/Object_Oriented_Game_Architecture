package ooga.engine.games;

import com.sun.source.tree.AssertTree;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Movable;
import ooga.loader.GameFactory;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  public void testPlayerSpecialAction(){
    Game game = gameFactory.makeCorrectGame("testMetroidPlayer");
    Collection<Entity> entities = (Collection<Entity>) game.getEntities();
    game.playerAction();
    boolean bulletCreated = false;
    for(Entity entity : entities){
      if(entity.getId().equals("bullet")){
        bulletCreated = true;
      }
    }
    assertTrue(bulletCreated);
  }

  @Test
  public void testBulletDirection(){
    Game game = gameFactory.makeCorrectGame("testMetroidPlayer");
    Collection<Entity> entities = (Collection<Entity>) game.getEntities();
    game.playerAction();
    int bulletVelocity = 1000;
    for(Entity entity : entities){
      if(entity.getId().equals("bullet")){
        assertEquals(entity.getVelocityX(), 1000);
      }
    }

    boolean changeDirection = false;
    try {
      game.moveLeft();
      game.updateLevel();
    }
    catch(NullPointerException e) {
      game.playerAction();
      for (Entity entity : entities) {
        if (entity.getId().equals("bullet")) {
          if(entity.getVelocityX() < 0){
            changeDirection = true;
          }
        }
      }
    }
    assertTrue(changeDirection);
  }
}
