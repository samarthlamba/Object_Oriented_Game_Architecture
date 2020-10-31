package ooga.engine.entities;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

class EntityTest {
  Entity testEntity = new Mario(100, 100, 50, 50);
  @Test
  void getNode() {

    Rectangle ans = new Rectangle(50, 50, 100, 100);
    assertEquals(ans, testEntity.getNode());
  }

  @Test
  void getID() {
    assertEquals(1, testEntity.getID());
  }

  @Test
  void getVelocityX() {
    assertEquals(5, testEntity.getVelocityX());
  }

  @Test
  void getVelocityY() {
    assertEquals(10, testEntity.getVelocityY());
  }

  @Test
  void mass() {
    assertEquals(5, testEntity.getMass());
  }

  @Test
  void setVelocityX() {
    testEntity.setVelocityX(21);
    assertEquals(21, testEntity.getVelocityX());
  }

  @Test
  void setX() {
    testEntity.setX(18);
    assertEquals(18, testEntity.getNode().getLayoutBounds().getCenterX());
  }

  @Test
  void setY() {
    testEntity.setY(47);
    assertEquals(47, testEntity.getNode().getLayoutBounds().getCenterY());
  }

  @Test
  void setHitpoints() {
    testEntity.setHitpoints(100);
    assertEquals(100, testEntity.getHitpoints());
  }

}