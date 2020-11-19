package ooga.view.screens;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.GameController;
import ooga.view.KeyBindingScreen;

import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Consumer;

public class SettingsScreen extends Screen{

    private Scene scene;
//    private Scene oldScene;
    private GameController gameController;

    public SettingsScreen(Scene pauseScreen, GameController controller, Consumer changeTheme) {
        gameController = controller;
        setScene(changeTheme);
//        oldScene = pauseScreen;
    }

    private void setScene(Consumer changeTheme){
        BorderPane root = new BorderPane();
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);

        ComboBox comboBox = new ComboBox();
        comboBox.setVisible(!gameController.isActive());
        Set themes = ResourceBundle.getBundle("themes").keySet();
        comboBox.setItems(FXCollections.observableArrayList(themes));
        comboBox.setValue(themes.toArray()[0]);
//        comboBox.setOnAction(e->changeTheme.accept(comboBox.getValue()));
        comboBox.setOnAction(e->gameController.setTheme((String)comboBox.getValue()));
        buttons.getChildren().add(comboBox);

        ToggleButton day = new ToggleButton("day");
        ToggleButton night = new ToggleButton("night");
        ToggleGroup toggleGroup = new ToggleGroup();
        day.setToggleGroup(toggleGroup);
        night.setToggleGroup(toggleGroup);
        HBox background = new HBox();
        background.setAlignment(Pos.CENTER);
        background.getChildren().add(day);
        background.getChildren().add(night);
        buttons.getChildren().add(background);

        Button keyBindButton = new Button("change keys");
        keyBindButton.setOnMouseClicked(e->accessKeyBindingScreen());
        buttons.getChildren().add(keyBindButton);

        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e->back());
        backButton.setAlignment(Pos.TOP_RIGHT);
        root.setTop(backButton);
        root.setCenter(buttons);
        scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    private void accessKeyBindingScreen() {
        KeyBindingScreen keyBindingScreen = new KeyBindingScreen(scene, gameController);
        gameController.setScene(keyBindingScreen.getView());
    }

    private void back() {
        gameController.setScene(oldScene);
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
