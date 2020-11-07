package ooga.engine.entities;

public class Mario extends Player {


  public Mario(int objectWidth, int objectHeight, double initialX, double initialY) {
    super(objectWidth, objectHeight, initialX, initialY);
  }

  public void update() {
    if(getHitpoints() < 0){
      status_Alive = false;
    }
  }
  @Override
  public void topMoveables(Moveables entity) {
    entity.setHitpoints(entity.getHitpoints() - 1);
          }



}

