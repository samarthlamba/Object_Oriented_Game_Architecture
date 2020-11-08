package ooga.engine.games;

import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.games.Game;
import ooga.engine.entities.Moveables;
import ooga.engine.obstacles.Collideable;

public class MarioGame extends Game {
  private Collection<Collideable> obstacles;
  private Collection<Moveables> entities;
  private boolean leftOver = false;
  private boolean rightOver = false;

  public MarioGame(Collection<Collideable> obstacleCollection, Collection<Moveables> entityCollection,
                   double timeElapsed) {
    super(obstacleCollection, entityCollection, timeElapsed);
    entities = entityCollection;
    obstacles = obstacleCollection;
  }

  private void simulateFall(Moveables entity, Node object){
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
  public void collisionForce(Moveables entity) {
    for (Collideable obstacle : obstacles) {
      Node object = obstacle.getNodeObject();
      collisions(entity, object);
    }
  }


  @Override
  public void collisions(Moveables entity, Node object) {
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
  public void moveEnemy(Moveables entity) {
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
  private void enemyDirection(Moveables entity){
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

