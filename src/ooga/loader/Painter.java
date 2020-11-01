package ooga.loader;

import java.util.ResourceBundle;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Painter {
  private static final ResourceBundle paints = ResourceBundle.getBundle("ClassDisplays");

  public static void paint(Shape toPaint, Class howToPaint) {
    String boy = paints.getString(howToPaint.getName());
    Paint color = Paint.valueOf(boy);
    toPaint.setFill(color);
  }
}
