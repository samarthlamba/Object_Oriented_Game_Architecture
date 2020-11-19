package ooga.loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

/**
 * This class is used to generate the data for a random game. It is primarily intented for use by
 * the GameFactory class.
 */
public class RandomGameGenerator {

  private final ResourceBundle randomBundle;
  private final ResourceBundle gameConfigBundle = ResourceBundle.getBundle("GameConfig");
  private final String gameName;
  private final Random random;
  private final String defaultBlock;
  private final String emptyBlock;

  /**
   * Each game requires a new instance of RandomGameGenerator.
   *
   * @param gameName The name of the game to be generated. Must have a corresponding
   *                 Random%s.properties that specifies the type of objects that can be generated as
   *                 well as the weights of various objects
   * @param seed     the seed for the game generator.
   */
  public RandomGameGenerator(String gameName, long seed) {
    this.gameName = gameName;
    this.random = new Random(seed);
    this.randomBundle = ResourceBundle.getBundle(
        String.format(gameConfigBundle.getString("randomBundleName"), gameName));
    this.emptyBlock = gameConfigBundle.getString("emptyBlock");
    this.defaultBlock = randomBundle.getString("defaultBlock");
  }

  /**
   * This method builds a list of strings where every string array is a horizontal row of strings
   * representing game objects for the specified gameName.
   *
   * @return A List<String[]> where each element can be converted into a Game object.
   */
  public List<String[]> buildLevelData() {
    int chunkX = Integer.parseInt(gameConfigBundle.getString("chunkSizeX"));
    int chunkY = Integer.parseInt(gameConfigBundle.getString("chunkSizeY"));
    String[] title = new String[1];
    title[0] = String.format("%s,,", gameName);
    List<String[]> firstChunk = generateSpecialChunk("firstChunkBlocks", chunkX, chunkY);
    List<String[]> lastChunk = generateSpecialChunk("lastChunkBlocks", chunkX, chunkY);
    List<String[]> randomChunks = generateRandomChunks(chunkX, chunkY);
    List<String[]> fullLevelData = addFirstAndLastChunks(firstChunk, lastChunk, randomChunks);
    return fullLevelData;
  }

  private List<String[]> addFirstAndLastChunks(List<String[]> firstChunk, List<String[]> lastChunk,
      List<String[]> randomChunks) {
    int heightOfChunk = Integer.parseInt(gameConfigBundle.getString("chunkSizeY"));
    List<String[]> subSetOfRandomChunks = randomChunks.subList(0, heightOfChunk);
    subSetOfRandomChunks = combineChunksHorizontally(firstChunk, subSetOfRandomChunks);
    subSetOfRandomChunks = combineChunksHorizontally(subSetOfRandomChunks, lastChunk);
    for (int i = 0; i < heightOfChunk; i++) {
      randomChunks.set(i, subSetOfRandomChunks.get(i));
    }
    return randomChunks;
  }

  private List<String[]> generateRandomChunks(int chunkX, int chunkY) {
    int numXChunks = Integer.parseInt(randomBundle.getString("numberXChunks"));
    int numYChunks = Integer.parseInt(randomBundle.getString("numberYChunks"));
    List<String[]> fullRandomChunks = new ArrayList<>();
    for (int i = 0; i < numYChunks; i++) {
      List<String[]> rowOfChunks = generateRandomChunk(chunkX, chunkY);
      for (int j = 1; j < numXChunks; j++) {
        List<String[]> singleChunk = generateRandomChunk(chunkX, chunkY);
        rowOfChunks = combineChunksHorizontally(rowOfChunks, singleChunk);
      }
      fullRandomChunks.addAll(rowOfChunks);
    }
    return fullRandomChunks;
  }

  private List<String[]> combineChunksHorizontally(List<String[]> rowOfChunks,
      List<String[]> singleChunk) {
    List<String[]> combinedChunks = new ArrayList<>();
    for (int i = 0; i < rowOfChunks.size(); i++) {
      String[] previousChunksRow = rowOfChunks.get(i);
      int rowX = previousChunksRow.length;
      String[] singleChunkRow = singleChunk.get(i);
      int singleChunkX = singleChunkRow.length;
      String[] newRow = new String[rowX + singleChunkX];
      for (int j = 0; j < rowX; j++) {
        newRow[j] = previousChunksRow[j];
      }
      for (int k = 0; k < singleChunkX; k++) {
        newRow[k + rowX] = singleChunkRow[k];
      }
      combinedChunks.add(newRow);
    }
    return combinedChunks;
  }

  private List<String[]> generateRandomChunk(int chunkX, int chunkY) {
    List<String> obstacles = getOptions(Obstacle.class);
    List<String> entities = getOptions(Entity.class);
    List<String[]> chunkData = new ArrayList<>();
    for (int i = 0; i < chunkY; i++) {
      String[] rowData = new String[chunkX];
      for (int j = 0; j < chunkX; j++) {
        rowData[j] = generateRandomBlock(obstacles, entities);
      }
      chunkData.add(rowData);
    }
    return chunkData;
  }

  private List<String> getOptions(Class toGenerate) {
    String path = toGenerate.getName();
    String allOptions = randomBundle.getString(path);
    List<String> optionsAsList = new ArrayList<>(Arrays.asList(allOptions.split(",")));
    return optionsAsList;
  }

  private String generateRandomBlock(List<String> obstacles, List<String> entities) {
    double obstacleChance = Double.parseDouble(gameConfigBundle.getString("obstaclePercent"));
    double entityChance = Double.parseDouble(gameConfigBundle.getString("entityPercent"));
    double randomDouble = random.nextDouble();
    String randomElement = emptyBlock;
    if (randomDouble < entityChance) {
      randomElement = pickRandom(entities);
    } else if (randomDouble < entityChance + obstacleChance) {
      randomElement = pickRandom(obstacles);
    }
    return randomElement;
  }

  private String pickRandom(List<String> choices) {
    double randomDouble = random.nextDouble();
    double accumulatedProb = 0.0;
    for (String option : choices) {
      double probOfOption = Double.parseDouble(randomBundle.getString(option));
      accumulatedProb += probOfOption;
      if (randomDouble < accumulatedProb) {
        return option;
      }
    }
    return emptyBlock;
  }

  private List<String[]> generateSpecialChunk(String blocksToGenerate, int chunkX, int chunkY) {
    List<String> specialBlocks = Arrays.asList(randomBundle.getString(blocksToGenerate).split(","));
    List<String[]> chunkData = new ArrayList<>();
    for (int i = 0; i < chunkY - 2; i++) {
      String[] rowData = new String[chunkX];
      for (int j = 0; j < chunkX; j++) {
        rowData[j] = emptyBlock;
      }
      chunkData.add(rowData);
    }
    chunkData.add(generateOneOfEach(specialBlocks, chunkX));
    chunkData.add(generateFloor(chunkX));
    return chunkData;
  }

  private String[] generateOneOfEach(List<String> blocksToGenerate, int chunkX) {
    String[] row = new String[chunkX];
    for (int i = 0; i < blocksToGenerate.size(); i++) {
      row[i] = blocksToGenerate.get(i);
    }
    for (int j = blocksToGenerate.size(); j < chunkX; j++) {
      row[j] = emptyBlock;
    }
    return row;
  }

  private String[] generateFloor(int chunkX) {
    String[] floor = new String[chunkX];
    for (int i = 0; i < chunkX; i++) {
      floor[i] = defaultBlock;
    }
    return floor;
  }

}
