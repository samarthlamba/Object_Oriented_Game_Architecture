package ooga.engine.entities.object;

import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;

public class PlayerObstacle extends Entity {
    Obstacle obstacle;
    int objectWidth;
    int objectHeight;
    double initialX;
    double initialY;

    public PlayerObstacle(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("playerobstacle");
        this.objectWidth = objectWidth;
        this.objectHeight = objectHeight;
        this.initialX = initialX;
        this.initialY = initialY;
    }

}
