package ooga.loader;

import ooga.engine.games.Game;

/**
 * This interface represents a Factory for a Game object that will convert a String fileLocation for
 * a game file CSV and use it to build some implementation of the abstract Game
 */
public interface Loader {

  /**
   * This method is called to get the correct implementation of the Abstract game class for the string given
   * @param fileLocation a string corresponding to the location of a CSV file representing a level of a game
   * @return some concrete implementation of the abstract Game class based on the header of the CSV
   * @throws FileNotFoundException a runtime exception that informs the front end that the file was
   * not found and populates an empty game to return to the Driver
   */
  Game getCorrectGame(String fileLocation) throws FileNotFoundException;
}
