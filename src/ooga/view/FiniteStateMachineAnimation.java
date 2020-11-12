package ooga.view;

import ooga.engine.entities.Entity;

public abstract class FiniteStateMachineAnimation {

    private final Entity entity;
    public FiniteStateMachineAnimation(Entity entity){
        this.entity = entity;
    }

    public abstract void update();




}
