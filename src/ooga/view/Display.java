package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import ooga.GameController;
import ooga.engine.games.GamePlay;
import ooga.view.screens.*;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class Display {//implements Viewer{

private static final ResourceBundle GAME_LABELS = ResourceBundle.getBundle("ooga.view.resources.mainmenubuttons_eng");//TODO
    private static final ResourceBundle LEVEL_FILE_LOCATIONS = ResourceBundle.getBundle("LevelFileLocations");


    private GameController gameController;
    private GamePlayScreen gameScreen;
    private String gameTitle;

    public Display(GameController gameController) {
        this.gameController = gameController;
    }

    public void setMainMenuScreen() {
        Screen screen = new MainMenuScreen(this::setGameMenuScreen);
        gameController.setScene(screen.getView());
    }

    private void launchGame(String levelChosen) {
        String gameLevelComboChosen = String.format("%s,%s", gameTitle,levelChosen);
        String filePath = LEVEL_FILE_LOCATIONS.getString(gameLevelComboChosen);
        gameController.launchGame(filePath);
        setGameDisplay(gameController.getGame());
    }

    private void restartGame() {
        gameController.restartGame();
        setGameDisplay(gameController.getGame());
    }

    public void setGameDisplay(GamePlay newGame) {
//        public void setGameDisplay(GamePlay newGame, Consumer pause, Consumer play, Consumer restart) {
//        gameScreen = new GamePlayScreen(newGame);
        gameScreen = new GamePlayScreen(newGame, gameController);

//        gameScreen = new GamePlayScreen(pause, play, restart);
        if (newGame !=null) {
            gameScreen.setGameScreen(newGame);
        } else {
            throw new RuntimeException("Game never defined"); //TODO maybe remove
        }

        gameController.setScene(gameScreen.getView());
    }

    public void updateDisplay() {
        gameScreen.update();//TODo
    }

    public void setGameMenuScreen (String gameLabel) { //TODO
        this.gameTitle = gameLabel;
        Screen gameMenu;
        String gameClassName = GAME_LABELS.getString(gameLabel);
        try {
            Constructor ruleCellTypeCons = Class.forName("ooga.view." + gameClassName + "MenuScreen").getDeclaredConstructor(Consumer.class);
            gameMenu = (Screen) ruleCellTypeCons.newInstance((Consumer<String>) this::launchGame);
        } catch (Exception er) {
            er.printStackTrace();
            throw new RuntimeException ("Error in reflection");//TODO
        }
        gameController.setScene(gameMenu.getView());
    }

    public void setSplashScreen(String displayKey) {
        SplashScreen resultScreen = new SplashScreen(displayKey,this::setMainMenuScreen,this::restartGame);
//        SplashScreen resultScreen = new SplashScreen(displayKey,this::setMainMenuScreen,gameScreen.restartGame());

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


