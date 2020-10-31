package ooga.loader;

import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import ooga.engine.games.Game;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class GameFactory {
  private static final String BUNDLE_NAME = "%sClasses";
  private ResourceBundle gameBundle;
  private String gameName;

  public Game makeCorrectGame(String fileLocation) {
    try {
      List<String[]> levelData = readLevelData(fileLocation);
      if(levelData.isEmpty()) {
        throw new FactoryException("Empty file");
      }
      gameName = levelData.get(0)[0];
      gameBundle = ResourceBundle.getBundle(String.format(BUNDLE_NAME,gameName));
      Collection<Obstacle> obstacles = getObjectsForGameOfType(levelData, Obstacle.class);
      Collection<Entity> entities = getObjectsForGameOfType(levelData, Entity.class);
      Class c = Class.forName(gameBundle.getString("Game"));
      Constructor constr = c.getDeclaredConstructor(Collection.class, Collection.class);
      return (Game) constr.newInstance(entities, obstacles);
    } catch (Exception e) {
      throw new FactoryException(String.format("Unable to build game from %s: %s",fileLocation,e.getMessage()),e);
    }
  }

  private <T> Collection<T> getObjectsForGameOfType(List<String[]> levelData,Class<T> objectToParse) {
    String[] classStrings = gameBundle.getString(objectToParse.getName()).split(",");
    Collection<T> objectsInData = new ArrayList<>();
    for(String[] each: levelData) {
      Collection<T> objectsForRow = parseRowForClass(Arrays.asList(each),classStrings, levelData.indexOf(each));
      objectsInData.addAll(objectsForRow);
    }
    return objectsInData;
  }

  private <T> Collection<T> parseRowForClass(List<String> each, String[] classStrings, int rowNumber) {
    List<String> classStringsAsList = Arrays.asList(classStrings);
    Collection<T> objectsInRow = new ArrayList<>();
    for(String every : each) {
      if(classStringsAsList.contains(every)) {
        GameObjectFactory<T> factory = new GameObjectFactory<>(gameName);
        T everyAsObject = factory.makeGameObject(every,rowNumber,each.indexOf(every));
        objectsInRow.add(everyAsObject);
      }
    }
    return objectsInRow;
  }

  private List<String[]> readLevelData(String fileName) {
    try {
      InputStream dataStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
      CSVReader csvReader = new CSVReader(new InputStreamReader(dataStream));
      return csvReader.readAll();
    } catch (Exception e) {
      throw new FactoryException(String.format("Could not find file with name %s",fileName),e);
    }
  }
}
