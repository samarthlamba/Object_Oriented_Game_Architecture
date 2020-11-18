package ooga.engine.games;

import javafx.stage.Stage;
import ooga.engine.entities.Entity;
import ooga.loader.FactoryException;
import ooga.loader.GameFactory;
import ooga.util.DukeApplicationTest;
import ooga.view.GamePlayScreen;
import org.junit.jupiter.api.Test;


import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class MarioTest extends DukeApplicationTest {
    private static final GameFactory factory = new GameFactory();
    private GamePlayScreen testScreen;

    @Override
    public void start(Stage stage) {
        testScreen = new GamePlayScreen();
    }

   @Test
   public void rightMovementTest() throws FactoryException {
       Game game = factory.makeCorrectGame("testMovement");
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
    public void leftMovementTest() throws FactoryException {

        Game game = factory.makeCorrectGame("testMovement");
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
    public void jumpTest() throws FactoryException {
        Game game = factory.makeCorrectGame("testMovement");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        Entity entity = entities.iterator().next();
        game.UP(entity);
        double previous = 200;
        for(int i = 0; i < 15; i++) {
            game.updateLevel();
            assertTrue(entity.getMaxY() < previous);
            System.out.println(entity.getMaxY());
            previous = entity.getMaxY();
        }
        for(int i = 0; i < 11; i++) {
            game.updateLevel();
            assertTrue(entity.getMaxY() >= previous);
            previous = entity.getMaxY();
        }

        for(int i = 0; i < 10; i++){
            game.updateLevel();
            System.out.println(entity.getMaxY());
        }

        assertTrue(game.areEqualDouble(entity.getMaxY(), 200, 1));
    }

    @Test
    public void leftCollisionTest() throws FactoryException {
        Game game = factory.makeCorrectGame("testNoMovement");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        System.out.println(entity.getCenterX());
        for(int i = 0; i < 1; i++){
            game.LEFT(entity);
        }
        for(int i = 0; i < 200; i++){
            game.updateLevel();
        }
        System.out.println(entity.getCenterX());
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 0));
    }

    @Test
    public void rightCollisionTest() throws FactoryException {
        Game game = factory.makeCorrectGame("testNoMovement");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        for(int i = 0; i < 1; i++){
            game.RIGHT(entity);
        }
        for(int i = 0; i < 100; i++){
            game.updateLevel();
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 0));
    }

    @Test
    public void rightWallCollisionTest() throws FactoryException {
        Game game = factory.makeCorrectGame("noRightMovement");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double initialPosition = 75;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        for(int i = 0; i < 1; i++){
            game.RIGHT(entity);
        }
        for(int i = 0; i < 100; i++){
            game.updateLevel();
        }
        assertTrue(game.areEqualDouble(75, entity.getCenterX(), 0));
    }

    @Test
    public void bottomCollisionTest() throws FactoryException {
        Game game = factory.makeCorrectGame("testCeilingMovement");
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
        try {
            Game game = factory.makeCorrectGame("testEnemyMovement");
            Collection<Entity> entities = (Collection<Entity>) game.getEntities();
            Entity entity = entities.iterator().next();
            for(int i = 0; i < 300; i++){
                game.updateLevel();
            }
        } catch (Exception e) {
            assertTrue(e instanceof FactoryException);
            assertEquals("Unable to build game from testEnemyMovement: No player found",e.getMessage());
        }
    }

    @Test
    public void testEnemyDies() throws FactoryException {
        Game game = factory.makeCorrectGame("testEnemyTopCollision");
        testScreen.setGameScreen(game);
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        Entity player = (Entity) game.getActivePlayer();
        Entity enemy = player;
        for(Entity entity : entities){
            if(entity.getId().equals("enemy")){
                enemy = entity;
            }
        }
        for(int i = 0; i < 300; i++){
            game.updateLevel();
        }
    }

    @Test
    public void testTurtleMarioCollision() throws FactoryException {
        Game game = factory.makeCorrectGame("testTurtleMarioCollision");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        Entity player = (Entity) game.getActivePlayer();
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

    @Test
    public void testQuestionBoxCollision() throws FactoryException {
        Game game = factory.makeCorrectGame("testMarioQuestionBox");
        testScreen.setGameScreen(game);
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        boolean questionBox = false;
        Entity player = (Entity) game.getActivePlayer();
        for(Entity entity : entities){
            if(entity.getId().equals("question")){
                questionBox = true;
            }
        }
        assertTrue(questionBox);
        questionBox = false;
        for (int i = 0; i < 5; i++) {
            game.RIGHT(player);
            game.updateLevel();
        }
        entities = (Collection<Entity>) game.getEntities();
        for(Entity entity : entities){
            if(entity.getId().equals("question")){
                questionBox = true;
            }
        }
        assertFalse(questionBox);
    }

    @Test
    public void testCoinGeneration() throws FactoryException {
        Game game = factory.makeCorrectGame("testMarioQuestionBox");
        testScreen.setGameScreen(game);
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        boolean coinGenerated = false;
        for(Entity entity : entities){
            if(entity.getId().equals("question")){
                entity.setHitpoints(0);
            }
        }
        game.updateLevel();
        entities = (Collection<Entity>) game.getEntities();
        for(Entity entity : entities){
            if(entity.getId().equals("coin")){
                coinGenerated = true;
            }
        }
        assertTrue(coinGenerated);
    }

}
