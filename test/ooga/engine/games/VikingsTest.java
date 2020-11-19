package ooga.engine.games;

import javafx.animation.Timeline;
import javafx.stage.Stage;
import ooga.GameController;
import ooga.engine.entities.Entity;
import ooga.loader.FactoryException;
import ooga.loader.GameFactory;
import ooga.util.DukeApplicationTest;
import ooga.view.Display;
import ooga.view.screens.GamePlayScreen;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class VikingsTest extends DukeApplicationTest {
    private Game myGame;
    private Display myDisplay;
    private static final GameFactory gameFactory = new GameFactory();

    @Override
    public void start(Stage stage) {
        Timeline testTimeline = new Timeline();
        GameController testController = new GameController(stage,testTimeline,this::setMyGame);
        myDisplay = new Display(testController);
    }

    @Test
    public void testPlayerSpecialAction() throws FactoryException {
        Game game = gameFactory.makeCorrectGame("testVikingsPlayer");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double originalPosition = 0;
        for(Entity entity : entities) {
            if (entity.getId().equals("player")) {
                originalPosition = entity.getMaxY();
            }
        }
        game.playerAction();
        double newPosition = 0;
        for(Entity entity : entities) {
            if (entity.getId().equals("player")) {
               newPosition = entity.getMaxY();
            }
        }
        assertTrue(originalPosition != newPosition);
        game.playerAction();
        game.playerAction();
        for(Entity entity : entities) {
            if (entity.getId().equals("player")) {
                newPosition = entity.getMaxY();
            }
        }
        assertTrue(originalPosition == newPosition);
    }

    @Test
    public void testArrowGeneration() throws FactoryException {
        Game game = gameFactory.makeCorrectGame("testVikingsPlayer");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        boolean arrowGenerated = false;
        try {
            for (int i = 0; i < 200; i++) {
                game.updateLevel();
            }
        }
        catch(NullPointerException e){
            entities = (Collection<Entity>) game.getEntities();
            for(Entity entity : entities){
                if(entity.getId().equals("arrow")){
                    arrowGenerated = true;
                }
            }
            assertTrue(arrowGenerated);
        }
    }

    @Test
    public void testWaterfallCollision() throws FactoryException {
        Game game = gameFactory.makeCorrectGame("testVikingsWaterfall");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double initialPosition = 175;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        for(int i = 0; i < 100; i++){
            game.moveRight();
        }
        for(int i = 0; i < 1000; i++){
            game.updateLevel();
        }
        System.out.println(entity.getCenterX());
        assertTrue(game.areEqualDouble(175, entity.getCenterX(), 0));
    }

    @Test
    public void testWaterfallPercolate() throws FactoryException {
        Game game = gameFactory.makeCorrectGame("testVikingsWaterfall");
        javafxRun(() -> myDisplay.setGameDisplay(game));
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        Entity player = (Entity) game.getActivePlayer();
        boolean intersection = false;
        for(Entity entity : entities) {
            if (entity.getId().equals("waterfall")) {
                player.setCenterX(entity.getCenterX());
                player.setMaxY(entity.getMaxY());
            }
        }

        for(Entity entity : entities){
            if(entity.getId().equals("waterfall")){
                if(player.getBoundsInParent().intersects(entity.getBoundsInParent())){
                    intersection = true;
                }
            }
        }
        game.updateLevel();
        game.playerAction();
        game.updateLevel();
        assertTrue(intersection);
    }

    @Test
    public void testTimeScore() throws FactoryException {
        Game game = gameFactory.makeCorrectGame("testVikingsWaterfall");
        for(int i = 0; i < 1000; i++){
            game.updateLevel();

        }
        assertTrue(game.getPoints() != 0);
    }


    private void setMyGame(Game game) {
        myGame = game;
    }

}
