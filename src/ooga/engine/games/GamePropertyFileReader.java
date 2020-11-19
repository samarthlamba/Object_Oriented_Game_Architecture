package ooga.engine.games;

import java.util.ArrayList;
import java.util.Collection;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class GamePropertyFileReader {

  private static final String DEFAULT_BUNDLE = "Default";
  private static final int METHOD_NAME_INDEX = 0;
  private static final int PARAMETER_NAME_INDEX = 1;
  private static final int METHOD_PARAMETERS_TO_SPLIT = 2;
  private ResourceBundle gameConfigBundle;

  public GamePropertyFileReader(String objectName) {
    try {
      this.gameConfigBundle = ResourceBundle.getBundle(objectName);
    } catch (MissingResourceException m) {
      this.gameConfigBundle = ResourceBundle.getBundle(String.format(DEFAULT_BUNDLE + objectName));
    }
  }

  public Collection<String> getMethods(String key) {
    Collection<String> methods = getContent(key, METHOD_NAME_INDEX);
    return methods;
  }

  private Collection<String> getContent(String key, int option) {

    Collection<String> methods = new ArrayList<>();
    String options = gameConfigBundle.getString(key);
    String[] split = options.split(":");
    for (String val : split) {
      methods.add(val.split(",", METHOD_PARAMETERS_TO_SPLIT)[option]);
    }
    return methods;
  }

  public Collection<String> getParameters(String key) {
    Collection<String> methods = getContent(key, PARAMETER_NAME_INDEX);
    return methods;
  }
}
