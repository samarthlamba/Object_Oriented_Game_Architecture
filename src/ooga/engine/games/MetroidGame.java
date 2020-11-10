package ooga.engine.games;

import java.util.Collection;

import ooga.engine.games.beans.MetroidBean;
import javafx.scene.Node;
import ooga.engine.entities.Movable;
import ooga.engine.entities.weapon.Bullet;
import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;
import ooga.engine.obstacles.Unmovable;
import ooga.view.GamePlayScreen;


public class MetroidGame extends Game{
  private static final int BULLET_WIDTH = 10;
  private static final int BULLET_HEIGHT = 3;
  private static final double BULLET_VELOCITY = -30;
  private GamePlayScreen tempGamePlayScreen = new GamePlayScreen();

  public MetroidGame(Collection<Unmovable> obstacles,
                     Collection<Movable> entities, double timeElapsed, MetroidBean bean) {
    super(obstacles, entities, timeElapsed, bean);

  }
  public boolean hasFinished(){
    return false;
  }


  @Override
    public void shoot(){
      Movable entity = super.findMainPlayer();
      double bulletStartX = entity.getCenterX() - entity.getEntityWidth()/2;
      double bulletStartY = entity.getMaxY() - entity.getEntityHeight()/2;
      double bulletVelocity = BULLET_VELOCITY;
      if(entity.getFacing()) {
        bulletStartX = entity.getCenterX() + entity.getEntityWidth()/2;
        bulletVelocity *= NEGATIVE_DIRECTION;
      }
      Bullet bullet = new Bullet(BULLET_WIDTH, BULLET_HEIGHT, bulletStartX, bulletStartY);
      bullet.setVelocityX(bulletVelocity);
      entities.add(bullet);
      tempGamePlayScreen.spawn(bullet);
    }

  }
