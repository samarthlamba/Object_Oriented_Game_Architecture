package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import ooga.GameController;
import ooga.TimelineManager;
import ooga.engine.games.GamePlay;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static ooga.view.Screen.DEFAULT_RESOURCE_PACKAGE;


public class Display {//implements Viewer{

private static final ResourceBundle GAME_LABELS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "mainmenubuttons_eng");;

    private GameController gameController;
    private GamePlay newGame;
    private GamePlayScreen gameScreen;
    private TimelineManager timelineManager;

    public Display(GameController gameController) {
        this.gameController = gameController;
    }

    public void setMainMenuScreen() {
        Screen screen = new MainMenuScreen(gameController);
        gameController.setScene(screen.getView());
    }

    public void setGameDisplay(GamePlay newGame) {
//        public void setGameDisplay(GamePlay newGame, Consumer pause, Consumer play, Consumer restart) {
//        gameScreen = new GamePlayScreen();
        gameScreen = new GamePlayScreen(this.newGame);

//        gameScreen = new GamePlayScreen(pause, play, restart);
        if (this.newGame !=null) {
            gameScreen.setGameScreen(this.newGame);
        } else {
            throw new RuntimeException("Game never defined"); //TODO maybe remove
        }

        gameController.setScene(gameScreen.getView());
    }

    public void updateDisplay() {
        gameScreen.update();//TODo
    }

    public void setGameMenuScreen (String gameLabel) { //TODO
        Screen gameMenu;
        String gameClassName = GAME_LABELS.getString(gameLabel);
        try {
            Constructor ruleCellTypeCons = Class.forName("ooga.view." + gameClassName + "MenuScreen").getDeclaredConstructor(Consumer.class);
            gameMenu = (Screen) ruleCellTypeCons.newInstance(gameController::launchGame);
        } catch (Exception er) {
            er.printStackTrace();
            throw new RuntimeException ("Error in reflection");//TODO
        }
        gameController.setScene(gameMenu.getView());
    }

    public void setSplashScreen(String displayKey) {
        SplashScreen resultScreen = new SplashScreen(displayKey,gameController);
        gameController.setScene(resultScreen.getView());
    }

    public void test() {
        Group root = new Group();
        Scene scene = new Scene(root,500,500);
        gameController.setScene(scene);
    }

    public Screen getGameMenu(Consumer<String> e) {
        Screen gameMenu = new SuperMarioBrosMenuScreen(e);
        return gameMenu;
    }
}


