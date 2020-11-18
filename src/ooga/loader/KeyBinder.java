package ooga.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.input.KeyCode;

/**
 * This class can be used to rebind keys at runtime.
 */
public class KeyBinder {
  private static final String DEFAULT_BUNDLE_LOCATION = "DefaultKeys";
  private static final String PATH_TO_KEYBINDINGS = "src/resources/KeyBindings.properties";
  private static final String BINDABLE_BUNDLE_LOCATION = "KeyBindings";
  private final Map<String, KeyCode> methodKeyMap;

  private final Map<KeyCode, String> keyMethodMap;
  private ResourceBundle keyBundle;

  public KeyBinder(){
    keyBundle = ResourceBundle.getBundle(BINDABLE_BUNDLE_LOCATION);
    methodKeyMap = new HashMap<>();
    keyMethodMap = new HashMap<>();
    initializeMaps();
  }

  /**
   * Binds a specific button to a string corresponding to a specific action.
   * @param button The Button to press to invoke the action
   * @param actionToBindTo a string corresponding to the method that should be invoked when button is pressed
   */
  public void setBinding(KeyCode button, String actionToBindTo) {
    if(keyMethodMap.keySet().contains(button)) {
      String otherAction = keyMethodMap.get(button);
      throw new KeyBindingException(String.format("That button is bound to %s already",otherAction));
    }
    if(!methodKeyMap.containsKey(actionToBindTo)) {
      throw new KeyBindingException(String.format("Action %s does not exist",actionToBindTo));
    }
    removeOldBinding(actionToBindTo);
    keyMethodMap.put(button,actionToBindTo);
    methodKeyMap.put(actionToBindTo,button);
  }

  /**
   * Sets the internally tracked keybinings map to the default values.
   */
  public void setToDefault(){
    keyBundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_LOCATION);
    initializeMaps();
    keyBundle = ResourceBundle.getBundle(BINDABLE_BUNDLE_LOCATION);
  }

  /**
   * Used to get the map from method names to key codes
   * @return a map whose keys are strings representing method names and whose value are KeyCode buttons
   */
  public Map<String,KeyCode> getMethodKeyMap() {
    return methodKeyMap;
  }

  /**
   * Used to get the map from keycodes to method names
   * @return a map whose keys are KeyCode buttons and whose value are strings representing method names
   */
  public Map<KeyCode, String> getKeyMethodMap() {
    return keyMethodMap;
  }

  /**
   * Saves the internally tracked keybindings map to a properties file
   * @throws IOException if there is any issue writing to the properties file
   */
  public void saveMap() throws IOException {
    File file = new File(PATH_TO_KEYBINDINGS);
    FileOutputStream stream = new FileOutputStream(file);
    Properties table = new Properties();
    for (KeyCode key : keyMethodMap.keySet()) {
      String keyString = key.toString();
      table.setProperty(keyString, keyMethodMap.get(key));
    }
    table.store(stream, null);
  }

  private void initializeMaps() {
    keyMethodMap.clear();
    methodKeyMap.clear();
    for(String keyString : keyBundle.keySet()) {
      KeyCode keyCode = KeyCode.valueOf(keyString);
      String methodName = keyBundle.getString(keyString);
      methodKeyMap.put(methodName,keyCode);
      keyMethodMap.put(keyCode,methodName);
    }
  }

  private void removeOldBinding(String actionToBindTo) {
    KeyCode previousKey = methodKeyMap.get(actionToBindTo);
    methodKeyMap.remove(actionToBindTo);
    keyMethodMap.remove(previousKey);
  }

}
