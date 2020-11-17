package ooga.view;

import static ooga.view.AnimationState.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class AnimationStateTest {
  private static final List<AnimationState> ANIMATION_STATE_LIST = List.of(WALK,JUMP,STAND,SPECIAL);
  private static final List<String> ANIMATION_STRINGS = List.of("WALK","JUMP","STAND","SPECIAL");

  @Test
  public void testToStrings() {
    for(AnimationState state : ANIMATION_STATE_LIST) {
      String relevantString = ANIMATION_STRINGS.get(ANIMATION_STATE_LIST.indexOf(state));
      assertEquals(relevantString,state.toString());
    }
  }

  @Test
  public void testValueOf() {
    for(String relevantString : ANIMATION_STRINGS) {
      AnimationState state = ANIMATION_STATE_LIST.get(ANIMATION_STRINGS.indexOf(relevantString));
      assertEquals(state,AnimationState.valueOf(relevantString));
    }
  }

}
