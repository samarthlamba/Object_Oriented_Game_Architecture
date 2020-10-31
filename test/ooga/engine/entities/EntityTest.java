package ooga.engine.entities;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

class EntityTest {
  Entity testEntity = new Mario(100, 100, 50, 50);
  @Test
  void getNode() {

    Rectangle ans = new Rectangle(100, 100, 50, 50);
    assertEquals(ans, testEntity.getNode());
  }

  @Test
  void getID() {
  }

  @Test
  void getVelocityX() {
  }

  @Test
  void getVelocityY() {
  }

  @Test
  void mass() {
  }

  @Test
  void setVelocityX() {
  }

  @Test
  void setX() {
  }

  @Test
  void setY() {
  }

  @Test
  void setHitpoints() {
  }

  @Test
  void getHitpoints() {
  }
}