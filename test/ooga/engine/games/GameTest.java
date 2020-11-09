package ooga.engine.games;

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
       Collection<Entity> entities = (Collection<Entity>) game.getEntities();
       double initialPosition = 75;
       Entity entity = entities.iterator().next();
       assertEquals(initialPosition, entity.getCenterX());
       game.RIGHT(entity);
       game.updateLevel();
       System.out.println(entity.getCenterX());
       assertTrue(game.areEqualDouble(88.88, entity.getCenterX(), 2));
   }

    @Test
    public void leftMovementTest() {
        Game game = factory.makeCorrectGame("testMovement.csv");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        game.LEFT(entity);
        game.updateLevel();
        System.out.println(entity.getCenterX());
        assertTrue(game.areEqualDouble(61.1, entity.getCenterX(), 1));
    }

    @Test
    public void jumpTest(){
        Game game = factory.makeCorrectGame("testMovement.csv");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        Entity entity = entities.iterator().next();
        game.UP(entity);
        double previous = 200;
        for(int i = 0; i < 16; i++) {
            game.updateLevel();
            assertTrue(entity.getMaxY() < previous);
            System.out.println(entity.getMaxY());
            previous = entity.getMaxY();
        }
        for(int i = 0; i < 17; i++) {
            game.updateLevel();
            assertTrue(entity.getMaxY() >= previous);
            System.out.println(entity.getMaxY());
            previous = entity.getMaxY();
        }

        for(int i = 0; i < 10; i++){
            game.updateLevel();
            System.out.println(entity.getMaxY());
        }

        assertTrue(game.areEqualDouble(entity.getMaxY(), 200, 1));
    }

    @Test
    public void leftCollisionTest() {
        Game game = factory.makeCorrectGame("testNoMovement.csv");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        System.out.println(entity.getCenterX());
        for(int i = 0; i < 100; i++){
            game.LEFT(entity);
            game.updateLevel();
        }

        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 1));
    }

    @Test
    public void rightCollisionTest() {
        Game game = factory.makeCorrectGame("testNoMovement.csv");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        for(int i = 0; i < 10; i++){
            game.RIGHT(entity);
            game.updateLevel();
            System.out.println(entity.getCenterX());
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 1));
    }

    @Test
    public void rightWallCollisionTest() {
        Game game = factory.makeCorrectGame("noRightMovement.csv");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        for(int i = 0; i < 10; i++){
            game.RIGHT(entity);
            game.updateLevel();
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 1));
    }

    @Test
    public void bottomCollisionTest(){
        Game game = factory.makeCorrectGame("testCeilingMovement.csv");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        game.UP(entity);
        for(int i = 0; i < 300; i++){
            game.updateLevel();
            System.out.println(entity.getMaxY());
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 1));

    }

    @Test
    public void enemyMovement(){
        Game game = factory.makeCorrectGame("testEnemyMovement.csv");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        Entity entity = entities.iterator().next();
        for(int i = 0; i < 300; i++){
            game.updateLevel();
            assertTrue(Math.abs(entity.getVelocityX()) == 200);
            assertTrue(entity.getCenterX() > 20);
            assertTrue(entity.getCenterX() < 130);
            assertTrue(game.areEqualDouble(entity.getMaxY(), 200, 1));
        }
    }

    @Test
    public void testEnemyDies(){
        Game game = factory.makeCorrectGame("testEnemyTopCollision.csv");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        Entity player = game.findMainPlayer();
        Entity enemy = player;
        for(Entity entity : entities){
            if(entity.getId().equals("enemy")){
                enemy = entity;
            }
        }
        for(int i = 0; i < 300; i++){
            game.updateLevel();
        }

        assertEquals(enemy.getHitpoints(), 0);
        assertEquals(player.getHitpoints(), 100);
    }

    @Test
    public void testTurtleMarioCollision(){
        Game game = factory.makeCorrectGame("testTurtleMarioCollision.csv");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        Entity player = game.findMainPlayer();
        double startY = player.getMaxY();
        double newY = 0;
        for(int i = 0; i < 300; i++){
            game.updateLevel();
            if(player.getMaxY() != startY){
                newY = player.getMaxY();
            }
        }
        assertTrue(newY < startY);
    }

}
