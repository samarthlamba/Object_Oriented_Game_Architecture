package ooga.engine.games;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import ooga.engine.entities.player.Player;
import ooga.engine.games.beans.MarioBean;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Movable;
import ooga.engine.entities.object.Coin;
import ooga.engine.obstacles.Unmovable;

public class MarioGame extends Game {
  private boolean leftOver = false;
  private boolean rightOver = false;
  private int coinSize = 50;
  private Collection<Movable> coins = new ArrayList<>();
  private double dt;
  private double lowestPoint = 0;
//  private GamePlayScreen tempGamePlayScreen = new GamePlayScreen();


  public MarioGame(Player player,Collection<Unmovable> obstacleCollection, Collection<Movable> entityCollection,
                   double timeElapsed, MarioBean bean) {
    super(player,obstacleCollection, entityCollection, timeElapsed, bean);
    entities = entityCollection;
    obstacles = obstacleCollection;
    dt = timeElapsed;
    findSceneLowestY();
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
   // removeMovable();
    for (Movable entity : entities) {
      moveMovable(entity);
      generateCoins(entity);
    }
    for(Movable coin : coins){
      entities.add(coin);
    }
    fallingDeath();
    viewable.remove(entitiesToRemove);
    entities.removeAll(entitiesToRemove);
    entitiesToRemove.clear();
    viewable.spawn(entitiesToAdd);
    entitiesToAdd.clear();
    coins.clear();
  }

  private void fallingDeath() {
    Movable player = getActivePlayer();
    if (player.getMaxY() > lowestPoint + 600) {
      player.setHitpoints(0);
    }
  }

  private void findSceneLowestY(){
    for(Unmovable obstacle : obstacles){
      double yPosition = obstacle.getNode().getBoundsInParent().getMaxY();
      if(yPosition > lowestPoint){
        lowestPoint = yPosition;
      }
    }
  }


  private void generateCoins(Movable entity){
    if(entity.getId().equals("question") && !entity.getStatusAlive()) {
      Random rand = new Random();
      int numberCoins = rand.nextInt(10) + 2;
      for (int i = 0; i < numberCoins; i++) {
        randomCoin(entity, i);
      }
    }
  }

  private void randomCoin(Movable entity, int seed){
    double initialX = entity.getCenterX();
    double initialY = entity.getMaxY() - 2 * entity.getEntityHeight();
    Coin coin = new Coin(coinSize, coinSize, initialX, initialY);
    Random randXVelocity = new Random(seed);
    Random randDirection = new Random(seed);
    Random randYVelocity = new Random(seed);
    double xVelocity = randXVelocity.nextInt(300);
    double yVelocity = randYVelocity.nextInt(2500);
    double direction = randDirection.nextInt(3);
    if(direction == 1){
      xVelocity *= NEGATIVE_DIRECTION;
    }
   // coin.setTimeElapsedY(dt);
    //coin.setTimeElapsedX(dt);
    coin.setVelocityX(xVelocity + 200);
    coin.setVelocityY(-(yVelocity + 400));
    coin.setJump(true);
    coins.add(coin);
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

  @Override
  public void setPoints(Movable entity){
    if(entity.getId().equals("coin")){
      System.out.println(totalPoints);
      totalPoints++;
    }
  }


}

