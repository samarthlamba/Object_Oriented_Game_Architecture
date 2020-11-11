package ooga.engine.games;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import ooga.engine.games.beans.MarioBean;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Movable;
import ooga.engine.entities.object.Coin;
import ooga.engine.obstacles.Obstacle;
import ooga.engine.obstacles.Unmovable;
import ooga.view.GamePlayScreen;

public class MarioGame extends Game {
  private boolean leftOver = false;
  private boolean rightOver = false;
  private int coinSize = 50;
//  private GamePlayScreen tempGamePlayScreen = new GamePlayScreen();


  public MarioGame(Collection<Unmovable> obstacleCollection, Collection<Movable> entityCollection,
                   double timeElapsed, MarioBean bean) {
    super(obstacleCollection, entityCollection, timeElapsed, bean);
    entities = entityCollection;
    obstacles = obstacleCollection;

  }

  public boolean hasFinished(){
    return false;
  }

  private void simulateFall(Movable entity, Node object){
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
  protected void updateMovable(){
      // System.out.println("stepped12324");
    for (Movable entity : entities) {
      moveMovable(entity);
      generateCoins(entity);
    }
    removeMovable();
  }

  private void generateCoins(Movable entity){
    if(entity.getId().equals("question") && !entity.getStatusAlive()) {
      Random rand = new Random();
      int numberCoins = rand.nextInt(10);
      for (int i = 0; i < numberCoins; i++) {
        randomCoin(entity);
      }
    }
  }

  private void randomCoin(Movable entity){
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
    coin.setYForce(yForce);
    entities.add(coin);
    entitiesToAdd.add(coin);
//    tempGamePlayScreen.spawn(entitiesToAdd);
  }

 /* @Override
  public void collisionForce(Movable entity) {
    for (Obstacle obstacle : obstacles) {
      Node object = obstacle.getNodeObject();
      collisions(entity, object);
    }
    if(!entity.getId().equals("player")){
      collisions(entity, findMainPlayer());
    }
  }*/


  @Override
  public void collisions(Movable entity, Collideable object) {
    if (object.getNode().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
      if (entity.getId() == "enemy") {
        simulateFall(entity, object.getNode());
      }
      handleCollisions.collisions((Entity) entity, object);
    }

  }

  @Override
  public void moveEnemy(Movable entity) {
    enemyDirection(entity);
    if(entity.getId().equals("enemy")){
      // System.out.println("prev " + entity.getPreviousY() + " now " + entity.getMaxY());
      if(entity.getPreviousY() != entity.getMaxY()){
        entity.setMaxY(entity.getPreviousY());
        entity.setCenterX(entity.getPreviousX());
        entity.setVelocityX(entity.getVelocityX()*-1);
      }
    }

  }
  private void enemyDirection(Movable entity){
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

