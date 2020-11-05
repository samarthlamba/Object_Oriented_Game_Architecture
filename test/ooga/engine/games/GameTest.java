package ooga.engine.games;

import ooga.engine.games.Game;
import ooga.engine.entities.Moveables;
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
       Collection<Moveables> entities = game.getEntities();
       double initialPosition = 75;
       Moveables entity = entities.iterator().next();
       assertEquals(initialPosition, entity.getCenterX());
       for(int i = 0; i < 100; i++){
           game.RIGHT(entity);
           game.updateMoveables();
       }
       System.out.println(entity.getCenterX());
       assertTrue(game.areEqualDouble(75.27, entity.getCenterX(), 2));
   }

    @Test
    public void leftMovementTest() {
        Game game = factory.makeCorrectGame("testMovement.csv");
        Collection<Moveables> entities = game.getEntities();
        double initialPosition = 75;
        Moveables entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        for(int i = 0; i < 10; i++){
            game.LEFT(entity);
            game.updateMoveables();
        }
        assertTrue(game.areEqualDouble(74.9, entity.getCenterX(), 1));
    }

    @Test
    public void jumpTest(){
        Game game = factory.makeCorrectGame("testMovement.csv");
        Collection<Moveables> entities = game.getEntities();
        Moveables entity = entities.iterator().next();
        game.UP(entity);
        double previous = 200;
        for(int i = 0; i < 100; i++) {
            game.updateMoveables();
           // assertTrue(entity.getMaxY() < previous);
            System.out.println(entity.getMaxY());
            previous = entity.getMaxY();
        }
        for(int i = 0; i < 10; i++) {
            game.updateMoveables();
            assertTrue(entity.getMaxY() >= previous);
            System.out.println(entity.getMaxY());
            previous = entity.getMaxY();
        }

        for(int i = 0; i < 10; i++){
            game.updateMoveables();
            System.out.println(entity.getMaxY());
        }

        assertEquals(entity.getMaxY(), 200);
    }

    @Test
    public void leftCollisionTest() {
        Game game = factory.makeCorrectGame("testNoMovement.csv");
        Collection<Moveables> entities = game.getEntities();
        double initialPosition = 75;
        Moveables entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        System.out.println(entity.getCenterX());
        for(int i = 0; i < 10; i++){
            game.LEFT(entity);
            game.updateMoveables();
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 1));
    }

    @Test
    public void rightCollisionTest() {
        Game game = factory.makeCorrectGame("testNoMovement.csv");
        Collection<Moveables> entities = game.getEntities();
        double initialPosition = 75;
        Moveables entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        for(int i = 0; i < 10; i++){
            game.RIGHT(entity);
            game.updateMoveables();
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 1));
    }

    @Test
    public void rightWallCollisionTest() {
        Game game = factory.makeCorrectGame("noRightMovement.csv");
        Collection<Moveables> entities = game.getEntities();
        double initialPosition = 75;
        Moveables entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        for(int i = 0; i < 10; i++){
            game.RIGHT(entity);
            game.updateMoveables();
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 1));
    }

    @Test
    public void bottomCollisionTest(){
        Game game = factory.makeCorrectGame("testCeilingMovement.csv");
        Collection<Moveables> entities = game.getEntities();
        double initialPosition = 75;
        Moveables entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        game.UP(entity);
        for(int i = 0; i < 300; i++){
            game.updateMoveables();
            System.out.println(entity.getMaxY());
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 1));

    }

}