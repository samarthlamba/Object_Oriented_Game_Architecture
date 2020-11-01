package ooga.engine.games;

import javafx.scene.input.KeyCode;
import ooga.engine.games.Game;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;
import ooga.loader.GameFactory;
import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Collection;

class GameTest {
    private static final GameFactory factory = new GameFactory();

   @Test
   public void rightMovementTest() {
       Game game = factory.makeCorrectGame("testJump.csv");
       Collection<Entity> entities = game.getEntities();
       double initialPosition = 50;
       Entity entity = entities.iterator().next();
       assertEquals(initialPosition, entity.getX());
       game.RIGHT(entity);
       game.updateEntity();
       System.out.println(entity.getX());
       assertTrue(game.areEqualDouble(initialPosition + .5, entity.getX(), 3));
   }

    @Test
    public void leftMovementTest() {
        Game game = factory.makeCorrectGame("testJump.csv");
        Collection<Entity> entities = game.getEntities();
        double initialPosition = 50;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getX());
        game.LEFT(entity);
        game.updateEntity();
        assertTrue(game.areEqualDouble(initialPosition - .5, entity.getX(), 10));
    }

    @Test
    public void jumpTest(){
        Game game = factory.makeCorrectGame("testJump.csv");
        Collection<Entity> entities = game.getEntities();
        Entity entity = entities.iterator().next();
        game.UP(entity);
        System.out.println(entity.getY());
        for(int i = 0; i < 50; i++) {
            game.updateEntity();
            System.out.println(entity.getY());
        }
    }


}