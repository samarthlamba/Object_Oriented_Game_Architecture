package ooga.engine.games;

import java.util.Collection;
import ooga.engine.entities.Movable;
import ooga.engine.entities.player.Player;
import ooga.engine.entities.weapon.Bullet;
import ooga.engine.games.beans.MetroidBean;
import ooga.engine.obstacles.Unmovable;

/**
 * Concrete MetroidGame class that extends Game is part of our game inheritance hierarchy
 * contains metorid game specific code
 */
public class MetroidGame extends Game {

  private final static String POINT = "enemy";
  private final int bulletWidth;
  private final int bulletHeight;
  private final double bulletVelocity;

  /**
   * Metroid game constructor initializes variables through parameter input and constant values retrieved
   * from the beans
   *
   * @param player      main player entity
   * @param obstacles  collection of Unmovable obstacles
   * @param entities   collection of Movable entities
   * @param timeElapsed 1 / frame rate
   * @param bean        bean constants
   */
  public MetroidGame(Player player, Collection<Unmovable> obstacles,
      Collection<Movable> entities, double timeElapsed, MetroidBean bean) {
    super(player, obstacles, entities, timeElapsed, bean);
    this.bulletWidth = bean.getBulletWidth();
    this.bulletHeight = bean.getBulletHeight();
    this.bulletVelocity = bean.getBulletVelocityX();
  }

  /**
   * Checks if game is won if all of the enemies are killed
   * @return boolean returns true if condition is satisfied that no enemies alive
   */
  @Override
  public boolean isWon() {
    for (Movable entity : entities) {
      if (entity.getId().equals("enemy")) {
        return false;
      }
    }
    return true;
  }


  /**
   * When player action key pressed, player will shoot bullet weapons
   * These will kill enemies on contact
   */
  @Override
  public void playerAction() {
    Movable entity = super.getActivePlayer();
    specialActionDelayFlag = 0;
    entity.setSpecialAction(true);
    double bulletStartX = entity.getCenterX() - entity.getEntityWidth() / 2;
    double bulletStartY = entity.getMaxY() - 3 * entity.getEntityHeight() / 4;
    double bulletVelocity = this.bulletVelocity;
    if (entity.getFacing()) {
      bulletStartX = entity.getCenterX() + entity.getEntityWidth() / 2;
      bulletVelocity *= NEGATIVE_DIRECTION;
    }
    Bullet bullet = new Bullet(bulletWidth, bulletHeight, bulletStartX, bulletStartY);
    bullet.setVelocityX(bulletVelocity);
    entities.add(bullet);
    entitiesToAdd.add(bullet);
  }

  /**
   * Sets point value to number of enemies killed
   * @param entity Movable entity if enemy then points added
   */
  @Override
  public void setPoints(Movable entity) {
    if (entity.getId().equals(POINT)) {
      totalPoints++;
    }
  }
}
