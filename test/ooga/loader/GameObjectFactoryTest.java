package ooga.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ooga.engine.entities.Entity;
import org.junit.jupiter.api.Test;

public class GameObjectFactoryTest {

  @Test
  public void testExceptionThrownOnBadSymbol() {
    GameObjectFactory<Entity> entityFactory = new GameObjectFactory<>("Mario");
    try {
      entityFactory.makeGameObject("2", 1, 1);
    } catch (Exception e) {
      assertTrue( e instanceof FactoryException);
      assertEquals("Symbol 2 not present in this game", e.getMessage());
    }
  }

}
