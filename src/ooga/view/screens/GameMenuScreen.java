package ooga.view.screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ooga.GameController;
import ooga.view.HighScoreScreen;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class GameMenuScreen extends Screen {

    protected static final double IMAGE_WIDTH = 400.0;
    protected static final double IMAGE_HEIGHT = 400.0;
    Pos MENU_POSITION = Pos.BOTTOM_CENTER;
    private static final String TITLE_PATH = "/ooga/view/resources/images/%sTitle.png";


    private final String gameTitle;
    private GameController gameController;
    Scene scene;
    ResourceBundle gameMenuButtonProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "gamemenubuttons");//_" + language);;
    private final Consumer<String> randomLevel;
    private final Consumer<String> launchGame;

    public GameMenuScreen(String gameTitle, Consumer launchGame, Consumer randomLevel, GameController controller){
        this.gameTitle = gameTitle;
        this.launchGame = launchGame;
        this.randomLevel = randomLevel;
        gameController = controller;
        BorderPane root = new BorderPane();
        VBox center = new VBox();
        Menu menu = getMenu();
        menu.setAlignment(MENU_POSITION);
        Image titleImage = setGameTitle();
        ImageView imageView = new ImageView(titleImage);
        imageView.setY(100);
        center.setAlignment(Pos.CENTER);

        center.getChildren().addAll(imageView,menu);

        Button backButton = new Button("back");//TODO
        backButton.setOnMouseClicked(f->back());
        Pane spacer = new Pane();

        Button highScoreButton = new Button("high scores"); //TODO image in css
        highScoreButton.setOnMouseClicked(ev->showHighScores());

        HBox topBar = new HBox(backButton,spacer,highScoreButton);
        topBar.setHgrow(spacer, Priority.ALWAYS);

        root.setTop(topBar);
        root.setCenter(center);

        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().addAll("gamebase.css");//TODO
    }

    private void showHighScores(){
        HighScoreScreen screen = new HighScoreScreen(gameController,gameTitle);
        screen.setOldScene(this.getView());
        gameController.setScene(screen.getView());
    }

    private void back() {
        gameController.setScene(oldScene);
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

    public abstract Image setGameTitle();

    @Override
    public Scene getView() {
        return scene;
    }
}
