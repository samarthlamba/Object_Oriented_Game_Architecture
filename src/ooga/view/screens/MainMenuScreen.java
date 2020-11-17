package ooga.view.screens;

import java.util.function.Consumer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import ooga.view.Menu;

import java.util.ResourceBundle;

public class MainMenuScreen extends Screen {

    Pos MENU_POSITION = Pos.CENTER;
    Consumer<String> gameSelectorConsumer;

    Scene scene;
    String language = "eng";
    ResourceBundle mainMenuButtonProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "mainmenubuttons_" + language);;

    public MainMenuScreen(Consumer<String> gameSelector){//TODO
        gameSelectorConsumer = gameSelector; //TODO
        Menu menu = getMenu();
        menu.setAlignment(MENU_POSITION);
        scene = new Scene(menu, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add("gamebase.css");
    }

    private Menu getMenu() {
        Menu menu = new Menu(mainMenuButtonProperties, gameSelectorConsumer);//TODO
        return menu;
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
