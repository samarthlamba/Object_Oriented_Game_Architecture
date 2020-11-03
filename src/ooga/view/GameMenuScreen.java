package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public class GameMenuScreen extends Screen{

    Pos MENU_POSITION = Pos.CENTER;

    Scene scene;
    String language = "eng";
    ResourceBundle gameMenuButtonProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "gamemenubuttons_" + language);;
    Consumer<String> E;

    public GameMenuScreen(Consumer e){
        E = e; //TODO
        Menu menu = getMenu();
        menu.setAlignment(MENU_POSITION);
        scene = new Scene(menu, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private Menu getMenu() {
        Menu menu = new Menu(gameMenuButtonProperties, E);//TODO
        return menu;
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
