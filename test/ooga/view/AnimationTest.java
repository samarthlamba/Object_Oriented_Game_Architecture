package ooga.view;

import ooga.loader.AnimationBrain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimationTest {
AnimationBrain animationBrain = new AnimationBrain("MarioGame");

    int length = animationBrain.getLengthMap().get(AnimationState.WALK);
    int pos = animationBrain.getPositionOfFirstAnimationMap().get(AnimationState.WALK);
    int duration = animationBrain.getDurationMap().get(AnimationState.WALK);
    Animation animation = new Animation(animationBrain.getImage(),50.0,50.0,animationBrain.getxWhiteSpaceConstant(),animationBrain.getyWhiteSpaceConstant(),length,pos, animationBrain.getFramesPerRow(), animationBrain.getWidthActual(), animationBrain.getHeightActual(),duration);

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
        assertEquals(25, animation.getImage().getX());
        animation.setY(32);
        assertEquals(32, animation.getImage().getX());
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
    void setScaleX() {
    }

    @Test
    void setScaleY() {
    }

    @Test
    void interpolate() {
    }

    @Test
    void getImage() {
    }
}