package ooga;

import javafx.animation.Timeline;

public class TimelineManager {
  private Timeline timeline;

  public TimelineManager(Timeline t) {
    this.timeline = t;
    t.play();
  }

  public void pause(){
    timeline.pause();
  }

  public void play() {
    System.out.println("This method is called");
    timeline.play();
    System.out.println("it is also finished");
  }

}