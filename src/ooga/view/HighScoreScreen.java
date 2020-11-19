package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import ooga.GameController;
import ooga.engine.games.HighScore;
import ooga.view.screens.Screen;

public class HighScoreScreen extends Screen {

    private Scene scene;
    private GameController gameController;

    public HighScoreScreen(GameController controller) {
        scene = new Scene(new Group());
        gameController = controller;
        setUpScene();
    }

    private void setUpScene() {
        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e->back());

        BorderPane root = new BorderPane();
        root.setTop(backButton);

        showHighScores();
    }

    private void showHighScores() {
    }

    private void back() {
        gameController.setScene(oldScene);
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
