package ooga.engine.entities;

public class Mario extends Player {


  public Mario(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }

  public void update() {
    System.out.println("heeeeeeee" + getNode().getBoundsInParent().getMaxY());
    //getNode().setLayoutX(getNode().getLayoutBounds().getCenterX()+1);
    getNode().relocate(getNode().getBoundsInParent().getMinX()+getVelocityX(), getNode().getBoundsInParent().getMinY() + getVelocityY());
    System.out.println("heeeeeeee" + getNode().getBoundsInParent().getMaxY());
  }
}

