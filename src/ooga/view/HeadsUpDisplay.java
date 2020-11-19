package ooga.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import ooga.GameController;
import ooga.view.screens.PauseScreen;
import ooga.view.screens.Screen;

import java.util.ResourceBundle;
import java.util.function.Consumer;


public class HeadsUpDisplay extends HBox {
    private static final double STATUS_SPACING = 20.0;
    private static final double LABEL_SPACING = 10.0;
    private static final double HUD_ICON_WIDTH = 15;
    private static final double HUD_ICON_HEIGHT = HUD_ICON_WIDTH;
    private static final ResourceBundle hudResources = ResourceBundle.getBundle(Screen.DEFAULT_RESOURCE_PACKAGE + "hud");
    private static final String styles =  "mario.css";
    private GameController gameController;
    private int points;
    private int lives;
    Text pointsText;
    Text livesText;

    public HeadsUpDisplay(GameController controller, int p, int l) {
        points = p;
        lives = l;
        gameController = controller;
    }

    public void setUpHud(Scene oldScene,Screen settings,Runnable goToMenu, Runnable restart, Consumer changeTheme) {
        ImagePattern livesImage = new ImagePattern(new Image(hudResources.getString("heart")));
        Shape livesLabel = new Rectangle(HUD_ICON_WIDTH,HUD_ICON_HEIGHT);
        livesLabel.setFill(livesImage);
        livesText = new Text(Integer.toString(lives)); //TODO
        ImagePattern pointsImage = new ImagePattern(new Image(hudResources.getString("coin")));
        Shape pointsLabel = new Rectangle(HUD_ICON_WIDTH,HUD_ICON_HEIGHT);
        pointsLabel.setFill(pointsImage);

        pointsText = new Text(Integer.toString(points));

        Button settingsButton = new Button();
        Button pauseButton = new Button();

        settingsButton.setFocusTraversable(false);
        pauseButton.setFocusTraversable(false);

        HBox lifeStatus = new HBox(livesLabel, livesText);
        lifeStatus.setSpacing(LABEL_SPACING);
        HBox pointsStatus = new HBox(pointsLabel, pointsText);
        pointsStatus.setSpacing(LABEL_SPACING);
        HBox status = new HBox(lifeStatus, pointsStatus);
        status.setSpacing(STATUS_SPACING);

        HBox buttons = new HBox(pauseButton);//settings button
        buttons.setSpacing(15);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        this.getStylesheets().add(styles);
        pauseButton.getStyleClass().add(hudResources.getString("pause"));
        pauseButton.setOnMouseClicked(e->{pauseFunction(oldScene,settings,goToMenu,restart,changeTheme);});
        settingsButton.getStyleClass().add(hudResources.getString("settings"));

        this.getChildren().addAll(status,buttons);
        this.setHgrow(buttons, Priority.ALWAYS);
        this.setPadding(new Insets(5,10,0,10)); //TODO constants
        this.setFillHeight(true);

    }

    private void pauseFunction(Scene oldScene, Screen settingsScreen, Runnable goToMenu, Runnable restart, Consumer changeTheme) {
        PauseScreen pauseScreen = new PauseScreen(oldScene,settingsScreen,gameController,goToMenu,restart,changeTheme);
        gameController.setScene(pauseScreen.getView());
        gameController.pauseTimeline();
    }

    public void update(int p, int l) {
        if (points != p) {
            points = p;
            pointsText.setText(Integer.toString(points));
        }
        if (lives != l) {
            lives = l;
            livesText.setText(Integer.toString(lives));
        }
    }

}
