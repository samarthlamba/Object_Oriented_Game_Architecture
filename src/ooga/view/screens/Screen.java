package ooga.view.screens;

import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class Screen {//} implements Viewer{

    protected static final int SCREEN_WIDTH = 500; //TODO
    protected static final int SCREEN_HEIGHT = 500; //TODO
    public static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";

    public Scene oldScene;

    public abstract Scene getView();

    public void setOldScene(Scene scene) {
        oldScene = scene;
    }
}
