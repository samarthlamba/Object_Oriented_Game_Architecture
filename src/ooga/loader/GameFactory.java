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
import java.util.ResourceBundle;

import ooga.engine.entities.player.Player;
import ooga.engine.games.Game;
import ooga.engine.games.beans.*;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class GameFactory {
  private static final String BUNDLE_NAME = "GameConfig";
  private final ResourceBundle gameConfigBundle;
  private GameBeanFactory beanMaker = new GameBeanFactory();
  private ResourceBundle gameBundle;
  private String gameName;
  private final double frameRate;

  public GameFactory() {
    this.gameConfigBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    this.frameRate = Double.parseDouble(gameConfigBundle.getString("framerate"));
  }

  public Game makeCorrectGame(String fileLocation) {
    try {
      List<String[]> levelData = readLevelData(fileLocation);
      if(levelData.isEmpty()) {
        throw new FactoryException("Empty file");
      }
      gameName = levelData.get(0)[0];
      gameBundle = ResourceBundle.getBundle(
          String.format(gameConfigBundle.getString("gameBundleName"),gameName));
      Player player = findPlayer(levelData);
      Collection<Obstacle> obstacles = getObjectsForGameOfType(levelData, Obstacle.class);
      Collection<Entity> entities = getObjectsForGameOfType(levelData, Entity.class);
      entities.add(player);
      GameBean gameBean = beanMaker.makeGameBean(gameName,fileLocation);
      Class c = Class.forName(gameBundle.getString("Game"));
      Constructor constr = c.getDeclaredConstructor(Player.class, Collection.class, Collection.class, double.class,gameBean.getClass());
      return (Game) constr.newInstance(player,obstacles, entities,1/frameRate,gameBean);
    } catch (Exception e) {
      throw new FactoryException(String.format("Unable to build game from %s: %s",fileLocation,e.getMessage()),e);
    }
  }

  private Player findPlayer(List<String[]> levelData) {
    Collection<Player> playerSet = getObjectsForGameOfType(levelData,Player.class);
    Optional<Player> potentialFirstPlayer = playerSet.stream().findFirst();
    if(potentialFirstPlayer.isEmpty()) {
      throw new FactoryException("No player found");
    }
    else{
      return potentialFirstPlayer.get();
    }
  }

  private <T> Collection<T> getObjectsForGameOfType(List<String[]> levelData,Class<T> objectToParse) {
    String[] classStrings = gameBundle.getString(objectToParse.getName()).split(",");
    Collection<T> objectsInData = new ArrayList<>();
    int i = 0;
    for(String[] each: levelData) {
      Collection<T> objectsForRow = parseRowForClass(Arrays.asList(each),classStrings, i);
      objectsInData.addAll(objectsForRow);
      i++;
    }
    return objectsInData;
  }

  private <T> Collection<T> parseRowForClass(List<String> each, String[] classStrings, int rowNumber) {
    List<String> classStringsAsList = Arrays.asList(classStrings);
    Collection<T> objectsInRow = new ArrayList<>();
    int j = 0;
    for(String every : each) {
      if(classStringsAsList.contains(every)) {
        GameObjectFactory<T> factory = new GameObjectFactory<>(gameName);
        T everyAsObject = factory.makeGameObject(every,rowNumber,j);
        objectsInRow.add(everyAsObject);
      }
      j++;
    }
    return objectsInRow;
  }

  private List<String[]> readLevelData(String fileLocation) {
    String csvFileLocation = fileLocation+".csv";
    try {
      InputStream dataStream = this.getClass().getClassLoader().getResourceAsStream(csvFileLocation);
      CSVReader csvReader = new CSVReader(new InputStreamReader(dataStream));
      return csvReader.readAll();
    } catch (Exception e) {
      throw new FactoryException(String.format("Could not find file with name %s",csvFileLocation),e);
    }
  }
}
