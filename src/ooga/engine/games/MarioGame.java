package ooga.engine.games;

import java.util.Collection;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.entities.Entity;
import ooga.engine.entities.object.Coin;
import ooga.engine.obstacles.Obstacle;

public class MarioGame extends Game {
  private Collection<Obstacle> obstacles;
  private Collection<Entity> entities;
  private boolean leftOver = false;
  private boolean rightOver = false;
  private int coinSize = 50;

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
  protected void updateEntity(){
      // System.out.println("stepped12324");
    for (Entity entity : entities) {
      moveEntity(entity);
      generateCoins(entity);
    }
    removeEntity();
  }

  private void generateCoins(Entity entity){
    if(entity.getId().equals("question") && !entity.getStatusAlive()) {
      Random rand = new Random();
      int numberCoins = rand.nextInt(10);
      for (int i = 0; i < numberCoins; i++) {
        randomCoin(entity);
      }
    }
  }

  private void randomCoin(Entity entity){
    double initialX = entity.getCenterX() - entity.getEntityWidth()/2;
    double initialY = entity.getMaxY() - entity.getEntityHeight();
    Coin coin = new Coin(coinSize, coinSize, initialX, initialY);
    Random randXForceHigh = new Random();
    Random randXForceLow = new Random();
    Random randYForceHigh = new Random();
    Random randYForceLow = new Random();
    double xForce = randXForceHigh.nextDouble() * 1000 + randXForceLow.nextDouble() * -1000;
    double yForce = randYForceHigh.nextDouble() * 100 + randYForceLow.nextDouble() * -100;
    coin.setXForce(xForce);
    coin.setY(yForce);
    entities.add(coin);
  }

 /* @Override
  public void collisionForce(Entity entity) {
    for (Obstacle obstacle : obstacles) {
      Node object = obstacle.getNodeObject();
      collisions(entity, object);
    }
    if(!entity.getId().equals("player")){
      collisions(entity, findMainPlayer());
    }
  }*/


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

