package ooga.engine.obstacles;


import javafx.scene.Node;
import ooga.engine.entities.Moveables;

import java.util.Map;

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
        return new Ladder(obstacleWidth, obstacleHeight, initialX, initialY);
    }

    @Override
    public void leftCollideable(Moveables entity) {}

    @Override
    public void rightCollideable(Moveables entity) {}

    @Override
    public void bottomCollideable(Moveables entity) {}

}