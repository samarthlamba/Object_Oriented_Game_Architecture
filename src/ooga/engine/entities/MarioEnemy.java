package ooga.engine.entities;

public class MarioEnemy extends Enemy{

    public MarioEnemy(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        this.setVelocityX(-200);
    }

    public void update() {
        if(getHitpoints() < 0){
            status_Alive = false;
        }
    }
    }
