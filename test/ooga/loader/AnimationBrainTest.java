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
  public void setup() {
    mario = new AnimationBrain("Mario");
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
    assertEquals(4,lengthMap.get(WALK));
  }

  @Test
  public void testXOffsetsCorrect() {
    Map<AnimationState,Integer> xMap = mario.getXOffsetMap();
    assertEquals(0,xMap.get(STAND));
    assertEquals(0,xMap.get(SPECIAL));
    assertEquals(4,xMap.get(JUMP));
    assertEquals(1,xMap.get(WALK));
  }

  @Test
  public void testYOffsetsCorrect() {
    Map<AnimationState,Integer> yMap = mario.getYOffsetMap();
    assertEquals(0,yMap.get(STAND));
    assertEquals(0,yMap.get(SPECIAL));
    assertEquals(0,yMap.get(JUMP));
    assertEquals(0,yMap.get(WALK));
  }

  @Test
  public void testFramesPerRowCorrect() {
    int framesPerRow = mario.getFramesPerRow();
    assertEquals(6,framesPerRow);

  }

  @Test
  public void testFramesPerColumnCorrect() {
    int framesPerColumn = mario.getFramesPerColumn();
    assertEquals(2,framesPerColumn);
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
