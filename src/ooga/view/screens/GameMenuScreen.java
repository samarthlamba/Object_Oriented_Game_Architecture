package ooga.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class GameMenuScreen extends Screen {

    protected static final double IMAGE_WIDTH = 400.0;
    protected static final double IMAGE_HEIGHT = 400.0;
    Pos MENU_POSITION = Pos.BOTTOM_CENTER;

    Scene scene;
    String language = "eng";
    ResourceBundle gameMenuButtonProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "gamemenubuttons_" + language);;
    Consumer<String> E;

    public GameMenuScreen(Consumer<String> e){
        E = e; //TODO
        VBox root = new VBox();
        Menu menu = getMenu();
        menu.setAlignment(MENU_POSITION);
        Image gameTitle = setGameTitle();
        ImageView imageView = new ImageView(gameTitle);
        imageView.setY(100);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(100,100,100,100));
        root.setSpacing(70.0);
        root.getChildren().addAll(imageView,menu);
        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        scene.getStylesheets().addAll("gamebase.css");
    }

    private Menu getMenu() {
        Menu menu = new Menu(gameMenuButtonProperties, E);//TODO
        return menu;
    }

    public abstract Image setGameTitle();

    @Override
    public Scene getView() {
        return scene;
    }
}
