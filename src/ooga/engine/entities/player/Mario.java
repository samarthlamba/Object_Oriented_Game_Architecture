package ooga.engine.entities.player;

import ooga.engine.entities.player.Player;

public class Mario extends Player {

    private static final int FULL_HEALTH = 100;

    public Mario(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setHitpoints(FULL_HEALTH);
    }


}

