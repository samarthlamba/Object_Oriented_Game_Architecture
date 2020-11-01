package ooga.engine.entities;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

class EntityTest {
  Entity testEntity = new Mario(100, 100, 50, 50);
  Entity testEntityEnemy = new Enemy(100, 100, 50, 50);
  @Test
  void getNode() {

    Rectangle ans = new Rectangle(50, 50, 100, 100);
    assertEquals(ans.getLayoutBounds(), testEntity.getNode().getLayoutBounds());
  }

  @Test
  void getID() {
    assertEquals(0, testEntity.getID());
  }

  @Test
  void getVelocityX() {
    assertEquals(5, testEntity.getVelocityX());
  }

  @Test
  void getVelocityY() {
    assertEquals(10, testEntity.getVelocityY());
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
    testEntity.setX(18);
    assertEquals(18, testEntity.getX());
  }

  @Test
  void setY() {
    testEntity.setY(47);
    assertEquals(47, testEntity.getY());
  }

  @Test
  void setHitpoints() {
    testEntity.setHitpoints(100);
    assertEquals(100, testEntity.getHitpoints());
  }

}