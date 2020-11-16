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
  private final Map<AnimationState, Integer> xOffsetMap;
  private final Map<AnimationState, Integer> yOffsetMap;
  private final int framesPerRow;
  private final int framesPerColumn;
  private Animation jump;
  private Animation walk;
  private Animation special;
  private Animation stand;
  private Boolean currentFacing;
  private Animation currentAnimation;

  public FiniteStateMachineAnimation(Entity entity, AnimationBrain animationBrain){
    this.entity = entity;
    this.animationBrain = animationBrain;
    this.spriteSheet=  animationBrain.getImage();
    this.lengthMap = animationBrain.getLengthMap();
    this.xOffsetMap = animationBrain.getXOffsetMap();
    this.yOffsetMap = animationBrain.getYOffsetMap();
    this.framesPerRow = animationBrain.getFramesPerRow();
    this.framesPerColumn = animationBrain.getFramesPerColumn();
    this.currentFacing = entity.getFacing();
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
    int xOffset = xOffsetMap.get(state);
    int yOffset = yOffsetMap.get(state);
    return new Animation(spriteSheet,entity.getWidth(),entity.getHeight(),xOffset,yOffset,length,framesPerRow, framesPerColumn);
  }
  private void changeAnimationDirection(){
    this.jump.swapDirection();
    this.walk.swapDirection();
    this.special.swapDirection();
    this.stand.swapDirection();
  }
  public void update(){
    currentAnimation.getImage().setX(entity.getCenterX());
    currentAnimation.getImage().setY(entity.getMaxY());
    getFacing();
    if(checkIfJumping() && currentAnimation != jump){
      this.currentAnimation = this.jump;
      currentAnimation.setCycleCount(Animation.INDEFINITE);
      currentAnimation.play();
      System.out.println("jump");
      System.out.println(currentAnimation.getImage().getViewport());
    }
    else if (checkIfMoving() && currentAnimation != walk){
      currentAnimation = this.walk;
      currentAnimation.setCycleCount(Animation.INDEFINITE);
      currentAnimation.play();
      System.out.println("move");
    }
    else if (this.checkIfDoingSpecialMove() && currentAnimation!= special){
      currentAnimation = this.special;
      currentAnimation.setCycleCount(Animation.INDEFINITE);
      currentAnimation.play();
      System.out.println("special");
    }
    else{
      currentAnimation = this.stand;
      currentAnimation.setCycleCount(Animation.INDEFINITE);
      currentAnimation.play();
      System.out.println("stand");
    }
  }
  public void getFacing(){
    if (entity.getFacing() != currentFacing){
      currentFacing = entity.getFacing();
      changeAnimationDirection();
    }
  }
  private Boolean checkIfJumping(){
    return entity.isJump();
  }

  public Animation getCurrentAnimation(){
    return this.currentAnimation;
  }

  private Boolean checkIfMoving(){
    if(entity.getPreviousY() == entity.getMaxY() && entity.getPreviousX() != entity.getCenterX()){
      return true;
    }
    return false;
  }

  private Boolean checkIfDoingSpecialMove(){
    return false;
  }






}
