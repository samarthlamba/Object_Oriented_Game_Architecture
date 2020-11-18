package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import ooga.GameController;
import ooga.view.screens.Screen;


public class KeyBindingScreen extends Screen {

    private Scene scene;
    private Scene oldScene;
    private GameController gameController;

    public KeyBindingScreen(Scene settingsScene, GameController controller) {
        gameController = controller;
        oldScene = settingsScene;
        setUpScene();
    }

    private void setUpScene() {
        BorderPane root = new BorderPane();

        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e -> back());

        root.setTop(backButton);

        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private void back() {
        gameController.setScene(oldScene);
    }


    @Override
    public Scene getView() {
        return scene;
    }
}
