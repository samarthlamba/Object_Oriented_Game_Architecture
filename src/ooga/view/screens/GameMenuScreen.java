package ooga.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ooga.GameController;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class GameMenuScreen extends Screen {

    protected static final double IMAGE_WIDTH = 400.0;
    protected static final double IMAGE_HEIGHT = 400.0;
    Pos MENU_POSITION = Pos.BOTTOM_CENTER;

    private GameController gameController;
    Scene scene;
//    String language = "en";
    ResourceBundle gameMenuButtonProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "gamemenubuttons");//_" + language);;
    Consumer<String> E;

    public GameMenuScreen(Consumer<String> e, GameController controller){
        gameController = controller;
        E = e; //TODO
        BorderPane root = new BorderPane();
        VBox center = new VBox();
        Menu menu = getMenu();
        menu.setAlignment(MENU_POSITION);
        Image gameTitle = setGameTitle();
        ImageView imageView = new ImageView(gameTitle);
        imageView.setY(100);
        center.setAlignment(Pos.CENTER);
//        center.setPadding(new Insets(100,100,100,100));
//        center.setSpacing(70.0);
        center.getChildren().addAll(imageView,menu);

        Button backButton = new Button("back");
        backButton.setOnMouseClicked(f->back());

        root.setTop(backButton);
        root.setCenter(center);
        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        scene.getStylesheets().addAll("gamebase.css");
    }

    private void back() {
        gameController.setScene(oldScene);
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
