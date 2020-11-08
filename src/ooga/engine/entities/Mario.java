package ooga.engine.entities;

public class Mario extends Player {


  public Mario(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }

  @Override
  public void topMoveables(Moveables entity) {
    entity.setHitpoints(entity.getHitpoints() - 1);
          }



}

