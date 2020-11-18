package ooga.loader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import ooga.view.AnimationState;

/**
 * This class controls the Animation class for a particular entity.
 */
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
  private int heightActual;
  private int widthActual;
  private Map<AnimationState,Integer> durationMap;

  public AnimationBrain(String entityName) throws FactoryException {
    try {
      entityBundle = ResourceBundle.getBundle(String.format(BUNDLE_LOCATION, entityName));
      findImage();
      findyWhiteSpaceConstant();
      findxWhiteSpaceConstant();
      buildPositionOfFirstAnimation();
      buildLengthMap();
      findFramesPerRow();
      findActualHeight();
      findActualWidth();
      buildDurationMap();
    } catch (Exception e) {
      throw new FactoryException(String.format("Cannot build AnimationBrain for entity %s",entityName),e);
    }
  }
  private void findActualHeight(){
    this.heightActual = lookupIntInBundle("height");
  }
  private void findActualWidth(){
    this.widthActual = lookupIntInBundle("width");
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

  private void buildDurationMap() {
    durationMap = buildMapForAttribute("Duration");
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

  /**
   * Used to get the entire sprite sheet for an entity
   * @return the Image of the full sprite sheet
   */
  public Image getImage() {
    return spriteSheet;
  }

  public int getHeightActual(){
    return this.heightActual;
  }

  public Map<AnimationState, Integer> getDurationMap(){
    return Map.copyOf(durationMap);
  }

  public int getWidthActual(){
    return this.widthActual;
  }

  /**
   * The white space constant in x is the number of pixels of white space between the end of one sprite and
   * the beginning of the next.
   * @return an int corresponding to the x white space constant
   */
  public int getxWhiteSpaceConstant() {
    return xWhiteSpaceConstant;
  }

  /**
   * How many sprites are in every row of the sheet
   * @return an int corresponding to how many sprites are in the sheet's rows
   */
  public int getFramesPerRow() {
    return framesPerRow;
  }

  /**
   * The white space constant in y is the number of pixels between the bottom of one sprite and the
   * top of the next.
   * @return an int corresponding to the y white space constant
   */
  public int getyWhiteSpaceConstant() {
    return yWhiteSpaceConstant;
  }

  /**
   * Copies the position map, which specifies the x,y positions for the first sprite of each AnimationState.
   * @return a map whose keys are the 4 AnimationStates and whose values are their sprites first positions
   */
  public Map<AnimationState, Integer> getPositionOfFirstAnimationMap() {
    return Map.copyOf(PositionOfFirstAnimationMap);
  }

  /**
   * Copies the length map, which specifies how many sprites comprise the animations for each AnimationState.
   * @return a map whose keys are the 4 AnimationStates and whose values are ints of the number of sprites
   * in those animations
   */
  public Map<AnimationState, Integer> getLengthMap() {
    return Map.copyOf(lengthMap);
  }

}
