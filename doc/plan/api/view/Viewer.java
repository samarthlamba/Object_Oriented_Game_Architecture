package ooga.view;

import javafx.scene.Scene;

/**
 * This interface represents the visual part of the program. Anything that is displayed on the screen
 * should implement viewer and the only-front facing method it needs is getView(), which returns
 * a JavaFX Scene to display on primary stage.
 */
public interface Viewer {

  /**
   * This method is called from Driver on anything that needs to be shown on the primary stage.
   * @return Some scene that represents the view of the object
   */
  Scene getView();
}
