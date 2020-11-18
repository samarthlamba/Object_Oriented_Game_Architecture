package ooga.view;

import static ooga.view.AnimationState.*;

import java.util.Map;
import javafx.scene.image.Image;
import ooga.engine.entities.Entity;
import ooga.loader.AnimationBrain;


public class FiniteStateMachineAnimation {
  private final Entity entity;
  //you may not actually need this field
  private final AnimationBrain animationBrain;
  private final Image spriteSheet;
  private final Map<AnimationState, Integer> lengthMap;
  private final Map<AnimationState, Integer> positionOfFirstAnimationMap;
  private final Map<AnimationState, Integer> durationMap;
  private final int framesPerRow;
  private final int framesPerColumn;
  private Animation jump;
  private Animation walk;
  private Animation special;
  private Animation stand;
  private Boolean currentFacing;
  private Animation currentAnimation;
  private final double initialHeight;
  private final double initialWidth;

  public FiniteStateMachineAnimation(Entity entity, AnimationBrain animationBrain){
    this.entity = entity;
    initialHeight = entity.getHeight();
    initialWidth = entity.getWidth();
    this.animationBrain = animationBrain;
    this.spriteSheet=  animationBrain.getImage();
    this.lengthMap = animationBrain.getLengthMap();
    this.positionOfFirstAnimationMap = animationBrain.getPositionOfFirstAnimationMap();
    this.framesPerRow = animationBrain.getFramesPerRow();
    this.framesPerColumn = animationBrain.getyWhiteSpaceConstant();
    this.currentFacing = entity.getFacing();
    this.durationMap = animationBrain.getDurationMap();
    initialize();
  }

  //property file
  private void initialize(){
    this.jump = getAnimationForState(JUMP);
    this.walk = getAnimationForState(WALK);
    this.special = getAnimationForState(SPECIAL);
    this.stand = getAnimationForState(STAND);
    currentAnimation = this.stand;

  }

  private Animation getAnimationForState(AnimationState state) {
    int length = lengthMap.get(state);
    int pos = positionOfFirstAnimationMap.get(state);
    int duration = durationMap.get(state);
    return new Animation(spriteSheet,entity.getWidth(),entity.getHeight(),animationBrain.getxWhiteSpaceConstant(),animationBrain.getyWhiteSpaceConstant(),length,pos, animationBrain.getFramesPerRow(), animationBrain.getWidthActual(), animationBrain.getHeightActual(),duration);
  }
  private void changeAnimationDirection(){
    this.jump.swapDirection();
    this.walk.swapDirection();
    this.special.swapDirection();
    this.stand.swapDirection();
  }
  public void update(){
    moveAndScale();
    getFacing();
    if (isSpecial()){
      setAnimation(this.special);
    }
    else if(isJumping()){
      setAnimation(this.jump);
    }
    else if (isMoving()){
      setAnimation(this.walk);
    }

    else{
      jump.stop();
      setAnimation(this.stand);
    }
  }

  private void setAnimation(Animation jump) {
    this.currentAnimation = jump;
    currentAnimation.setCycleCount(Animation.INDEFINITE);
    currentAnimation.play();
  }

  private void getFacing(){
    if (entity.getFacing() != currentFacing){
      currentFacing = entity.getFacing();
      changeAnimationDirection();
    }
  }

  private void moveAndScale(){

    currentAnimation.getImage().setX(entity.getCenterX()-entity.getWidth()/2);
    currentAnimation.getImage().setY(entity.getMaxY()-entity.getHeight());
    currentAnimation.scale(entity.getWidth(),entity.getHeight());


    /*
    currentAnimation.setScaleX(entity.getWidth()/initialWidth);
    currentAnimation.setScaleY(entity.getHeight()/initialHeight);

     */
  }
  private Boolean isJumping(){
    return entity.isJump();
  }

  public Animation getCurrentAnimation(){
    moveAndScale();
    return this.currentAnimation;
  }

  private Boolean isMoving(){
    if((int)entity.getPreviousY() == (int)entity.getMaxY() && (int)entity.getPreviousX() != (int)entity.getCenterX()){
      return true;
    }
    return false;
  }

  private Boolean isSpecial(){
   // System.out.println(entity.getSpecialAction());
    return entity.getSpecialAction();
  }






}

