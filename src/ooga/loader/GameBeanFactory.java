package ooga.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import ooga.engine.games.beans.GameBean;

public class GameBeanFactory {
  private static final String BEAN_LOCATION = "ooga.engine.games.beans.%sBean";
  private static final String DEFAULT_BUNDLE = "Default%sValues";

  public <T extends GameBean>  T makeGameBean(String gameType, String fileLocation) {
    ResourceBundle bundle = getBundle(gameType,fileLocation);
    String classPath = String.format(BEAN_LOCATION,gameType);
    try {
      Class c = Class.forName(classPath);
      Constructor constr = c.getConstructor();
      T bean = (T) constr.newInstance();
      for(String each : bundle.keySet()) {
        String methodName = String.format("set%s",each);
        String value = bundle.getString(each);
        Method setterForEach = bean.getClass().getMethod(methodName,String.class);
        setterForEach.invoke(bean,value);
      }
      return bean;
    } catch(Exception e) {
      throw new FactoryException("Error making Bean",e);
    }
  }

  private ResourceBundle getBundle(String gameType, String fileLocation) {
    ResourceBundle bundle;
    try {
      bundle = ResourceBundle.getBundle(fileLocation);
    } catch (MissingResourceException m) {
      bundle = ResourceBundle.getBundle(String.format(DEFAULT_BUNDLE,gameType));
    }
    return bundle;
  }

}
