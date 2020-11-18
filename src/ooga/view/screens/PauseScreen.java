package ooga.view.screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ooga.GameController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class PauseScreen extends Screen{

    private Scene scene;
    private Scene oldScene;
    private GameController gameController;
    private Screen settingsScreen;

    public PauseScreen(Scene scene, Screen settings, GameController controller, Runnable goToMenu, Runnable restart, Consumer changeTheme){
        setScene(goToMenu, restart, changeTheme);
        oldScene = scene;
        gameController = controller;
        settingsScreen = settings;
    }

    private void setScene(Runnable goToMenu, Runnable restart, Consumer changeTheme) {
        Collection<Button> buttons = makeButtons(goToMenu, restart, changeTheme);
        VBox root = new VBox();
        for(Button button : buttons) {
            root.getChildren().add(button);
        }
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);//TODO
        scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    private Collection<Button> makeButtons(Runnable goToMenu, Runnable restart, Consumer changeTheme) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button playButton = new Button("play");
        playButton.setOnMouseClicked(e->play());
        Button restartButton = new Button("restart");
        restartButton.setOnMouseClicked(e->{restart.run();});
        Button settingsButton = new Button("settings");
        settingsButton.setOnMouseClicked(e->{settingsFunction(changeTheme);});
        Button menuButton = new Button("Game Menu");
        menuButton.setOnMouseClicked(e->goToMenu.run());
        buttons.add(playButton);
        buttons.add(settingsButton);
        buttons.add(restartButton);
        buttons.add(menuButton);
        return buttons;
    }

    private void settingsFunction(Consumer changeTheme) {
//        SettingsScreen settingsScreen = new SettingsScreen(scene,gameController,changeTheme);
        settingsScreen.setOldScene(this.getView());
        gameController.setScene(settingsScreen.getView());
    }

    private void play() {
        gameController.setScene(oldScene);
        gameController.playTimeline();
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
