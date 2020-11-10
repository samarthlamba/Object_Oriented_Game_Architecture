package ooga.engine.games;

import java.util.Collection;

import ooga.engine.games.beans.VikingsBean;
import javafx.scene.Node;
import ooga.engine.entities.Entity;
import ooga.engine.entities.weapon.Arrow;
import ooga.engine.entities.Movable;
import ooga.engine.obstacles.Obstacle;
import ooga.engine.obstacles.Unmovable;
import ooga.view.GamePlayScreen;

public class VikingsGame extends Game{

  private static final int ARROW_WIDTH = 10;
  private static final int ARROW_HEIGHT = 3;
  private static final double ARROW_VELOCITY = -30;
  private static final double UPWARDS_VELOCITY = -20;
  private GamePlayScreen tempGamePlayScreen = new GamePlayScreen();

  public VikingsGame(Collection<Unmovable> obstacles,
                     Collection<Movable> entities, double timeElapsed, VikingsBean bean) {
    super(obstacles, entities, timeElapsed, bean);
  }

  public boolean hasFinished(){
    return false;
  }

  @Override
  protected void updateMovable(){
    // System.out.println("stepped12324");
    for (Movable entity : entities) {
      moveMovable(entity);
      generateArrows(entity);
    }
    removeMovable();
  }

  public void generateArrows(Movable entity){
    if(entity.getId().equals("enemy")){
      makeArrow(entity);
    }
  }

  private void makeArrow(Movable enemy){
    double arrowStartX = enemy.getCenterX() - enemy.getEntityWidth()/2;
    double arrowStartY = enemy.getMaxY() - enemy.getEntityHeight()/2;
    double arrowVelocity = ARROW_VELOCITY;
    if(enemy.getFacing()) {
      arrowStartX = enemy.getCenterX() + enemy.getEntityWidth()/2;
      arrowVelocity *= NEGATIVE_DIRECTION;
    }
    Arrow arrow = new Arrow(ARROW_WIDTH, ARROW_HEIGHT, arrowStartX, arrowStartY);
    arrow.setVelocityX(arrowVelocity);
    arrow.setVelocityY(UPWARDS_VELOCITY);
    entities.add(arrow);
    tempGamePlayScreen.spawn(arrow);
  }


}
