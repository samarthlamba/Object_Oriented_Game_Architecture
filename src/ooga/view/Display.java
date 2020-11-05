package ooga.view;

import javafx.scene.Group;
import javafx.scene.Node;
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
    private GamePlayScreen gameScreen;

    public Display(Stage initialStage) {
        stage = initialStage;
    }

    public void setMainMenuScreen(Consumer<String> e) {
        Screen screen = new MainMenuScreen(e);
        stage.setScene(screen.getView());
    }

    public void setGameDisplay(GamePlay newGame) {
        game = newGame;
        gameScreen = new GamePlayScreen();
        if (game!=null) {
            gameScreen.setGameScreen(game);
        } else {
            throw new RuntimeException("Game never defined"); //TODO maybe remove
        }

        stage.setScene(gameScreen.getView());
    }
    public void updateDisplay() {
        gameScreen.update();
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

    public Screen getGameMenu(Consumer<String> e) {
        Screen gameMenu = new SuperMarioBrosMenuScreen(e);
        return gameMenu;
    }
}


