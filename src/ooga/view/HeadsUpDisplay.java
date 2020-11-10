package ooga.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


public class HeadsUpDisplay extends HBox {
    private static final double STATUS_SPACING = 20.0;
    private static final double LABEL_SPACING = 10.0;
    private static final double HUD_ICON_WIDTH = 15;
    private static final double HUD_ICON_HEIGHT = HUD_ICON_WIDTH;
    private static final ResourceBundle hudResources = ResourceBundle.getBundle(Screen.DEFAULT_RESOURCE_PACKAGE + "hud");
    private static final String styles =  "mario.css";

    //          private Consumer pauseConsumer;
//          private Consumer playConsumer;
//          private Consumer restartConsumer;


    public HeadsUpDisplay() {
//        public HeadsUpDisplay(Consumer pause, Consumer play, Consumer restart) {
//          pauseConsumer = pause;
//          playConsumer = play;
//          restartConsumer = restart;
        setUpHud();
    }

    private void setUpHud() {
        ImagePattern livesImage = new ImagePattern(new Image(hudResources.getString("heart")));
        Shape livesLabel = new Rectangle(HUD_ICON_WIDTH,HUD_ICON_HEIGHT);
        livesLabel.setFill(livesImage);
        Node lives = new Text(Integer.toString(getLives()));//TODO
        ImagePattern pointsImage = new ImagePattern(new Image(hudResources.getString("coin")));
        Shape pointsLabel = new Rectangle(HUD_ICON_WIDTH,HUD_ICON_HEIGHT);
        pointsLabel.setFill(pointsImage);

        Node points = new Text(Integer.toString(getPoints()));

        Button settingsButton = new Button();
        Button pauseButton = new Button();
//        pauseButton.setOnMouseClicked(e -> {
//          pauseConsumer.accept();
//          showPopUpScreen(playConsumer, restartConsumer);
//        });

        settingsButton.setFocusTraversable(false);
        pauseButton.setFocusTraversable(false);

        HBox lifeStatus = new HBox(livesLabel, lives);
        lifeStatus.setSpacing(LABEL_SPACING);
        HBox pointsStatus = new HBox(pointsLabel, points);
        pointsStatus.setSpacing(LABEL_SPACING);
        HBox status = new HBox(lifeStatus, pointsStatus);
        status.setSpacing(STATUS_SPACING);

//        pauseButton.setGraphic(imageView);

        HBox buttons = new HBox(pauseButton, settingsButton);
        buttons.setSpacing(15);
        buttons.setAlignment(Pos.CENTER_RIGHT);

//        buttons.

        this.getStylesheets().add(styles);
        pauseButton.getStyleClass().add(hudResources.getString("pause"));
        settingsButton.getStyleClass().add(hudResources.getString("settings"));
//        Image

        this.getChildren().addAll(status,buttons);
        this.setHgrow(buttons, Priority.ALWAYS);
        this.setPadding(new Insets(5,10,0,10));
        this.setFillHeight(true);

    }

    private int getPoints() {
        return 0; //TODO
    }

    private int getLives() {
        return 3; //tODO
    }

    //        public void showPopUpScreen(Consumer play, Consumer restart) {
//        Popup popup = new Popup;
//        Button playButton = new Button();
//        playButton.getStyleClass().add("play");
//        playButton.setOnMouseClicked(e -> playConsumer.accept());
//        Button restartButton = new Button();
//        restartButton.getStyleClass().add("restart");
//        restartButton.setOnMouseClicked(e -> restartConsumer.accept());
//        Hbox buttons = new HBox(playButton, restartButton)
//    }
}
