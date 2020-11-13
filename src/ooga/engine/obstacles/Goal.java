package ooga.engine.obstacles;

import javafx.scene.Node;
import ooga.engine.entities.Entity;

public class Goal extends Obstacle{
    private static final String ID= "goal";
    public Goal(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
        super(obstacleWidth, obstacleHeight, initialX, initialY);
        setId(ID);
    }

    @Override
    public void leftCollideable(Entity entity) {
        entity.setWon(true);
    }

    @Override
    public void rightCollideable(Entity entity) {
        entity.setWon(true);
    }

    @Override
    public void bottomCollideable(Entity entity) {
        entity.setWon(true);
    }

    @Override
    public void topCollideable(Entity entity){
        entity.setWon(true);
    }

}
