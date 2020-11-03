package ooga.view;

import java.util.function.Consumer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.lang.reflect.Method;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainMenuScreen extends Screen{

    Pos MENU_POSITION = Pos.CENTER;
    Consumer<String> E;

    Scene scene;
    String language = "eng";
    ResourceBundle mainMenuButtonProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "mainmenubuttons_" + language);;

    public MainMenuScreen(Consumer<String> e){//TODO
        E = e; //TODO
        Menu menu = getMenu();
        menu.setAlignment(MENU_POSITION);
        scene = new Scene(menu, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private Menu getMenu() {
        Menu menu = new Menu(mainMenuButtonProperties, E);//TODO
        return menu;
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
