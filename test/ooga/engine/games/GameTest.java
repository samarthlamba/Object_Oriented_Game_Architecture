package ooga.engine.games;

import javafx.scene.input.KeyCode;
import ooga.engine.Games.Game;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;
import ooga.loader.GameFactory;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class GameTest {
    private static final GameFactory factory = new GameFactory();

   @Test
   public void jumpTest() {
       Game game = factory.makeCorrectGame("testJump.csv");
       Collection<Entity> entities = game.getEntities();
       Collection<Obstacle> obstacles = game.getBackground();
       for(Entity entity : entities){
           game.UP(entity);
       }
       game.updateEntity();
       System.out.println();
   }


}