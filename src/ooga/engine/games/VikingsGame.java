package ooga.engine.games;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import ooga.engine.games.beans.VikingsBean;
import javafx.scene.Node;
import ooga.engine.entities.Entity;
import ooga.engine.entities.weapon.Arrow;
import ooga.engine.entities.Movable;
import ooga.engine.obstacles.Obstacle;
import ooga.engine.obstacles.Unmovable;
import ooga.view.GamePlayScreen;

public class VikingsGame extends Game{

  private static final int ARROW_WIDTH = 3;
  private static final int ARROW_HEIGHT = 10;
  //private static final double ARROW_VELOCITY = -30;
  private static final double UPWARDS_VELOCITY = -200;
  private Collection<Movable> arrows = new ArrayList<>();
  private double dt;
//  private GamePlayScreen tempGamePlayScreen = new GamePlayScreen();

  public VikingsGame(Collection<Unmovable> obstacles,
                     Collection<Movable> entities, double timeElapsed, VikingsBean bean) {
    super(obstacles, entities, timeElapsed, bean);
    dt = timeElapsed;
  }

  public boolean hasFinished(){
    return false;
  }

  @Override
  protected void updateMovable(){
    // System.out.println("stepped12324");
    for (Movable entity : entities) {
      moveMovable(entity);
      if(entity.getId().equals("enemy")){
        generateArrows(entity);
      }
    }
    for(Movable arrow : arrows){
      entities.add(arrow);
    }
    viewable.remove(entitiesToRemove);
    entities.removeAll(entitiesToRemove);
    entitiesToRemove.clear();
    viewable.spawn(entitiesToAdd);
    entitiesToAdd.clear();
    arrows.clear();
  }

  public void generateArrows(Movable entity){
    if(entity.getId().equals("enemy")){
      makeArrow(entity);
    }
  }

  private void makeArrow(Movable enemy){
    double arrowStartX = enemy.getCenterX() - enemy.getEntityWidth();
    double arrowStartY = enemy.getMaxY() - enemy.getEntityHeight();
    //double arrowVelocity = ARROW_VELOCITY;
    if(enemy.getFacing()) {
      arrowStartX = enemy.getCenterX() + enemy.getEntityWidth()/2;
     // arrowVelocity *= NEGATIVE_DIRECTION;
    }
    Arrow arrow = new Arrow(ARROW_WIDTH, ARROW_HEIGHT, arrowStartX, arrowStartY);
    //arrow.setVelocityX(arrowVelocity);
    arrow.setVelocityY(UPWARDS_VELOCITY);
    arrow.setTimeElapsedX(dt);
    arrow.setTimeElapsedY(dt);
    Random rand = new Random();
    double arrowFrequency = rand.nextInt(10);
    if(arrowFrequency == 1) {
      arrows.add(arrow);
      entitiesToAdd.add(arrow);
    }
//    tempGamePlayScreen.spawn(arrow);
  }


}
