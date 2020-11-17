package ooga.engine.entities.object;

import ooga.engine.entities.Entity;
import ooga.engine.obstacles.Obstacle;
import ooga.engine.obstacles.Wall;

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

    public void leftCollideable(Entity entity) {
        cannotPass(entity);
    }


    public void rightCollideable(Entity entity) {
        cannotPass(entity);
    }

    public void bottomCollideable(Entity entity) {
        cannotPass(entity);
    }

    public void topCollideable(Entity entity) {
        cannotPass(entity);
    }

    private void cannotPass(Entity entity){
        if(!entity.getId().equals("player")){
            entity.setHitpoints(0);
        }
    }
}
