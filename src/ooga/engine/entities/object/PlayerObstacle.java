package ooga.engine.entities.object;

import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class PlayerObstacle extends Entity {

    public PlayerObstacle(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("playerobstacle");
    }


    @Override
    public boolean hasGravity(){
        return false;
    }

}
