package ooga.engine.entities;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

class EntityTest {
  Entity testEntity = new Mario(100, 100, 50, 50);
  Entity testEntityEnemy = new Goomba(100, 100, 50, 50);
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

}