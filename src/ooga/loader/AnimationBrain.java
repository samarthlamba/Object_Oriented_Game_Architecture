package ooga.loader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import ooga.view.Animation;
import ooga.view.AnimationState;

public class AnimationBrain {
  private static final String BUNDLE_LOCATION = "%sAnimations";
  private static final List<AnimationState> ANIMATION_STATE_LIST = Arrays.asList(AnimationState.values());
  private final ResourceBundle entityBundle;
  private Map<AnimationState, Integer> xOffsetMap;
  private Map<AnimationState, Integer> yOffsetMap;
  private Map<AnimationState,Integer> lengthMap;
  private Image spriteSheet;
  private int framesPerRow;
  private int framesPerColumn;

  public AnimationBrain(String entityName) {
    try {
      entityBundle = ResourceBundle.getBundle(String.format(BUNDLE_LOCATION, entityName));
      findImage();
      findFramesPerRow();
      findFramerPerColumn();
      buildXOffsetMap();
      buildYOffsetMap();
      buildLengthMap();
    } catch (Exception e) {
      throw new FactoryException(String.format("Cannot build AnimationBrain for entity %s",entityName),e);
    }
  }

  private void findFramerPerColumn() {
    framesPerColumn = lookupIntInBundle("framesPerColumn");
  }

  private void findFramesPerRow() {
    framesPerRow = lookupIntInBundle("framesPerRow");
  }

  private void findImage() {
    String url = entityBundle.getString("imageLocation");
    spriteSheet = new Image(url);
  }

  private void buildLengthMap() {
    lengthMap = buildMapForAttribute("Length");
  }


  private void buildYOffsetMap() {
    xOffsetMap = buildMapForAttribute("XOffset");
  }

  private void buildXOffsetMap() {
    yOffsetMap = buildMapForAttribute("YOffset");
  }

  private Map<AnimationState, Integer> buildMapForAttribute(String attribute) {
    Map<AnimationState,Integer> attributeMap = new HashMap<>();
    for(AnimationState state : ANIMATION_STATE_LIST) {
      String key = String.format("%s%s",state.toString().toLowerCase(),attribute);
      Integer valueAsInt = lookupIntInBundle(key);
      attributeMap.put(state,valueAsInt);
    }
    return attributeMap;
  }

  private Integer lookupIntInBundle(String stringKey) {
    String value = entityBundle.getString(stringKey);
    Integer intValue = Integer.valueOf(value);
    return intValue;
  }


  public Image getImage() {
    return spriteSheet;
  }

  public int getFramesPerRow() {
    return framesPerRow;
  }

  public int getFramesPerColumn() {
    return framesPerColumn;
  }

  public Map<AnimationState, Integer> getXOffsetMap() {
    return Map.copyOf(xOffsetMap);
  }

  public Map<AnimationState, Integer> getYOffsetMap() {
    return Map.copyOf(yOffsetMap);
  }

  public Map<AnimationState, Integer> getLengthMap() {
    return Map.copyOf(lengthMap);
  }

}
