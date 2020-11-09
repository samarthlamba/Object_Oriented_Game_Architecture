package ooga.engine.obstacles;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallAndObstacleTest {
    Obstacle testWall = new Wall(100, 100, 10, 10);

    @Test
    void moveXBy() {
        double initialY = testWall.getBoundsInParent().getCenterY();
        testWall.moveXBy(10);
        assertEquals(70, testWall.getBoundsInParent().getCenterX());
        testWall.moveXBy(10);
        assertEquals(initialY, testWall.getBoundsInParent().getCenterY());
        assertEquals(80, testWall.getBoundsInParent().getCenterX());
        testWall.moveXBy(-30);
        assertEquals(50, testWall.getBoundsInParent().getCenterX());
        assertEquals(initialY, testWall.getBoundsInParent().getCenterY());
    }


    @Test
    void moveY() {
        double initialX = testWall.getBoundsInParent().getCenterX();
        testWall.moveYBy(15);
        assertEquals(75, testWall.getBoundsInParent().getCenterY());
        assertEquals(initialX, testWall.getBoundsInParent().getCenterX());
        testWall.moveYBy(-25);
        assertEquals(50, testWall.getBoundsInParent().getCenterY());
        assertEquals(initialX, testWall.getBoundsInParent().getCenterX());
    }

    @Test
    void moveContinouslyXBy() {
    }

    @Test
    void moveContinouslyYBy() {
    }

    @Test
    void getNodeObject() {
        testWall = new Wall(100, 100, 10, 10);
        testWall.getNode();
        Node ans = new Rectangle(10, 10, 100, 100);
        assertEquals(ans.getLayoutBounds(), ans.getLayoutBounds());

    }
}