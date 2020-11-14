package ooga.engine.entities;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.shape.Rectangle;
import ooga.engine.entities.enemy.Goomba;
import ooga.engine.entities.player.Mario;
import ooga.engine.entities.player.Player;
import ooga.engine.entities.player.Viking;
import org.junit.jupiter.api.Test;

class EntityTest {
  Entity testEntity = new Mario(100, 100, 50, 50);
  Entity testEntityEnemy = new Goomba(100, 100, 50, 50);
  Entity vikingEntity = new Viking(100, 100, 10, 10);
  @Test
  void getNode() {

    Rectangle ans = new Rectangle(50, 50, 100, 100);
    assertEquals(ans.getLayoutBounds(), testEntity.getNode().getLayoutBounds());
  }

 /* @Test
  void getID() {
    assertEquals("player", testEntity.getId());
  }*/

  @Test
  void getVelocityX() {
    assertEquals(0, testEntity.getVelocityX());
    testEntity.setVelocityX(24);
    assertEquals(24, testEntity.getVelocityX());
  }

  @Test
  void checkPreviousValue(){
    testEntity.setPreviousX(14);
    assertEquals(14, testEntity.getPreviousX());
    testEntity.setPreviousY(17);
    assertEquals(17, testEntity.getPreviousY());
  }
  @Test
  void getVelocityY() {
    assertEquals(0, testEntity.getVelocityY());
    testEntity.setVelocityY(21);
    assertEquals(21, testEntity.getVelocityY());
  }

 /* @Test
  void mass() {
    assertEquals(5, testEntity.getMass());
  }*/

  @Test
  void setVelocityX() {
    testEntity.setVelocityX(21);
    assertEquals(21, testEntity.getVelocityX());
  }

  @Test
  void setX() {
    testEntity.setCenterX(18);
    assertEquals(18, testEntity.getCenterX());
  }

  @Test
  void setY() {
    testEntity.setMaxY(47);
    assertEquals(47, testEntity.getMaxY());
  }

  @Test
  void setHitpoints() {
    testEntity.setHitpoints(100);
    assertEquals(100, testEntity.getHitpoints());
  }

  @Test
  void getEntityHeightTest() {
    assertEquals(100, testEntity.getEntityHeight());
  }


  @Test
  void getXForceTest() {
    testEntity.setXForce(21);
    assertEquals(21, testEntity.getXForce());
  }

  @Test
  void timeElapsedTest() {
   // testEntity.setTimeElapsedY(2);
   // assertEquals(2, testEntity.getTimeElapsedY());
  }
  @Test
  void facingTest() {
    testEntity.setFacing(false);
    assertEquals(false, testEntity.getFacing());
  }

 /* @Test
  void hasFinishedTest(){
    assertEquals(false, testEntity.hasFinished());
    testEntity.setFinished(true);
    assertEquals(true, testEntity.hasFinished());
  }*/

  @Test
  void leftCollideable(){
    testEntityEnemy = new Goomba(100, 100, 50, 50);
    assertEquals(true, testEntityEnemy.status_Alive);
    vikingEntity.leftCollideable(testEntityEnemy);
    assertEquals(false, testEntityEnemy.status_Alive);

  }

  @Test
  void rightCollideable(){
    testEntityEnemy = new Goomba(100, 100, 50, 50);
    assertEquals(true, testEntityEnemy.status_Alive);
    vikingEntity.rightCollideable(testEntityEnemy);
    assertEquals(false, testEntityEnemy.status_Alive);

  }

  @Test
  void testGetNode() {
    testEntity = new Mario(100, 100, 50, 50);
    Rectangle compare = new Rectangle(50, 50, 100, 100);
    assertEquals(compare.getBoundsInParent(),testEntity.getNode().getBoundsInParent());
  }

  @Test
  void getStatusAlive() {
    assertEquals(true, testEntity.getStatusAlive());
    testEntity.setHitpoints(0);
    assertEquals(false, testEntity.getStatusAlive());
  }

 /* @Test
  void hasFinished() {
    testEntity.setFinished(false);
    assertEquals(false, testEntity.hasFinished());
    testEntity.setFinished(true);
    assertEquals(true, testEntity.hasFinished());
  }


  @Test
  void hasLost() {
    testEntity.setLost(false);
    assertEquals(false, testEntity.hasLost());
    testEntity.setLost(true);
    assertEquals(true, testEntity.hasLost());
  }*/


  @Test
  void getHealth() {
    testEntity.setHitpoints(21);
    assertEquals(21, testEntity.getHealth());
  }

}