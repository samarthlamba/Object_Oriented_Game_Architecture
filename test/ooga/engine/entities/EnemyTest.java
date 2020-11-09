package ooga.engine.entities;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ooga.engine.entities.enemy.Goomba;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

  Entity testEntityEnemy = new Goomba(100, 100, 50, 50);

    @Test
    void getNode() {

      Node ans = new Rectangle(50, 50, 100, 100);
      assertEquals(ans.getLayoutBounds(), testEntityEnemy.getNode().getLayoutBounds());
    }

    /*@Test
    void getID() {
      assertEquals(1, testEntityEnemy.getID());
    }*/

    @Test
    void getVelocityX() {
      assertEquals(200, testEntityEnemy.getVelocityX());
      testEntityEnemy.setVelocityX(27);
      assertEquals(27, testEntityEnemy.getVelocityX());
    }

    @Test
    void checkPreviousValue(){
      testEntityEnemy.setPreviousX(8);
      assertEquals(8, testEntityEnemy.getPreviousX());
      testEntityEnemy.setPreviousY(6);
      assertEquals(6, testEntityEnemy.getPreviousY());
    }
    @Test
    void getVelocityY() {
      assertEquals(0, testEntityEnemy.getVelocityY());
      testEntityEnemy.setVelocityY(154);
      assertEquals(154, testEntityEnemy.getVelocityY());
    }

 /* @Test
  void mass() {
    assertEquals(5, testEntity.getMass());
  }*/

    @Test
    void setVelocityX() {
      testEntityEnemy.setVelocityX(76);
      assertEquals(76, testEntityEnemy.getVelocityX());
    }

    @Test
    void setX() {
      testEntityEnemy.setCenterX(98);
      assertEquals(98, testEntityEnemy.getCenterX());
    }

    @Test
    void setY() {
      testEntityEnemy.setMaxY(87);
      assertEquals(87, testEntityEnemy.getMaxY());
    }

    @Test
    void setHitpoints() {
      testEntityEnemy.setHitpoints(104);
      assertEquals(104, testEntityEnemy.getHitpoints());
    }

  }
