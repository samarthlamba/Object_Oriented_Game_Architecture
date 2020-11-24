package ooga.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ooga.GameController;
import ooga.engine.games.HighScore;
import ooga.engine.games.HighScoreObject;
import ooga.view.screens.Screen;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

public class HighScoreScreen extends Screen {

    private static final String HIGH_SCORE_TITLE_PATH = "/ooga/view/resources/images/highScore.png";
    private static final ResourceBundle LEVEL_FILE_LOCATIONS = ResourceBundle.getBundle("LevelFileLocations");
    private Scene scene;
    private GameController gameController;
    private HighScore highScores;
    private String game;
    private String level;
    private String scope;

    public HighScoreScreen(GameController controller, String gameName) {
        gameController = controller;
        game = gameName;
//        String gameId = gameController.getId();
//        highScores = new HighScore(LEVEL_FILE_LOCATIONS.getString(gameId));
        setUpScene();
    }

    private void setUpScene() {
        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e->back());

        Image titleImage = new Image(HIGH_SCORE_TITLE_PATH, 200,200,true,true);
        ImageView imageView = new ImageView(titleImage);

        ComboBox scopeComboBox = new ComboBox();
        ComboBox levelComboBox = new ComboBox();
        ArrayList scopeOptions = new ArrayList<>(Arrays.asList("Weekly","Global","All"));
        ArrayList levelOptions = new ArrayList<>(Arrays.asList("Level 1","Level 2","Level 3"));
        scopeComboBox.setItems(FXCollections.observableArrayList(scopeOptions));
        levelComboBox.setItems(FXCollections.observableArrayList(levelOptions));
        scopeComboBox.setValue("Weekly");
        scopeComboBox.setId("scope");
        levelComboBox.setValue("Level 1");
        VBox highScores = new VBox();
        highScores.setAlignment(Pos.CENTER);

        scopeComboBox.setOnAction(e->changeHighScores(scopeComboBox.getValue(),levelComboBox.getValue(),highScores));
        levelComboBox.setOnAction(ev->changeHighScores(scopeComboBox.getValue(),levelComboBox.getValue(),highScores));

        changeHighScores(scopeComboBox.getValue(),levelComboBox.getValue(),highScores);

        VBox vbox = new VBox(imageView,scopeComboBox,levelComboBox,highScores);
        vbox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();

        root.setCenter(vbox);
        root.setTop(backButton);

        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private void changeHighScores(Object scopeValue, Object levelValue, VBox vbox) {
        vbox.getChildren().clear();
        scope = (String) scopeValue;
        String levelRaw = (String) levelValue;
        level = levelRaw.replaceAll(" ","");


        HighScore highScoreInst = new HighScore(game+level);

        Object obj;
        try {
            Method myObjMethod = highScoreInst.getClass().getMethod("get" + scope + "HighScores");
            obj = myObjMethod.invoke(highScoreInst);
        } catch (Exception e) {
            throw new RuntimeException("Invalid combo box option");
        }
        HighScoreObject[] highScores = (HighScoreObject[]) obj;
        for (HighScoreObject highScore : highScores) {
            vbox.getChildren().add(new Label(Integer.toString(highScore.getScore())));
        }
    }

    private void showHighScores() {
    }

    private void back() {
        gameController.setScene(oldScene);
    }

    @Override
    public Scene getView() {
        return scene;
    }

    public String getInfo() {
        return game+level+scope;
    }
}
