package ooga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;
import ooga.util.DukeApplicationTest;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.junit.jupiter.api.Test;

public class TimelineManagerTest extends DukeApplicationTest{
  private Timeline testTimeline;
  private TimelineManager testManager;
  private Rectangle testRectangle;

  @Override
  public void start(Stage stage) {
    testRectangle = new Rectangle(50,50, Color.RED);
    Scene myScene = new Scene(new Group(testRectangle),200,200);
    stage.setScene(myScene);
    stage.show();
    testTimeline = new Timeline();
    testTimeline.setCycleCount(5);
    testManager = new TimelineManager(testTimeline);
  }


  @Test
  public void testTimelineRunsOnPlay() {
    final double initialX = testRectangle.getX();
    final double initialY = testRectangle.getY();
    KeyFrame moveRectangle = new KeyFrame(Duration.millis(100),e -> {
      testRectangle.setX(testRectangle.getX()+10);
    });
    testTimeline.getKeyFrames().add(moveRectangle);
    testManager.play();
    sleep(550);
    final double finalX = testRectangle.getX();
    final double finalY = testRectangle.getY();
    assertEquals(initialX+50, finalX);
    assertEquals(initialY, finalY);
  }

  @Test
  public void testTimelineStopsOnPause() {
    final double initialX = testRectangle.getX();
    final double initialY = testRectangle.getY();
    KeyFrame moveRectangle = new KeyFrame(Duration.millis(100),e -> {
      testRectangle.setX(testRectangle.getX()+10);
    });
    testTimeline.getKeyFrames().add(moveRectangle);
    testManager.play();
    sleep(150);
    testManager.pause();
    final double finalX = testRectangle.getX();
    final double finalY = testRectangle.getY();
    assertEquals(initialX+10, finalX);
    assertEquals(initialY, finalY);
  }

}
