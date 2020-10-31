package ooga.view;

import java.util.function.Consumer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class MainMenuScreen extends Screen{
    private Consumer<String> myBoy;
    private Scene myScene;

    public MainMenuScreen(Consumer<String> myBoy) {
        this.myBoy = myBoy;
        makeScene();
    }

    private void makeScene() {
        Button start = new Button("testFile.csv");
        start.setOnAction(e -> myBoy.accept(start.getText()));
        Group root = new Group();
        root.getChildren().add(start);
        myScene = new Scene(root,800,600);
        myScene.setFill(Color.AQUAMARINE);
    }

    @Override
    public Scene getView() {
        return myScene;
    }
}
