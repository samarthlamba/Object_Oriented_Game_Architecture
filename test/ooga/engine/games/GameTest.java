package ooga.engine.games;

import ooga.engine.games.Game;
import ooga.engine.entities.Entity;
import ooga.loader.GameFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
       System.out.println(entity.getY());
       assertTrue(game.areEqualDouble(50.333, entity.getX(), 2));
   }

    @Test
    public void leftMovementTest() {
        Game game = factory.makeCorrectGame("testJump.csv");
        Collection<Entity> entities = game.getEntities();
        double initialPosition = 50;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getX());
        System.out.println(entity.getX());
        game.LEFT(entity);
        game.updateEntity();
        System.out.println(entity.getX());
        assertTrue(game.areEqualDouble(49.666, entity.getX(), 2));
    }

    @Test
    public void jumpTest(){
        Game game = factory.makeCorrectGame("testJump.csv");
        Collection<Entity> entities = game.getEntities();
        Entity entity = entities.iterator().next();
        game.UP(entity);
        double previous = 150;
        System.out.println(entity.getY());
        for(int i = 0; i < 8; i++) {
            game.updateEntity();
            assertTrue(entity.getY() < previous);
            previous = entity.getY();
            //System.out.println(previous);
        }
        for(int i = 0; i < 10; i++) {
            game.updateEntity();
            assertTrue(entity.getY() >= previous);
            previous = entity.getY();
        }

        for(int i = 0; i < 10; i++){
            game.updateEntity();
        }

        assertEquals(entity.getY(), 200);
    }


}