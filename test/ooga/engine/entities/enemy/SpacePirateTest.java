package ooga.engine.entities.enemy;

import ooga.engine.entities.player.Samus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpacePirateTest {

    @Test
    void leftCollideable() {

        Samus samus = new Samus(10, 10, 0, 0);
        SpacePirate pirate = new SpacePirate(10, 10, 0, 0);
        pirate.leftCollideable(samus);
        samus.getHitpoints();
        assertEquals(samus.getHitpoints()-1,samus.getHitpoints());
        assertEquals(false, samus.getStatusAlive());

    }

    @Test
    void rightCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        SpacePirate pirate = new SpacePirate(10, 10, 0, 0);
        pirate.rightCollideable(samus);
        assertEquals(samus.getHitpoints()-1,samus.getHitpoints());
        assertEquals(false, samus.getStatusAlive());
    }

    @Test
    void bottomCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        SpacePirate pirate = new SpacePirate(10, 10, 0, 0);
        pirate.bottomCollideable(samus);
        assertEquals(samus.getHitpoints()-1,samus.getHitpoints());
        assertEquals(false, samus.getStatusAlive());
    }

    @Test
    void topCollideable() {
        Samus samus = new Samus(10, 10, 0, 0);
        SpacePirate pirate = new SpacePirate(10, 10, 0, 0);
        pirate.topCollideable(samus);
        assertEquals(samus.getHitpoints()-1,samus.getHitpoints());
        assertEquals(false, samus.getStatusAlive());
    }
}