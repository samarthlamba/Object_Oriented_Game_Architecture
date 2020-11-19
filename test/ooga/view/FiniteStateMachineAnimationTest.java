package ooga.view;

import javafx.scene.image.ImageView;
import ooga.engine.entities.Entity;
import ooga.engine.entities.player.Mario;
import ooga.loader.AnimationBrain;
import ooga.loader.FactoryException;
import ooga.util.DukeApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FiniteStateMachineAnimationTest extends DukeApplicationTest {
  private AnimationBrain animationBrain;
  private Animation animation;
  private FiniteStateMachineAnimation fsm;
  private Entity mario;

  @BeforeEach
  public void setup() throws FactoryException {
    animationBrain = new AnimationBrain("MarioGame");

    int length = animationBrain.getLengthMap().get(AnimationState.WALK);
    int pos = animationBrain.getPositionOfFirstAnimationMap().get(AnimationState.WALK);
    int duration = animationBrain.getDurationMap().get(AnimationState.WALK);
    animation = new Animation(animationBrain.getImage(), 50.0, 50.0, animationBrain.getxWhiteSpaceConstant(), animationBrain.getyWhiteSpaceConstant(), length, pos, animationBrain.getFramesPerRow(), animationBrain.getWidthActual(), animationBrain.getHeightActual(), duration);
    mario = new Mario(50, 50, 50, 50);
    fsm = new FiniteStateMachineAnimation(mario, animationBrain);
  }
  @Test
  void update() {

    mario.setWidth(10);
    ImageView initialPic = fsm.getCurrentAnimation().getImage();
    mario.setJump(true);
    System.out.println(mario.getWidth());
    fsm.update();
    assertEquals(10, fsm.getCurrentAnimation().getImage().getFitWidth());
    mario.setJump(false);
    System.out.println(mario.getWidth());
    fsm.update();
    assertEquals(10, fsm.getCurrentAnimation().getImage().getFitWidth());
    mario.setPreviousY(mario.getMaxY());
    mario.setPreviousX(mario.getCenterX()+5);
    fsm.update();
    assertNotEquals(initialPic, fsm.getCurrentAnimation().getImage());
  }


  @Test
  void getCurrentAnimation() {
    assertEquals(fsm.getCurrentAnimation().getImage().getImage(), animation.getImage().getImage());
  }
}