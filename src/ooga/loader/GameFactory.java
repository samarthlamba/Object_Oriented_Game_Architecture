package ooga.loader;

import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import ooga.engine.entities.Entity;
import ooga.engine.entities.player.Player;
import ooga.engine.games.Game;
import ooga.engine.games.beans.GameBean;
import ooga.engine.obstacles.Obstacle;

/**
 * This class is ued to generate Games from either a file location or to generate a random game.
 */
public class GameFactory {

  private static final String BUNDLE_NAME = "GameConfig";
  private final ResourceBundle gameConfigBundle;
  private final double frameRate;
  private GameBeanFactory beanMaker = new GameBeanFactory();
  private ResourceBundle gameBundle;
  private String gameName;

  public GameFactory() {
    this.gameConfigBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    this.frameRate = Double.parseDouble(gameConfigBundle.getString("framerate"));
  }

  /**
   * Builds a level from a specified file location
   *
   * @param fileLocation the file to build a level from. CSV must exist, bean is optional
   * @return the correct implementation of game specified in the .csv file location
   */
  public Game makeCorrectGame(String fileLocation) throws FactoryException {
    try {
      List<String[]> levelData = readLevelData(fileLocation);
      if (levelData.isEmpty()) {
        throw new FactoryException("Empty file");
      }
      gameName = levelData.get(0)[0];
      return makeGameFromLevelData(levelData, gameName, fileLocation);
    } catch (Exception e) {
      throw new FactoryException(
          String.format("Unable to build game from %s: %s", fileLocation, e.getMessage()), e);
    }
  }

  /**
   * Generates a random game from the game name. Must have a Random%s.properties file to generate.
   *
   * @param gameName The name of the game to generate a random level for
   * @return a large randomly generated implementation of the specified game
   */
  public Game makeRandomGame(String gameName) throws FactoryException {
    return makeRandomGame(gameName, new Random().nextLong());
  }

  /**
   * Used to generate a random game from a specific seed. Must have a Random%s.properties file to
   * generate.
   *
   * @param gameName The name of the game to generate a random level for.
   * @param seed     The seed to use for random generation
   * @return a large generated implementation of the specified game from specified seed
   */
  public Game makeRandomGame(String gameName, long seed) throws FactoryException {
    this.gameName = gameName;
    try {
      RandomGameGenerator randomGameGenerator = new RandomGameGenerator(gameName, seed);
      List<String[]> fullLevelData = randomGameGenerator.buildLevelData();
      return makeGameFromLevelData(fullLevelData, gameName, "Default");
    } catch (Exception e) {
      throw new FactoryException(String.format("Error building random level for game %s", gameName),
          e);
    }
  }

  private Game makeGameFromLevelData(List<String[]> levelData, String gameName,
      String beanFileLocation) throws Exception {
    gameBundle = ResourceBundle.getBundle(
        String.format(gameConfigBundle.getString("gameBundleName"), gameName));
    Player player = findPlayer(levelData);
    Collection<Obstacle> obstacles = getObjectsForGameOfType(levelData, Obstacle.class);
    Collection<Entity> entities = getObjectsForGameOfType(levelData, Entity.class);
    entities.add(player);
    GameBean gameBean = beanMaker.makeGameBean(gameName, beanFileLocation);
    Class c = Class.forName(gameBundle.getString("Game"));
    Constructor constr = c
        .getDeclaredConstructor(Player.class, Collection.class, Collection.class, double.class,
            gameBean.getClass());
    return (Game) constr.newInstance(player, obstacles, entities, 1 / frameRate, gameBean);
  }


  private Player findPlayer(List<String[]> levelData) throws FactoryException {
    Collection<Player> playerSet = getObjectsForGameOfType(levelData, Player.class);
    Optional<Player> potentialFirstPlayer = playerSet.stream().findFirst();
    if (potentialFirstPlayer.isEmpty()) {
      throw new FactoryException("No player found");
    } else {
      return potentialFirstPlayer.get();
    }
  }

  private <T> Collection<T> getObjectsForGameOfType(List<String[]> levelData,
      Class<T> objectToParse)
      throws FactoryException {
    String[] classStrings = gameBundle.getString(objectToParse.getName()).split(",");
    Collection<T> objectsInData = new ArrayList<>();
    int i = 0;
    for (String[] each : levelData) {
      Collection<T> objectsForRow = parseRowForClass(Arrays.asList(each), classStrings, i);
      objectsInData.addAll(objectsForRow);
      i++;
    }
    return objectsInData;
  }

  private <T> Collection<T> parseRowForClass(List<String> each, String[] classStrings,
      int rowNumber)
      throws FactoryException {
    List<String> classStringsAsList = Arrays.asList(classStrings);
    Collection<T> objectsInRow = new ArrayList<>();
    int j = 0;
    for (String every : each) {
      if (classStringsAsList.contains(every)) {
        GameObjectFactory<T> factory = new GameObjectFactory<>(gameName);
        T everyAsObject = factory.makeGameObject(every, rowNumber, j);
        objectsInRow.add(everyAsObject);
      }
      j++;
    }
    return objectsInRow;
  }

  private List<String[]> readLevelData(String fileLocation) throws FactoryException {
    String csvFileLocation = fileLocation + ".csv";
    try {
      InputStream dataStream = this.getClass().getClassLoader()
          .getResourceAsStream(csvFileLocation);
      CSVReader csvReader = new CSVReader(new InputStreamReader(dataStream));
      return csvReader.readAll();
    } catch (Exception e) {
      throw new FactoryException(String.format("Could not find file with name %s", csvFileLocation),
          e);
    }
  }
}
