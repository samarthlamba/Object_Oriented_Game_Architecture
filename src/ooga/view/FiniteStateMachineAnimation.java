package ooga.view;

import ooga.engine.entities.Entity;
import ooga.engine.games.beans.GameBean;


public class FiniteStateMachineAnimation {

    private final Entity entity;
    private final GameBean bean;
    private Animation jump;
    private Animation move;
    private Animation specialMove;
    public FiniteStateMachineAnimation(Entity entity, GameBean bean){
        this.entity = entity;
        this.bean = bean;
        initialize();

    }
//property file
    private void initialize(){
        jump = new Animation(bean.getJumpImage(), entity.getWidth(), entity.getHeight(), bean.xOffset(), bean.yOffset(), bean.length(), bean.framePerRow(), bean.framePerCol());
        move = new Animation(bean.getJumpImage(), entity.getWidth(), entity.getHeight(), bean.xOffset(), bean.yOffset(), bean.length(), bean.framePerRow(), bean.framePerCol());
        specialMove = new Animation(bean.getJumpImage(), entity.getWidth(), entity.getHeight(), bean.xOffset(), bean.yOffset(), bean.length(), bean.framePerRow(), bean.framePerCol());

    }
    public void update(){

    }

    public Boolean checkIfJumping(){

    }

    public Boolean checkIfMoving(){

    }

    public Boolean checkIfDoingSpecialMove(){

    }





}
