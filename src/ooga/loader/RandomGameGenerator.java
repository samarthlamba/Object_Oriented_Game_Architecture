package ooga.loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import ooga.engine.entities.Entity;
import ooga.engine.entities.player.Player;
import ooga.engine.obstacles.Obstacle;

public class RandomGameGenerator {
  private final ResourceBundle randomBundle;
  private final ResourceBundle gameConfigBundle = ResourceBundle.getBundle("GameConfig");
  private final String gameName;
  private final Random random;
  private final String defaultBlock;
  private final String emptyBlock;


  public RandomGameGenerator(String gameName,long seed) {
    this.gameName = gameName;
    this.random = new Random(seed);
    this.randomBundle = ResourceBundle.getBundle(
        String.format(gameConfigBundle.getString("randomBundleName"), gameName));
    this.emptyBlock = gameConfigBundle.getString("emptyBlock");
    this.defaultBlock = randomBundle.getString("defaultBlock");
  }

  public List<String[]> buildLevelData() {
    int numberPlayers = Integer.parseInt(randomBundle.getString("numberPlayers"));
    int chunkX = Integer.parseInt(gameConfigBundle.getString("chunkSizeX"));
    int chunkY = Integer.parseInt(gameConfigBundle.getString("chunkSizeY"));
    String[] title = new String[1];
    title[0] = String.format("%s,,",gameName);
    List<String[]> firstChunk = generateFirstChunk(numberPlayers,chunkX, chunkY);
    List<String[]> lastChunk = generateLastChunk(chunkX,chunkY);
    List<String[]> randomChunks = generateRandomChunks(chunkX,chunkY);
    List<String[]> fullLevelData = addFirstAndLastChunks(firstChunk,lastChunk,randomChunks);
    return fullLevelData;
  }

  private List<String[]> addFirstAndLastChunks(List<String[]> firstChunk, List<String[]> lastChunk, List<String[]> randomChunks) {
    int heightOfChunk = Integer.parseInt(gameConfigBundle.getString("chunkSizeY"));
    List<String[]> subSetOfRandomChunks = randomChunks.subList(0,heightOfChunk);
    subSetOfRandomChunks = combineChunksHorizontally(firstChunk,subSetOfRandomChunks);
    subSetOfRandomChunks = combineChunksHorizontally(subSetOfRandomChunks,lastChunk);
    for(int i = 0; i < heightOfChunk; i++) {
      randomChunks.set(i,subSetOfRandomChunks.get(i));
    }
    return randomChunks;
  }

  private List<String[]> generateRandomChunks(int chunkX, int chunkY) {
    int numXChunks = Integer.parseInt(randomBundle.getString("numberXChunks"));
    int numYChunks = Integer.parseInt(randomBundle.getString("numberYChunks"));
    List<String[]> fullRandomChunks  = new ArrayList<>();
    for(int i = 0; i < numYChunks; i++){
      List<String[]> rowOfChunks = generateRandomChunk(chunkX,chunkY);
      for(int j = 1; j < numXChunks; j++) {
        List<String[]> singleChunk = generateRandomChunk(chunkX,chunkY);
        rowOfChunks = combineChunksHorizontally(rowOfChunks,singleChunk);
      }
      fullRandomChunks.addAll(rowOfChunks);
    }
    return fullRandomChunks;
  }

  private List<String[]> combineChunksHorizontally(List<String[]> rowOfChunks, List<String[]> singleChunk) {
    List<String[]> combinedChunks = new ArrayList<>();
    for(int i = 0; i < rowOfChunks.size(); i ++) {
      String[] previousChunksRow = rowOfChunks.get(i);
      int rowX = previousChunksRow.length;
      String[] singleChunkRow = singleChunk.get(i);
      int singleChunkX = singleChunkRow.length;
      String[] newRow = new String[rowX+singleChunkX];
      for(int j = 0; j < rowX; j++) {
        newRow[j] = previousChunksRow[j];
      }
      for(int k = 0; k < singleChunkX; k++){
        newRow[k+rowX] = singleChunkRow[k];
      }
      combinedChunks.add(newRow);
    }
    return combinedChunks;
  }

  private List<String[]> generateRandomChunk(int chunkX, int chunkY) {
    List<String> obstacles = getOptions(Obstacle.class);
    List<String> entities = getOptions(Entity.class);
    List<String[]> chunkData = new ArrayList<>();
    for(int i = 0; i < chunkY; i++) {
      String[] rowData = new String[chunkX];
      for(int j = 0; j < chunkX; j++) {
        rowData[j] = generateRandomBlock(obstacles,entities);
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
    String randomElement =   emptyBlock;
    if(randomDouble < entityChance) {
      randomElement = pickRandom(entities);
    }
    else if (randomDouble < entityChance+obstacleChance) {
      randomElement = pickRandom(obstacles);
    }
    return randomElement;
  }

  private String pickRandom(List<String> choices) {
    double randomDouble = random.nextDouble();
    double accumulatedProb = 0.0;
    for(String option : choices) {
      double probOfOption = Double.parseDouble(randomBundle.getString(option));
      accumulatedProb+=probOfOption;
      if(randomDouble < accumulatedProb) {
        return option;
      }
    }
    return emptyBlock;
  }

  private List<String[]> generateFirstChunk(int numberPlayers, int chunkX, int chunkY) {
    List<String[]> chunkData = new ArrayList<>();
    for(int i = 0; i < chunkY-2; i++) {
      String[] rowData = new String[chunkX];
      for(int j = 0; j < chunkX; j++) {
        rowData[j] = emptyBlock;
      }
      chunkData.add(rowData);
    }
    chunkData.add(generateCorrectNumPlayers(numberPlayers,chunkX));
    chunkData.add(generateFloor(chunkX));
    return chunkData;
  }

  private String[] generateCorrectNumPlayers(int numberPlayers, int chunkX) {
    String[] playerRow = new String[chunkX];
    String playerString = randomBundle.getString(Player.class.getName());
    for(int p = 0; p < numberPlayers; p++) {
      playerRow[p] = playerString;
    }
    return playerRow;
  }

  private String[] generateFloor(int chunkX) {
    String[] floor = new String[chunkX];
    for(int i = 0; i<chunkX; i++) {
      floor[i] = defaultBlock;
    }
    return floor;
  }

  private List<String[]> generateLastChunk(int chunkX, int chunkY) {
    List<String[]> chunkData = new ArrayList<>();
    String lastChunkBlock = randomBundle.getString("lastChunkBlock");
    for(int i = 0; i < chunkY-1; i++) {
      String[] rowData = new String[chunkX];
      for(int j = 0; j < chunkX; j++) {
        if(i == chunkY-2 && j == chunkX -2) {
          rowData[j] = lastChunkBlock;
        }
        else{
          rowData[j] = emptyBlock;
        }
      }
      chunkData.add(rowData);
    }
    chunkData.add(generateFloor(chunkX));
    return chunkData;
  }

}
