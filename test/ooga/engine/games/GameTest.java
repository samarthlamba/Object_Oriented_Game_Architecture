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
       Game game = factory.makeCorrectGame("testMovement.csv");
       Collection<Entity> entities = game.getEntities();
       double initialPosition = 75;
       Entity entity = entities.iterator().next();
       assertEquals(initialPosition, entity.getCenterX());
       game.RIGHT(entity);
       game.updateEntity();
       System.out.println(entity.getCenterX());
       assertTrue(game.areEqualDouble(75, entity.getCenterX(), 2));
   }

    @Test
    public void leftMovementTest() {
        Game game = factory.makeCorrectGame("testMovement.csv");
        Collection<Entity> entities = game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        for(int i = 0; i < 10; i++){
            game.LEFT(entity);
            game.updateEntity();
            System.out.println(entity.getCenterX());
        }
        assertTrue(game.areEqualDouble(74.666, entity.getCenterX(), 2));
    }

    @Test
    public void jumpTest(){
        Game game = factory.makeCorrectGame("testMovement.csv");
        Collection<Entity> entities = game.getEntities();
        Entity entity = entities.iterator().next();
        game.UP(entity);
        double previous = 200;
        for(int i = 0; i < 8; i++) {
            game.updateEntity();
            assertTrue(entity.getMaxY() < previous);
            previous = entity.getMaxY();
           //System.out.println(previous);
        }
        for(int i = 0; i < 4; i++) {
            game.updateEntity();
            System.out.println(entity.getMaxY());
            assertTrue(entity.getMaxY() >= previous);
            previous = entity.getMaxY();
        }

        for(int i = 0; i < 10; i++){
            game.updateEntity();
            System.out.println(entity.getMaxY());
        }

        assertEquals(entity.getMaxY(), 200);
    }

    @Test
    public void leftCollisionTest() {
        Game game = factory.makeCorrectGame("testNoMovement.csv");
        Collection<Entity> entities = game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        System.out.println(entity.getCenterX());
        for(int i = 0; i < 10; i++){
            game.LEFT(entity);
            game.updateEntity();
        }
        assertTrue(game.areEqualDouble(50, entity.getCenterX(), 2));
    }

    @Test
    public void rightCollisionTest() {
        Game game = factory.makeCorrectGame("testNoMovement.csv");
        Collection<Entity> entities = game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        System.out.println(entity.getCenterX());
        for(int i = 0; i < 10; i++){
            game.RIGHT(entity);
            game.updateEntity();
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 2));
    }

    @Test
    public void rightWallCollisionTest() {
        Game game = factory.makeCorrectGame("noRightMovement.csv");
        Collection<Entity> entities = game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        System.out.println(entity.getCenterX());
        for(int i = 0; i < 10; i++){
            game.RIGHT(entity);
            game.updateEntity();
            System.out.println(entity.getCenterX());
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 2));
    }



}