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

    @Override
    public void leftCollideable(Entity entity) {
        entityDeath(entity, "arrow");
        entityDeath(entity, "waterfall");
    }

    @Override
    public void rightCollideable(Entity entity) {
        entityDeath(entity, "arrow");
        entityDeath(entity, "waterfall");
    }

    @Override
    public void bottomCollideable(Entity entity) {
        entityDeath(entity, "arrow");
        entityDeath(entity, "waterfall");
    }

    @Override
    public void topCollideable(Entity entity) {
        entityDeath(entity, "arrow");
        entityDeath(entity, "waterfall");
    }

}
