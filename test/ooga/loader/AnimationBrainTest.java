package ooga.loader;

import static ooga.view.AnimationState.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import ooga.util.DukeApplicationTest;
import ooga.view.AnimationState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnimationBrainTest extends DukeApplicationTest {
  private AnimationBrain mario;

  @BeforeEach
  public void setup() throws FactoryException {
    mario = new AnimationBrain("MarioGame");
  }

  @Test
  public void testImageCorrect() {
    assertNotNull(mario.getImage());
  }

  @Test
  public void testLengthsCorrect() {
    Map<AnimationState,Integer> lengthMap = mario.getLengthMap();
    assertEquals(1,lengthMap.get(STAND));
    assertEquals(1,lengthMap.get(SPECIAL));
    assertEquals(8,lengthMap.get(JUMP));
    assertEquals(3,lengthMap.get(WALK));
  }

  @Test
  public void testPositionOfFirstAnimationMap() {
    Map<AnimationState,Integer> yMap = mario.getPositionOfFirstAnimationMap();
    assertEquals(1,yMap.get(STAND));
    assertEquals(1,yMap.get(SPECIAL));
    assertEquals(5,yMap.get(JUMP));
    assertEquals(2,yMap.get(WALK));
  }

  @Test
  public void testFramesPerRowCorrect() {
    int framesPerRow = mario.getFramesPerRow();
    assertEquals(6,framesPerRow);

  }

  @Test
  public void testGetyWhiteSpaceConstant() {
    int framesPerColumn = mario.getyWhiteSpaceConstant();
    assertEquals(15,framesPerColumn);
  }

  @Test
  public void testGetxWhiteSpaceConstant() {
    int framesPerColumn = mario.getxWhiteSpaceConstant();
    assertEquals(17,framesPerColumn);
  }

  @Test
  public void testExceptionIsThrownOnBadEntityName() {
    try{
      new AnimationBrain("Luigi");
    } catch (Exception e) {
      assertTrue(e instanceof FactoryException);
      assertEquals("Cannot build AnimationBrain for entity Luigi",e.getMessage());
    }
  }

}


