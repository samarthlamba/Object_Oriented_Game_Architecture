package ooga;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import ooga.util.DukeApplicationTest;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.junit.jupiter.api.Test;

public class TimelineManagerTest{
  private final Timeline myTimeline = new Timeline();
  private final TimelineManager testManager = new TimelineManager(myTimeline);

  @Test
  public void testTimelineRunsOnPlay() {
    JFXPanel panel = new JFXPanel();
    KeyFrame autoPass = new KeyFrame(Duration.seconds(5),e -> {
      System.out.println(Thread.currentThread());
      fail();
    });
    myTimeline.getKeyFrames().add(autoPass);
    myTimeline.setCycleCount(1);
    testManager.play();
    myTimeline.play();
  }

}
