package ooga.loader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import ooga.view.AnimationState;

public class AnimationBrain {
  private static final String BUNDLE_LOCATION = "%sAnimations";
  private static final List<AnimationState> ANIMATION_STATE_LIST = Arrays.asList(AnimationState.values());
  private final ResourceBundle entityBundle;
  private Map<AnimationState, Integer> PositionOfFirstAnimationMap;
  private Map<AnimationState,Integer> lengthMap;
  private Image spriteSheet;
  private int xWhiteSpaceConstant;
  private int yWhiteSpaceConstant;
  private int framesPerRow;

  public AnimationBrain(String entityName) {
    try {
      entityBundle = ResourceBundle.getBundle(String.format(BUNDLE_LOCATION, entityName));
      findImage();
      findyWhiteSpaceConstant();
      findxWhiteSpaceConstant();
      buildPositionOfFirstAnimation();
      buildLengthMap();
      findFramesPerRow();
    } catch (Exception e) {
      throw new FactoryException(String.format("Cannot build AnimationBrain for entity %s",entityName),e);
    }
  }

  private void findxWhiteSpaceConstant() {
    xWhiteSpaceConstant = lookupIntInBundle("xWhiteSpaceConstant");
  }
  private void findFramesPerRow() {
    framesPerRow = lookupIntInBundle("framesPerRow");
  }

  private void findyWhiteSpaceConstant() {
    yWhiteSpaceConstant = lookupIntInBundle("yWhiteSpaceConstant");
  }

  private void findImage() {
    String url = entityBundle.getString("imageLocation");
    spriteSheet = new Image(url);
  }

  private void buildLengthMap() {
    lengthMap = buildMapForAttribute("Length");
  }


  private void buildPositionOfFirstAnimation() {
    PositionOfFirstAnimationMap = buildMapForAttribute("PositionOfFirstAnimation");
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

  public int getxWhiteSpaceConstant() {
    return xWhiteSpaceConstant;
  }
  public int getFramesPerRow() {
    return framesPerRow;
  }
  public int getyWhiteSpaceConstant() {
    return yWhiteSpaceConstant;
  }

  public Map<AnimationState, Integer> getPositionOfFirstAnimationMap() {
    return Map.copyOf(PositionOfFirstAnimationMap);
  }

  public Map<AnimationState, Integer> getLengthMap() {
    return Map.copyOf(lengthMap);
  }

}
