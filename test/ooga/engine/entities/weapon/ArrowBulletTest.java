package ooga.engine.entities.weapon;

import ooga.engine.entities.enemy.SpacePirate;
import ooga.engine.entities.player.Samus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrowBulletTest {

    @Test
    void checkCreation(){
        Arrow arrow = new Arrow(10, 10, 10, 10);
        assertEquals(arrow.getId(), "arrow");

        Bullet bullet = new Bullet(10, 10, 10, 10);
        assertEquals(bullet.getId(), "bullet");

        assertEquals(true, bullet.hasGravity());


    }
    @Test
    void checkGravity(){
        Arrow arrow = new Arrow(10, 10, 10, 10);
        assertEquals(false, arrow.hasGravity());
    }

    @Test
    void leftCollideable() {

        Samus samus = new Samus(10, 10, 0, 0);
        Weapon arrow = new Arrow(10, 10, 0, 0);
        arrow.leftCollideable(samus);
        assertEquals(0,samus.getHitpoints());
        assertEquals(false, samus.getStatusAlive());

    }

    @Test
    void rightCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        Weapon arrow = new Arrow(10, 10, 0, 0);
        arrow.rightCollideable(samus);
        assertEquals(0,samus.getHitpoints());
        assertEquals(false, samus.getStatusAlive());
    }

    @Test
    void bottomCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        Weapon arrow = new Arrow(10, 10, 0, 0);
        arrow.bottomCollideable(samus);
        assertEquals(0,samus.getHitpoints());
        assertEquals(false, samus.getStatusAlive());
    }

    @Test
    void topCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        Weapon arrow = new Arrow(10, 10, 0, 0);
        arrow.topCollideable(samus);
        assertEquals(0,samus.getHitpoints());
        assertEquals(false, samus.getStatusAlive());
    }

}