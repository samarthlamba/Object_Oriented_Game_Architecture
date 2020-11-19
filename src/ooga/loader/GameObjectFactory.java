package ooga.loader;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

/**
 * This class is used to build a specific implementation of a Game object from a single string in a
 * file. It is intended to be use primarily by the GameFactory.
 *
 * @param <T> The Class of the GameObject to be created (Or any superclass)
 */
public class GameObjectFactory<T> {

  private static final String BUNDLE_LOCATION = "%sClasses";
  private final int width;
  private final int height;
  private ResourceBundle gameBundle;

  public GameObjectFactory(String game) {
    this.gameBundle = ResourceBundle.getBundle(String.format(BUNDLE_LOCATION, game));
    width = Integer.parseInt(gameBundle.getString("width"));
    height = Integer.parseInt(gameBundle.getString("height"));
  }

  /**
   * Makes the correct object from the specified symbol for a specific row, column in the CSV grid.
   *
   * @param symbolToConvert the symbol to convert into a Game object
   * @param rowNumber       the row that the object should be generated in
   * @param columnNumber    the column that the object should be generated in
   * @return the correct imlementation of that symbol as specified in the gameBundle.
   */
  public T makeGameObject(String symbolToConvert, int rowNumber, int columnNumber)
      throws FactoryException {
    return makeGameObject(symbolToConvert, (double) columnNumber * width,
        (double) rowNumber * height);
  }

  /**
   * Makes the correct object from the specified symbol for a specific row, column in the CSV grid.
   *
   * @param symbolToConvert the symbol to convert into a Game object
   * @param x               the x positionthat the object should be generated in
   * @param y               the y position that the object should be generated in
   * @return the correct imlementation of that symbol as specified in the gameBundle.
   */
  public T makeGameObject(String symbolToConvert, double x, double y) throws FactoryException {
    try {
      Class c = Class.forName(gameBundle.getString(symbolToConvert));
      Constructor constr = c
          .getDeclaredConstructor(int.class, int.class, double.class, double.class);
      return (T) constr.newInstance(width, height, x, y);
    } catch (Exception e) {
      throw new FactoryException(
          String.format("Symbol %s not present in this game", symbolToConvert), e);
    }
  }

}
