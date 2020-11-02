package ooga.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ooga.engine.obstacles.Obstacle;
import ooga.engine.obstacles.Wall;
import org.junit.jupiter.api.Test;

public class PainterTest {
  private final ResourceBundle classDisplayBundle = ResourceBundle.getBundle("ClassDisplays");

  @Test
  public void testPaintsClassPresent() {
    Wall wall = new Wall(50,50,50,50);
    Painter.paint((Shape) wall.getNodeObject(),Wall.class);
    Color expectedColor = Color.valueOf(classDisplayBundle.getString("ooga.engine.obstacles.Wall"));
    assertEquals(expectedColor,((Shape) wall.getNodeObject()).getFill());
  }

  @Test
  public void testPaintsClassThatDoesNotExistThrowsError() {
    Rectangle someRectangle = new Rectangle(50,50,50,50);
    assertThrows(MissingResourceException.class, () -> Painter.paint(someRectangle, User.class));
  }

}
