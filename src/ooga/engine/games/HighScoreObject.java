package ooga.engine.games;

import java.io.Serializable;

/**
 * Object to record high score
 */
public class HighScoreObject implements Serializable {

  private static final int ZERO = 0;
  private static final String COMMA = ",";
  private final int score;
  private final long time;

  public HighScoreObject(int score) {
    time = System.currentTimeMillis();
    this.score = score;
  }

  public HighScoreObject(int score, long time) {
    this.time = time;
    this.score = score;
  }

  /**
   * Converts inputted string to the high score objects. Inverse of the toString
   *
   * @param s String to convert
   * @return score object
   */
  public static HighScoreObject toHighScoreObject(String s) {
    String[] split = s.split(COMMA);
    if (split.length > 1) {
      try {
        return new HighScoreObject(Integer.parseInt(split[ZERO]), Long.parseLong(split[1]));
      } catch (Exception e) {
        return new HighScoreObject(ZERO, ZERO);
      }
    }
    return new HighScoreObject(Integer.parseInt(split[ZERO]), ZERO);
  }

  /**
   * Getter to get the score value
   *
   * @return returns int score value
   */
  public int getScore() {
    return score;
  }

  /**
   * getter to return the time associated with the score
   *
   * @return returns long time when the score happened
   */
  public long getTime() {
    return time;
  }

  /**
   * Compares to score objects based on score value and time
   *
   * @param obj HighScoreObject to compare against
   * @return true if the object is greater else false
   */
  public boolean greaterThan(HighScoreObject obj) {
      if (this.score > obj.getScore()) {
          return true;
      } else {
          return this.score == obj.getScore() && this.time > obj.getTime();
      }
  }

  /**
   * Converts score and time to string
   *
   * @return String fot the score and time separated by a comma
   */
  @Override
  public String toString() {
    return this.getScore() + COMMA + this.getTime();
  }

}
