package ooga.view.screens;

import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import ooga.GameController;

import java.util.ResourceBundle;

public class MainMenuScreen extends Screen {

    Pos MENU_POSITION = Pos.CENTER;
    Consumer<String> gameSelectorConsumer;
    Screen settingsScreen;
    GameController gameController;
    Scene scene;
    ResourceBundle mainMenuButtonProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "mainmenubuttons");

    public MainMenuScreen(Consumer<String> gameSelector, Screen settings, GameController controller){//TODO
        gameController = controller;
        settingsScreen = settings;
        gameSelectorConsumer = gameSelector; //TODO
        Menu menu = getMenu();
        menu.setAlignment(MENU_POSITION);
        Button settingsButton = new Button();
        settingsButton.setAlignment(Pos.TOP_RIGHT);
        settingsButton.setOnMouseClicked(e->showSettings());

        Pane spacer = new Pane();
        HBox settingsGroup = new HBox(spacer,settingsButton);
        settingsGroup.setHgrow(spacer, Priority.ALWAYS);
        settingsGroup.setPadding(new Insets(10,10,10,10)); //TODO

        BorderPane root = new BorderPane();

        root.setTop(settingsGroup);
        root.setCenter(menu);
        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add("gamebase.css");
        settingsButton.getStyleClass().add("settings");
    }

    private void showSettings() {
        settingsScreen.setOldScene(this.getView());
        gameController.setScene(settingsScreen.getView());
    }

//    private void changeBackground() {
//        root.getStyleClass().remove(root.getStyleClass().size()-1);
//        root.getStyleClass().add(string);
//        if (string.equals("day")){ string = "night";}
//        else {string = "day";}
//    }

    private Menu getMenu() {
        Menu menu = new Menu(mainMenuButtonProperties, gameSelectorConsumer);//TODO
        return menu;
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
