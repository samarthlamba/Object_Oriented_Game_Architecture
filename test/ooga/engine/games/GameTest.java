package ooga.engine.games;

import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;
import ooga.loader.GameFactory;

import java.util.Collection;

class GameTest {
    private static final GameFactory factory = new GameFactory();
    private Game gameFromLoader = factory.makeCorrectGame("testFile.csv");
    private Collection<Entity> entitiesFromGame = gameFromLoader.getEntities();
    private Collection<Obstacle> obstaclesFromGame = gameFromLoader.getBackground();



}