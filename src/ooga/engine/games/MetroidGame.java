package ooga.engine.games;

import java.util.Collection;

import ooga.engine.entities.player.Player;
import ooga.engine.games.beans.MetroidBean;
import ooga.engine.entities.Movable;
import ooga.engine.entities.weapon.Bullet;
import ooga.engine.obstacles.Unmovable;


public class MetroidGame extends Game{
  private static final int BULLET_WIDTH = 3;
  private static final int BULLET_HEIGHT = 10;
  private static final double BULLET_VELOCITY = -1000;
  private double dt;
//  private GamePlayScreen tempGamePlayScreen = new GamePlayScreen();

  public MetroidGame(Player player,Collection<Unmovable> obstacles,
                     Collection<Movable> entities, double timeElapsed, MetroidBean bean) {
    super(player, obstacles, entities, timeElapsed, bean);
    dt = timeElapsed;
  }


  @Override
  public boolean isWon(){
    for(Movable entity : entities){
      if(entity.getId().equals("enemy")){
        return false;
      }
    }
    return true;
  }


  @Override
    public void playerAction(){
      Movable entity = super.getActivePlayer();
      double bulletStartX = entity.getCenterX() - entity.getEntityWidth()/2;
      double bulletStartY = entity.getMaxY() - entity.getEntityHeight()/2;
      double bulletVelocity = BULLET_VELOCITY;
      if(entity.getFacing()) {
        bulletStartX = entity.getCenterX() + entity.getEntityWidth()/2;
        bulletVelocity *= NEGATIVE_DIRECTION;
      }
      Bullet bullet = new Bullet(BULLET_WIDTH, BULLET_HEIGHT, bulletStartX, bulletStartY);
      bullet.setVelocityX(bulletVelocity);
     // bullet.setTimeElapsedX(dt);
      entities.add(bullet);
      entitiesToAdd.add(bullet);
//      tempGamePlayScreen.spawn(bullet);
    }

  @Override
  public void setPoints(Movable entity){
    if(entity.getId().equals("enemy")){
      totalPoints++;
    }
  }

  }
