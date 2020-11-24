package ooga.engine.games;

//<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import ooga.engine.entities.player.Player;
import ooga.engine.games.beans.MarioBean;
//=======
//>>>>>>> jnh24
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Movable;
import ooga.engine.entities.object.Coin;
import ooga.engine.entities.player.*;
import ooga.engine.games.beans.MarioBean;
import ooga.engine.obstacles.Unmovable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Concrete MarioGame class that extends Game is part of our game inheritance hierarchy
 * contains mario game specific code
 */
public class MarioGame extends Game {
  private final static int COIN_APPEAR_OFFSET = 3;
  private final static String POINTS = "coin";
  private final double simulateFallOffset;
  private final int coinDirectionNumerator;
  private final int coinDirectionDenominator;
  private final int randCoinVelocityXMax;
  private final int randCoinVelocityXMin;
  private final int randCoinVelocityYMax;
  private final int randCoinVelocityYMin;
  private boolean leftOver = false;
  private boolean rightOver = false;
  private final int coinSize;
  private final int randomCoinMax;
  private final int randomCoinMin;
  private final Collection<Movable> coins = new ArrayList<>();

  /**
   * Mario game constructor initializes variables through parameter input and constant values retrieved
   * from the beans
   *
   * @param player      main player entity
   * @param obstacleCollection   collection of Unmovable obstacles
   * @param entityCollection    collection of Movable entities
   * @param timeElapsed 1 / frame rate
   * @param bean        bean constants
   */
  public MarioGame(Player player,Collection<Unmovable> obstacleCollection, Collection<Movable> entityCollection,
                   double timeElapsed, MarioBean bean) {
    super(player,obstacleCollection, entityCollection, timeElapsed, bean);
    entities = entityCollection;
    obstacles = obstacleCollection;
    this.coinSize = bean.getCoinSize();
    this.randomCoinMax = bean.getRandomCoinMax();
    this.randomCoinMin = bean.getRandomCoinMin();
    this.simulateFallOffset = bean.getSimulateFallOffset();
    this.coinDirectionNumerator = bean.getCoinDirectionNumerator();
    this.coinDirectionDenominator = bean.getCoinDirectionDenominator();
    this.randCoinVelocityXMax = bean.getRandCoinVelocityXMax();
    this.randCoinVelocityXMin = bean.getRandCoinVelocityXMin();
    this.randCoinVelocityYMax = bean.getRandCoinVelocityYMax();
    this.randCoinVelocityYMin = bean.getRandCoinVelocityYMin();
  }


  private void simulateFall(Movable entity, Node object){
    Rectangle simulate = new Rectangle(entity.getNode().getBoundsInParent().getMinX(), entity.getMaxY(), simulateFallOffset, simulateFallOffset);
    if (simulate.intersects(object.getBoundsInParent())){
      leftOver = true;

    }
    simulate = new Rectangle(entity.getNode().getBoundsInParent().getMaxX(), entity.getMaxY(), simulateFallOffset, simulateFallOffset);
    if (simulate.intersects(object.getBoundsInParent())) {
      rightOver = true;
    }
  }

  @Override
  protected void updateMovable(){
    normalForce(entities, obstacles);
    for (Movable entity : entities) {
      moveMovable(entity);
      generateCoins(entity);
    }
    fallingDeath();
    entities.addAll(coins);
    viewable.remove(entitiesToRemove);
    entities.removeAll(entitiesToRemove);
    entitiesToRemove.clear();
    viewable.spawn(entitiesToAdd);
    entitiesToAdd.clear();
    coins.clear();
  }


  private void generateCoins(Movable entity){
    if(entity.doesGenerateCoins() && !entity.getStatusAlive()) {
      Random rand = new Random();
      int numberCoins = rand.nextInt(randomCoinMax - randomCoinMin) + randomCoinMin;
      for (int i = 0; i < numberCoins; i++) {
        randomCoin(entity, i);
      }
    }
  }

  private void randomCoin(Movable entity, int seed){
    double initialX = entity.getCenterX();
    double initialY = entity.getMaxY() - COIN_APPEAR_OFFSET * entity.getEntityHeight();
    Coin coin = new Coin(coinSize, coinSize, initialX, initialY);
    Random randXVelocity = new Random(seed);
    Random randDirection = new Random(seed);
    Random randYVelocity = new Random(seed);
    double xVelocity = randXVelocity.nextInt(randCoinVelocityXMax - randCoinVelocityXMin);
    double yVelocity = randYVelocity.nextInt(randCoinVelocityYMax - randCoinVelocityYMin);
    double direction = randDirection.nextInt(coinDirectionDenominator);
    if(direction == coinDirectionNumerator){
      xVelocity *= NEGATIVE_DIRECTION;
    }
    coin.setVelocityX(xVelocity + randCoinVelocityXMin);
    coin.setVelocityY(-(yVelocity + randCoinVelocityYMin));
    coin.setJump(true);
    coins.add(coin);
    entitiesToAdd.add(coin);
  }

  @Override
  protected void collisions(Movable entity, Collideable object) {
    if (object.getNode().getBoundsInParent().intersects(entity.getNode().getBoundsInParent())) {
      if (entity.getHorizontalMovement()) {
        simulateFall(entity, object.getNode());
      }
      handleCollisions.collisions((Entity) entity, object);
    }
  }

  /**
   * method that properly sets velocity to make entities
   * move independently back and forth across the screen
   * @param entity Movable entity will have correct movement if
   *               it has true horizontal movement state
   */
  @Override
  public void autoEntityMovement(Movable entity) {
    entityDirection(entity);
    if(entity.getHorizontalMovement()){
      if(entity.getPreviousY() != entity.getMaxY()){
        entity.setMaxY(entity.getPreviousY());
        entity.setCenterX(entity.getPreviousX());
        entity.setVelocityX(entity.getVelocityX() * NEGATIVE_DIRECTION);
      }
    }
  }

  private void entityDirection(Movable entity){
    if(!leftOver && rightOver){
      entity.setVelocityX(Math.abs(entity.getVelocityX()));
    }
    if(!rightOver && leftOver){
      entity.setVelocityX(Math.abs(entity.getVelocityX()) * NEGATIVE_DIRECTION);
    }
    leftOver = false;
    rightOver = false;
  }

  /**
   * sets point value as number of coins collection
   * @param entity if coin then is added to point value
   */
  @Override
  public void setPoints(Movable entity){
    if(entity.getId().equals(POINTS)){
      totalPoints++;
    }
  }
}

