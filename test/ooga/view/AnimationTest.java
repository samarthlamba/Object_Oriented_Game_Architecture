package ooga.view;

import javafx.geometry.Rectangle2D;
import ooga.loader.AnimationBrain;
import ooga.loader.FactoryException;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

class AnimationTest extends DukeApplicationTest {
  private AnimationBrain animationBrain;
  private Animation animation;

  @BeforeEach
  public void setup() throws FactoryException {
    animationBrain = new AnimationBrain("MarioGame");

    int length = animationBrain.getLengthMap().get(AnimationState.WALK);
    int pos = animationBrain.getPositionOfFirstAnimationMap().get(AnimationState.WALK);
    int duration = animationBrain.getDurationMap().get(AnimationState.WALK);
    animation = new Animation(animationBrain.getImage(), 50.0, 50.0, animationBrain.getxWhiteSpaceConstant(), animationBrain.getyWhiteSpaceConstant(), length, pos, animationBrain.getFramesPerRow(), animationBrain.getWidthActual(), animationBrain.getHeightActual(), duration);
  }
  @Test
  void setX() {
    animation.setX(21);
    assertEquals(21, animation.getImage().getX());
    animation.setX(31);
    assertEquals(31, animation.getImage().getX());
  }

  @Test
  void setY() {
    animation.setY(25);
    assertEquals(25, animation.getImage().getY());
    animation.setY(32);
    assertEquals(32, animation.getImage().getY());
  }

  @Test
  void swapDirection() {
    animation.swapDirection();
    assertEquals(-1, animation.getImage().getScaleX());
  }

  @Test
  void scale() {
    animation.scale(30, 30);
    assertEquals(30, animation.getImage().getFitHeight());
    assertEquals(30, animation.getImage().getFitWidth());
  }



  @Test
  void getImage() {
    animation.scale(31, 31);
    assertEquals(31, animation.getImage().getFitWidth());
  }
  @Test
  void interpolate() {
    Rectangle2D before = animation.getImage().getViewport();
    animation.interpolate(0.18);
    assertNotEquals(before, animation.getImage().getViewport());

  }

}