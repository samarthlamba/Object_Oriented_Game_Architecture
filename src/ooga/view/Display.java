package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.engine.games.GamePlay;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static ooga.view.Screen.DEFAULT_RESOURCE_PACKAGE;


public class Display {//implements Viewer{

private static final ResourceBundle GAME_LABELS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "mainmenubuttons_eng");;

    private Stage stage;
    private GamePlay game;

    public Display(Stage initialStage) {
        stage = initialStage;
    }

    public void setMainMenuScreen(Consumer<String> e) {
        Screen screen = new MainMenuScreen(e);
        stage.setScene(screen.getView());
    }

    public void setGameDisplay(GamePlay newGame) {
        game = newGame;
        GamePlayScreen screen = new GamePlayScreen();
        if (game!=null) {
            screen.setGameScreen(game);
        } else {
            throw new RuntimeException("Game never defined"); //TODO maybe remove
        }
        stage.setScene(screen.getView());
    }
    public void updateDisplay() {
        game.updateLevel();
    }

    public void setGameMenuScreen (String gameLabel, Consumer<String> e) { //TODO
        Screen gameMenu;
        String gameClassName = GAME_LABELS.getString(gameLabel);
        try {
            Constructor ruleCellTypeCons = Class.forName("ooga.view." + gameClassName + "MenuScreen").getDeclaredConstructor(Consumer.class);
            gameMenu = (Screen) ruleCellTypeCons.newInstance(e);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException | ClassNotFoundException er) {
            er.printStackTrace();
            throw new RuntimeException ("Error in reflection");//TODO
        }
        stage.setScene(gameMenu.getView());
    }

    public void test() {
        Group root = new Group();
        Scene scene = new Scene(root,500,500);
        stage.setScene(scene);
    }
}


