import javafx.scene.Node;
import ooga.engine.entities.MovableBounds;
import ooga.engine.games.GamePlay;
import ooga.engine.entities.Entity;
import ooga.engine.games.Collideable;
import ooga.engine.obstacles.Obstacle;


import java.util.Collection;
import java.util.Collections;

//Provides a basic use case for a GamePlay, an object that runs a a playable game.
//use case: Calculate the position of entities in the game in a single frame
public class NewMarioGame implements GamePlay {
  private Collection<Entity> entities;
  private Collection<Obstacle> obstacles;

  //Simply moves each entity 5 to the right if it is not colliding with any obstacle
  public void updateLevel() {
    for(Entity each : entities) {
      for(Obstacle block : obstacles) {
        if(!block.hasCollided((Node) each)) {
          each.setCenterX(each.getCenterX()+5);
        }
      }
    }
  }

  //Gets an unmodifiable collection of all obstacles
  public Collection<Collideable> getBackground() {
    return Collections.unmodifiableCollection(obstacles);

  }

  //Gets an unmodifiable collection of all entitites
  public MovableBounds getEntities() {
    return Collections.unmodifiableCollection(entitites);
  }
}