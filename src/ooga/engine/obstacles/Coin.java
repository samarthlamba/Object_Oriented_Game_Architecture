package ooga.engine.obstacles;

import javafx.scene.Node;
import ooga.engine.entities.Moveables;
import ooga.engine.obstacles.Obstacle;

/**
 * Gives mario coin when hit
 */
public class Coin extends Obstacle {
  private int totalMoney = 0;
  public Coin(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
    super(obstacleWidth, obstacleHeight, initialX, initialY);
  }

  @Override
  public Node getNodeObject() {
    return null;
  }

  public void addMoney(int amount){
    totalMoney = totalMoney + amount;
  }

  public int getTotalMoney() {
    return totalMoney;
  }
}
