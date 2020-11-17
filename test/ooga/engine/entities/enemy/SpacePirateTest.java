package ooga.engine.entities.enemy;

import ooga.engine.entities.player.Samus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpacePirateTest {

    @Test
    void leftCollideable() {

        Samus samus = new Samus(10, 10, 0, 0);
        SpacePirate pirate = new SpacePirate(10, 10, 0, 0);
        int preHitLives = samus.getHitpoints();
        pirate.leftCollideable(samus);
        samus.getHitpoints();
        assertEquals(preHitLives-1,samus.getHitpoints());
        pirate.bottomCollideable(samus);
        pirate.bottomCollideable(samus);
        assertEquals(false, samus.getStatusAlive());

    }

    @Test
    void rightCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        SpacePirate pirate = new SpacePirate(10, 10, 0, 0);
        int preHitLives = samus.getHitpoints();
        pirate.rightCollideable(samus);
        assertEquals(preHitLives-1,samus.getHitpoints());
        pirate.bottomCollideable(samus);
        assertEquals(true, samus.getStatusAlive());
    }

    @Test
    void bottomCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        SpacePirate pirate = new SpacePirate(10, 10, 0, 0);
        int preHitLives = samus.getHitpoints();
        pirate.bottomCollideable(samus);
        assertEquals(preHitLives-1,samus.getHitpoints());
        pirate.bottomCollideable(samus);
        pirate.bottomCollideable(samus);
        assertEquals(false, samus.getStatusAlive());
    }

    @Test
    void topCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        SpacePirate pirate = new SpacePirate(10, 10, 0, 0);
        int preHitLives = samus.getHitpoints();
        pirate.topCollideable(samus);
        assertEquals(preHitLives-1,samus.getHitpoints());
        pirate.bottomCollideable(samus);
        pirate.bottomCollideable(samus);
        assertEquals(false, samus.getStatusAlive());
    }
}