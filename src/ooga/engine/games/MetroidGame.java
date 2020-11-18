package ooga.engine.games;

import java.util.Collection;
import ooga.engine.entities.player.Player;
import ooga.engine.games.beans.MetroidBean;
import ooga.engine.entities.Movable;
import ooga.engine.entities.weapon.Bullet;
import ooga.engine.obstacles.Unmovable;


public class MetroidGame extends Game{
  private final static String POINT = "enemy";
  private final int bulletWidth;
  private final int bulletHeight;
  private final double bulletVelocity;

  public MetroidGame(Player player,Collection<Unmovable> obstacles,
                     Collection<Movable> entities, double timeElapsed, MetroidBean bean) {
    super(player, obstacles, entities, timeElapsed, bean);
    this.bulletWidth = bean.getBulletWidth();
    this.bulletHeight = bean.getBulletHeight();
    this.bulletVelocity = bean.getBulletVelocityX();
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
    specialActionDelayFlag = 0;
    entity.setSpecialAction(true);
    double bulletStartX = entity.getCenterX() - entity.getEntityWidth()/2;
    double bulletStartY = entity.getMaxY() - 3 * entity.getEntityHeight() / 4;
    double bulletVelocity = this.bulletVelocity;
    if(entity.getFacing()) {
      bulletStartX = entity.getCenterX() + entity.getEntityWidth()/2;
      bulletVelocity *= NEGATIVE_DIRECTION;
    }
    Bullet bullet = new Bullet(bulletWidth, bulletHeight, bulletStartX, bulletStartY);
    bullet.setVelocityX(bulletVelocity);
    entities.add(bullet);
    entitiesToAdd.add(bullet);
  }

  @Override
  public void setPoints(Movable entity){
    if(entity.getId().equals(POINT)){
      totalPoints++;
    }
  }
}
