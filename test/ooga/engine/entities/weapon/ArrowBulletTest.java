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

        assertEquals(false, arrow.hasGravity());


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
        arrow.leftCollideable(samus);
        arrow.leftCollideable(samus);
        assertEquals(0,samus.getHitpoints());
        assertEquals(false, samus.getStatusAlive());

    }

    @Test
    void rightCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        Weapon arrow = new Arrow(10, 10, 0, 0);
        int initialHitpoint = samus.getHitpoints();
        arrow.rightCollideable(samus);
        arrow.rightCollideable(samus);
        assertEquals(initialHitpoint-2,samus.getHitpoints());
        assertEquals(true, samus.getStatusAlive());
    }

    @Test
    void bottomCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        Weapon arrow = new Arrow(10, 10, 0, 0);
        arrow.bottomCollideable(samus);
        arrow.bottomCollideable(samus);
        arrow.bottomCollideable(samus);
        arrow.topCollideable(samus);
        assertEquals(3,samus.getHitpoints());
        assertEquals(true, samus.getStatusAlive());
    }

    @Test
    void topCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        Weapon arrow = new Arrow(10, 10, 0, 0);
        arrow.topCollideable(samus);
        arrow.topCollideable(samus);
        arrow.topCollideable(samus);
        assertEquals(3,samus.getHitpoints());
        assertEquals(true, samus.getStatusAlive());
    }

}