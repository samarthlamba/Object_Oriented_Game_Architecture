package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public class GameMenuScreen extends Screen{

    private static final double IMAGE_WIDTH = 400.0;
    private static final double IMAGE_HEIGHT = 400.0;
    private static final String TITLE_PATH = "/ooga/view/resources/images/%sTitle.png";
    private final String gameTitle;
    Pos MENU_POSITION = Pos.BOTTOM_CENTER;

    Scene scene;
    String language = "eng";
    ResourceBundle gameMenuButtonProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "gamemenubuttons_" + language);;
    private final Consumer<String> randomLevel;
    private final Consumer<String> launchGame;

    public GameMenuScreen(String gameTitle, Consumer launchGame, Consumer randomLevel){
        this.gameTitle = gameTitle;
        this.launchGame = launchGame;
        this.randomLevel = randomLevel;
        VBox root = new VBox();
        Menu menu = getMenu();
        menu.setAlignment(MENU_POSITION);
        Image titleImage = new Image(String.format(TITLE_PATH,gameTitle),IMAGE_WIDTH,IMAGE_HEIGHT,true,true);
        ImageView imageView = new ImageView(titleImage);
//        imageView.setX(0);
//        imageView.setX();
        imageView.setY(100);
        root.setAlignment(Pos.TOP_CENTER);
        //root.setPadding(new Insets(100,100,100,100));
        root.setSpacing(70.0);
        root.getChildren().addAll(imageView,menu);
        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        scene.getStylesheets().addAll("gamebase.css");
    }

    private void invokeCorrectMethod(String buttonName) {
        if(buttonName.equals("random")) {
            randomLevel.accept(gameTitle);
        }
        else{
            launchGame.accept(buttonName);
        }
    }

    private Menu getMenu() {
        Menu menu = new Menu(gameMenuButtonProperties, this::invokeCorrectMethod);//TODO
        return menu;
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
