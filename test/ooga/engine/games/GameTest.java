package ooga.engine.games;

import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;
import ooga.loader.GameFactory;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class GameTest {
    private static final GameFactory factory = new GameFactory();

   @Test
   public void jumpTest() {
       Game game = factory.makeCorrectGame("ooga/resources/testJump.csv");
       Collection<Entity> entities = game.getEntities();
       Collection<Obstacle> obstacles = game.getBackground();
       for(Entity entity : entities){
           System.out.println(entity.getX());
       }
       game.updateEntity();
       System.out.println();
   }


}