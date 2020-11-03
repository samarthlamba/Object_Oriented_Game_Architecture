package ooga.view;

import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class Screen {//} implements Viewer{

    protected static final int SCREEN_WIDTH = 500; //TODO
    protected static final int SCREEN_HEIGHT = 500; //TODO
    protected static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";

    public abstract Scene getView();
}
