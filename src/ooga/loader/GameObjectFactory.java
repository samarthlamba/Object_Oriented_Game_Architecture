package ooga.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

public class GameObjectFactory<T> {
  private ResourceBundle gameBundle;
  private static final String BUNDLE_LOCATION = "%sClasses";
  private final int width;
  private final int height;

  public GameObjectFactory (String game) {
    this.gameBundle = ResourceBundle.getBundle(String.format(BUNDLE_LOCATION,game));
    width = Integer.parseInt(gameBundle.getString("width"));
    height = Integer.parseInt(gameBundle.getString("height"));
  }

  public T makeGameObject(String symbolToConvert, int rowNumber, int columnNumber) {
    return makeGameObject(symbolToConvert,(double)columnNumber*width, (double)rowNumber*height);
  }

  public T makeGameObject(String every, double x, double y) {
    try{
      Class c = Class.forName(gameBundle.getString(every));
      Constructor constr = c.getDeclaredConstructor(int.class, int.class, double.class, double.class);
      return (T) constr.newInstance(width,height,x,y);
    } catch (Exception e) {
      throw new FactoryException(String.format("Symbol %s not present in this game",every),e);
    }
  }

}
