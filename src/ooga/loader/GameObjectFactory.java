package ooga.loader;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

public class GameObjectFactory<T> {
  private ResourceBundle gameBundle;
  private static final String BUNDLE_LOCATION = "%sClasses";

  public GameObjectFactory (String game) {
    this.gameBundle = ResourceBundle.getBundle(String.format(BUNDLE_LOCATION,game));
  }

  public <T> T makeGameObject(String every, int rowNumber, int columnNumber) {
    int width = Integer.parseInt(gameBundle.getString("width"));
    int height = Integer.parseInt(gameBundle.getString("height"));
    try {
      Class c = Class.forName(gameBundle.getString(every));
      Constructor constr = c.getDeclaredConstructor(int.class, int.class, double.class, double.class);
      return (T) constr.newInstance(width,height,width*columnNumber,height*rowNumber);
    } catch (Exception e) {
      throw new FactoryException(String.format("Symbol %s not present in this game",every),e);
    }
  }
}
