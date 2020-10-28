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
import ooga.engine.Games.Game;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class GameFactory {

  private static final String BUNDLE_NAME = "%sClasses";
  private ResourceBundle gameBundle;

  public Game getCorrectGame(String fileLocation) {
    try {
      List<String[]> levelData = readLevelData(fileLocation);
      if(levelData.isEmpty()) {
        throw new GameFactoryException("Empty file");
      }
      gameBundle = getBundleForGame(levelData.get(0));
      String game = gameBundle.getString("Game");
      Collection<Obstacle> obstacles = getObjectsForGame(levelData, Obstacle.class);
      Collection<Entity> entities = getObjectsForGame(levelData, Entity.class);
      Class c = Class.forName(game);
      Constructor constr = c.getDeclaredConstructor(Collection.class, Collection.class);
      return (Game) constr.newInstance(entities, obstacles);
    } catch (Exception e) {
      throw new GameFactoryException(String.format("Unable to build game from %s: %s",fileLocation,e.getMessage()),e);
    }
  }

  private <T> Collection<T> getObjectsForGame(List<String[]> levelData,Class<T> objectToParse) {
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
        T everyAsObject = convertStringToObject(every,rowNumber,each.indexOf(every));
        objectsInRow.add(everyAsObject);
      }
    }
    return objectsInRow;
  }

  private <T> T convertStringToObject(String every, int rowNumber, int columnNumber) {
    int width = Integer.parseInt(gameBundle.getString("width"));
    int height = Integer.parseInt(gameBundle.getString("height"));
    try {
      Class c = Class.forName(gameBundle.getString(every));
      Constructor constr = c.getDeclaredConstructor(int.class, int.class, double.class, double.class);
      return (T) constr.newInstance(width,height,width*columnNumber,height*rowNumber);
    } catch (Exception e) {
      throw new GameFactoryException(String.format("Symbol %s not present in this game",every),e);
    }
  }

  private ResourceBundle getBundleForGame(String[] strings) {
    ResourceBundle attemptedBundle = ResourceBundle.getBundle(String.format(BUNDLE_NAME,strings[0]));
    return attemptedBundle;
  }

  private List<String[]> readLevelData(String fileName) {
    try {
      InputStream dataStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
      CSVReader csvReader = new CSVReader(new InputStreamReader(dataStream));
      return csvReader.readAll();
    } catch (Exception e) {
      throw new GameFactoryException(String.format("Could not find file with name %s",fileName),e);
    }
  }
}
