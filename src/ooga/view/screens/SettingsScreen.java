package ooga.view.screens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.GameController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Consumer;

public class SettingsScreen extends Screen{

    private Scene scene;
    private Scene oldScene;
    private GameController gameController;

    public SettingsScreen(Scene pauseScreen, GameController controller, Consumer changeTheme) {
        setScene(changeTheme);
        oldScene = pauseScreen;
        gameController = controller;
    }

    private void setScene(Consumer changeTheme){
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);

        ComboBox comboBox = new ComboBox();
        Set themes = ResourceBundle.getBundle("themes").keySet();
        comboBox.setItems(FXCollections.observableArrayList(themes));
        comboBox.setValue(themes.toArray()[0]);
        comboBox.setOnAction(e->changeTheme.accept(comboBox.getValue())); //Display.changeTheme
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

        scene = new Scene(buttons,SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
