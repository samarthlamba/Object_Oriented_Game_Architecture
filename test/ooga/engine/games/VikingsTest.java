package ooga.engine.games;

import com.sun.source.tree.AssertTree;
import ooga.engine.entities.Entity;
import ooga.loader.GameFactory;
import ooga.util.DukeApplicationTest;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class VikingsTest extends DukeApplicationTest {

    private final GameFactory gameFactory = new GameFactory();

    @Test
    public void testPlayerSpecialAction(){
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
    public void testArrowGeneration(){
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
    public void testWaterfallCollision() {
        Game game = gameFactory.makeCorrectGame("testVikingsWaterfall");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        double initialPosition = 175;
        Entity entity = entities.iterator().next();
        assertEquals(initialPosition, entity.getCenterX());
        for(int i = 0; i < 100; i++){
            game.RIGHT(entity);
        }
        for(int i = 0; i < 1000; i++){
            game.updateLevel();
        }
        assertTrue(game.areEqualDouble(181, entity.getCenterX(), 0));
    }

    @Test
    public void testWaterfallPercolate(){
        Game game = gameFactory.makeCorrectGame("testVikingsWaterfall");
        Collection<Entity> entities = (Collection<Entity>) game.getEntities();
        Entity player = entities.iterator().next();
        player.setCenterX(150);
        player.setMaxY(50);
        game.updateLevel();
        try {
            game.playerAction();
            game.updateLevel();
        }
        catch (Exception e) {
            assertEquals("Entity not in Scene" ,e.getMessage());
        }

    }


}
