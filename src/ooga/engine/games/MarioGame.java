package ooga.engine.games;

import java.util.Collection;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class MarioGame extends Game {
  private Collection<Obstacle> obstacles;
  private Collection<Entity> entities;
  private boolean leftOver = false;
  private boolean rightOver = false;

  public MarioGame(Collection<Obstacle> obstacleCollection, Collection<Entity> entityCollection,
                   double timeElapsed) {
    super(obstacleCollection, entityCollection, timeElapsed);
    entities = entityCollection;
    obstacles = obstacleCollection;
  }

  public boolean hasFinished(){
    return false;
  }
  private void simulateFall(Entity entity, Node object){
    Rectangle simulate = new Rectangle(entity.getNode().getBoundsInParent().getMinX(), entity.getMaxY(), 0.1, 0.1);
    if (simulate.intersects(object.getBoundsInParent())){
      leftOver = true;

    }
    simulate = new Rectangle(entity.getNode().getBoundsInParent().getMaxX(), entity.getMaxY(),0.1, 0.1);
    if (simulate.intersects(object.getBoundsInParent())) {
      rightOver = true;
    }
  }
  @Override
  public void collisionForce(Entity entity) {
    for (Obstacle obstacle : obstacles) {
      Node object = obstacle.getNodeObject();
      collisions(entity, object);
    }
  }


  @Override
  public void collisions(Entity entity, Node object) {
    if (object.getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
      if (entity.getId() == "enemy") {
        simulateFall(entity, object);
      }
      handleCollisions.collisions(entity, object);
    }
  }

  @Override
  public void fireBullets() {}


  @Override
  public void moveEnemy(Entity entity) {
    enemyDirection(entity);
    if(entity.getId().equals("enemy")){
      // System.out.println("prev " + entity.getPreviousY() + " now " + entity.getMaxY());
      if(entity.getPreviousY() != entity.getMaxY()){
        entity.setMaxY(entity.getPreviousY());
        entity.setCenterX(entity.getPreviousX());
        entity.setVelocityX(entity.getVelocityX()*-1);
        // double c = entity.getMaxY();
      }

      // double c = entity.getMaxY();


      // System.out.println(entity.getVelocityX());


    }

  }
  private void enemyDirection(Entity entity){
    if(!leftOver && rightOver){
      entity.setVelocityX(Math.abs(entity.getVelocityX())*1);
    }
    if(!rightOver && leftOver){
      entity.setVelocityX(Math.abs(entity.getVelocityX())*-1);
    }
    //System.out.println("status " + leftOver + "     " +  rightOver);
    leftOver = false;
    rightOver = false;
  }

}

