package ooga.engine.entities.enemy;

import ooga.engine.entities.player.Mario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoombaTest {

    @Test
    void leftCollideable() {

        Goomba test = new Goomba(50,50,0,0);
        Mario player = new Mario(50, 50, 0, 0);
        int initialHitpoints = player.getHitpoints();
        test.leftCollideable(player);
        assertNotEquals(initialHitpoints, player.getHitpoints());
    }

    @Test
    void rightCollideable() {
        Goomba test = new Goomba(50,50,0,0);
        Mario player = new Mario(50, 50, 0, 0);
        int initialHitpoints = player.getHitpoints();
        test.rightCollideable(player);
        assertNotEquals(initialHitpoints, player.getHitpoints());
    }

    @Test
    void bottomCollideable() {
        Goomba test = new Goomba(50,50,0,0);
        Mario player = new Mario(50, 50, 0, 0);
        int initialHitpoints = player.getHitpoints();
        test.bottomCollideable(player);
        assertNotEquals(initialHitpoints, player.getHitpoints());
    }

    @Test
    void topCollideable() {
        Goomba test = new Goomba(50,50,0,0);
        Mario player = new Mario(50, 50, 0, 0);
        int initialHitpoints = test.getHitpoints();
        player.setYForce(400);
        test.topCollideable(player);
        assertEquals(0, test.getHitpoints());
    }
}