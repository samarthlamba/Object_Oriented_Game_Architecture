package ooga.engine.obstacles;


import javafx.scene.Node;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Moveable;

public class Ladder extends Obstacle {
    private double initialX;
    private double initialY;
    private int obstacleWidth;
    private int obstacleHeight;
    private final static String ID = "wall";

    public Ladder(int obstacleWidth, int obstacleHeight, double initialX, double initialY) {
        super(obstacleWidth, obstacleHeight, initialX, initialY);
        this.initialX = initialX;
        this.initialY= initialY;
        this.obstacleHeight = obstacleHeight;
        this.obstacleWidth = obstacleWidth;
        setId(ID);
    }
    @Override
    public Node getNodeObject(){
        return this;
    }

    @Override
    public void leftCollideable(Entity entity) {}

    @Override
    public void rightCollideable(Entity entity) {}

    @Override
    public void bottomCollideable(Entity entity) {}

}