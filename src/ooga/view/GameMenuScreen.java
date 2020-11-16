package ooga.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public class GameMenuScreen extends Screen{

    private static final double IMAGE_WIDTH = 400.0;
    private static final double IMAGE_HEIGHT = 400.0;
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
        Image gameTitle = new Image("/ooga/view/resources/images/marioTitle.png",IMAGE_WIDTH,IMAGE_HEIGHT,true,true);
        ImageView imageView = new ImageView(gameTitle);
//        imageView.setX(0);
//        imageView.setX();
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

    @Override
    public Scene getView() {
        return scene;
    }
}
