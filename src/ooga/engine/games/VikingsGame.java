package ooga.engine.games;

import java.util.*;

import ooga.engine.entities.object.PlayerObstacle;
import ooga.engine.entities.player.Player;
import ooga.engine.games.beans.VikingsBean;
import ooga.engine.entities.weapon.Arrow;
import ooga.engine.entities.Movable;
import ooga.engine.obstacles.Unmovable;

public class VikingsGame extends Game{

  private static final int ARROW_WIDTH = 15;
  private static final int ARROW_HEIGHT = 6;
  //private static final double ARROW_VELOCITY = -30;
  private double xVelocity = -1000;
  private Collection<Movable> arrows = new ArrayList<>();
  private List<Movable> playerOrder = new ArrayList<>();
  private double dt;

//  private GamePlayScreen tempGamePlayScreen = new GamePlayScreen();

  public VikingsGame(Player player,Collection<Unmovable> obstacles,
                     Collection<Movable> entities, double timeElapsed, VikingsBean bean) {
    super(player,obstacles, entities, timeElapsed, bean);
    dt = timeElapsed;
    getPlayerObstacle();
  }

  private void getPlayerObstacle(){
    int i = 0;
    for(Movable entity : entities){
      if(entity.getId().equals("playerobstacle")){
        playerOrder.add(i, entity);
        i++;
      }
    }
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
    double arrowStartY = enemy.getMaxY() - enemy.getEntityHeight()/2;
    //double arrowVelocity = ARROW_VELOCITY;
    if(enemy.getFacing()) {
      arrowStartX = enemy.getCenterX() + enemy.getEntityWidth()/2;
      xVelocity *= NEGATIVE_DIRECTION;
    }
    Arrow arrow = new Arrow(ARROW_WIDTH, ARROW_HEIGHT, arrowStartX, arrowStartY);
    //arrow.setVelocityX(arrowVelocity);
    arrow.setVelocityX(xVelocity);
    //arrow.setTimeElapsedX(dt);
    Random rand = new Random();
    double arrowFrequency = rand.nextInt(10);
    if(arrowFrequency == 1) {
      arrows.add(arrow);
      entitiesToAdd.add(arrow);
    }
//    tempGamePlayScreen.spawn(arrow);
  }

  @Override
  public void playerAction() {
    Movable entity = super.getActivePlayer();
    double startY = entity.getMaxY() - entity.getEntityHeight();
    double startX = entity.getCenterX() - entity.getEntityWidth()/2;
    PlayerObstacle block = new PlayerObstacle((int) entity.getEntityWidth(), (int) entity.getEntityHeight(), startX, startY);
    Movable nextPlayer = playerOrder.get(0);
    entity.setCenterX(nextPlayer.getCenterX());
    entity.setMaxY(nextPlayer.getMaxY());
    playerOrder.remove(0);
    playerOrder.add(1, block);
    entitiesToRemove.add(nextPlayer);
    entitiesToAdd.add(block);
    entities.add(block);
    entities.remove(nextPlayer);
  }

  @Override
  public void setPoints(Movable entity){
    totalPoints += dt;
  }

}
