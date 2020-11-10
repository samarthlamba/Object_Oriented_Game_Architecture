package ooga.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KeyBinderTest {
  private static final ResourceBundle DEFAULT_KEY_BUNDLE = ResourceBundle.getBundle("DefaultKeys");
  private KeyBinder myBinder;
  private ResourceBundle bindableBundle;
  private Map<String,KeyCode> defaultMethodKeyCodeMap;
  private Map<KeyCode, String> defaultKeyCodeMethodMap;


  @BeforeEach
  public void setup() throws IOException {
    myBinder = new KeyBinder();
    myBinder.setToDefault();
    myBinder.saveMap();
    bindableBundle = ResourceBundle.getBundle("KeyBindings");
    defaultMethodKeyCodeMap = new HashMap<>();
    defaultKeyCodeMethodMap = new HashMap<>();
    for(String keyString : DEFAULT_KEY_BUNDLE.keySet()) {
      KeyCode key = KeyCode.valueOf(keyString);
      String methodName = DEFAULT_KEY_BUNDLE.getString(keyString);
      defaultKeyCodeMethodMap.put(key,methodName);
      defaultMethodKeyCodeMap.put(methodName,key);
    }
  }

  @Test
  public void testKeyBinderFindsDefaultMaps() {
    Map<String, KeyCode> methodKeyMap = myBinder.getMethodKeyMap();
    assertEquals(defaultMethodKeyCodeMap,methodKeyMap);
    Map<KeyCode,String> keyMethodMap = myBinder.getKeyMethodMap();
    assertEquals(defaultKeyCodeMethodMap,keyMethodMap);
  }

  @Test
  public void testSetBindingChangesMaps() {
    myBinder.setBinding(KeyCode.D,"moveLeft");
    Map<String, KeyCode> methodKeyMap = myBinder.getMethodKeyMap();
    Map<KeyCode,String> keyMethodMap = myBinder.getKeyMethodMap();
    assertEquals(KeyCode.D,methodKeyMap.get("moveLeft"));
    assertEquals("moveLeft",keyMethodMap.get(KeyCode.D));
    assertEquals(defaultKeyCodeMethodMap.size(),keyMethodMap.size());
    assertEquals(defaultMethodKeyCodeMap.size(),methodKeyMap.size());
  }

  @Test
  public void testSetBindingIfKeyAlreadyBoundThrowsException() {
    try {
      myBinder.setBinding(KeyCode.RIGHT,"moveLeft");
    } catch (Exception e) {
      assertTrue(e instanceof KeyBindingException);
      assertEquals("That button is bound to moveRight already",e.getMessage());
    }
  }

  @Test
  public void testSetBindingsIfBadMethodThowsException() {
    try{
      myBinder.setBinding(KeyCode.D,"dance");
    } catch (Exception e) {
      assertTrue(e instanceof KeyBindingException);
      assertEquals("Action dance does not exist",e.getMessage());
    }
  }

  @Test
  public void testSaveMapSavesChangesCorrectly() throws IOException {
    myBinder.setBinding(KeyCode.D,"moveUp");
    myBinder.saveMap();
    File location = new File("src/resources/KeyBindings.properties");
    Properties p = new Properties();
    p.load(new FileInputStream(location));
    assertEquals("moveUp",p.getProperty("D"));
  }

  @Test
  public void testKeyBinderResetsToDefault() {
    myBinder.setBinding(KeyCode.D, "moveLeft");
    assertNotEquals(defaultKeyCodeMethodMap, myBinder.getKeyMethodMap());
    assertNotEquals(defaultMethodKeyCodeMap, myBinder.getMethodKeyMap());
    myBinder.setToDefault();
    assertEquals(defaultKeyCodeMethodMap, myBinder.getKeyMethodMap());
    assertEquals(defaultMethodKeyCodeMap, myBinder.getMethodKeyMap());
  }

  @AfterAll
  public static void cleanup() throws IOException {
    KeyBinder binder = new KeyBinder();
    binder.setToDefault();
    binder.saveMap();
  }

}
