package ooga.engine.entities;

public class MarioEnemy extends Enemy{

    public MarioEnemy(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        this.setVelocityX(-10);
    }

    public void update() {
        System.out.println("heeeeeeee" + getNode().getBoundsInParent().getCenterX());
            //getNode().setLayoutX(getNode().getLayoutBounds().getCenterX()+1);
        getNode().relocate(getNode().getBoundsInParent().getMinX()+getVelocityX(), getNode().getBoundsInParent().getMinY());
            System.out.println("heeeeeeee" + getNode().getBoundsInParent().getCenterX());
        }
    }
