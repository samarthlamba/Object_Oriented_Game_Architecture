package ooga.engine;

import java.util.Collection;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

/**
 * This interface represents a "playable" game, one which tracks and updates the position of various
 * entites and obstacles within it and from which those entities and obstacles can be retrieved.
 */
public interface GamePlay {

  /**
   * This method updates the position and state of every object for a single unit time. It should move
   * moveables based on their speed, perform any state/physics calculations, etc.
   */
  void updateLevel();

  /**
   * This method can be called to get all of the obstalces that make up the background/stage of the
   * Game. Currently it returns a Collection<Obstacle> but once implemented it will return an ObstacleCollection
   * object that wraps a Collection and implements iterable, so that individual elements cannot be nulled
   * @return A collection of the obstacles making up the background of the level
   */
  Collection<Obstacle> getBackground();

  /**
   * This method can be called to get all of the entities moving in the Game.
   * Currently it returns a Collection<Entity> but once implemented it will return an Entityollection
   * object that wraps a Collection and implements iterable, so that individual elements cannot be nulled
   * @return A collection of the Entities moving in the level.
   */
  Collection<Entity> getEntities();
}
