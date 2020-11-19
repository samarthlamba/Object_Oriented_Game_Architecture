package ooga.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import ooga.engine.games.beans.GameBean;

/**
 * This class builds GameBeans for a specific game and file location GameBeans are used to feed
 * constants in property files into a Game
 */
public class GameBeanFactory {

  private static final String BEAN_LOCATION = "ooga.engine.games.beans.%sBean";
  private static final String DEFAULT_BUNDLE = "Default%sValues";

  /**
   * @param gameType     The name of the game, i.e. Mario, Vikings, Metroid
   * @param fileLocation The file location of this bean, which should correspond to the name of the
   *                     level it will be used for
   * @param <T>          The implementation of game bean corresponding to class, i.e. MarioBean,
   *                     VikingsBean, etc.
   * @return a GameBean from the specified FileLocation and for the specfied GameType. Will use
   * default bean if not present
   */
  public <T extends GameBean> T makeGameBean(String gameType, String fileLocation)
      throws FactoryException {
    ResourceBundle bundle = getBundle(gameType, fileLocation);
    String classPath = String.format(BEAN_LOCATION, gameType);
    try {
      Class c = Class.forName(classPath);
      Constructor constr = c.getConstructor();
      T bean = (T) constr.newInstance();
      for (String each : bundle.keySet()) {
        String methodName = String.format("set%s", each);
        String value = bundle.getString(each);
        Method setterForEach = bean.getClass().getMethod(methodName, String.class);
        setterForEach.invoke(bean, value);
      }
      return bean;
    } catch (Exception e) {
      throw new FactoryException("Error making Bean", e);
    }
  }

  private ResourceBundle getBundle(String gameType, String fileLocation) {
    ResourceBundle bundle;
    try {
      bundle = ResourceBundle.getBundle(fileLocation);
    } catch (MissingResourceException m) {
      bundle = ResourceBundle.getBundle(String.format(DEFAULT_BUNDLE, gameType));
    }
    return bundle;
  }

}
