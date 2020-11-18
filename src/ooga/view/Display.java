package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.GameController;
import ooga.GameEndStatus;
import ooga.engine.games.GamePlay;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import ooga.loader.FactoryException;

import static ooga.view.Screen.DEFAULT_RESOURCE_PACKAGE;


public class Display {//implements Viewer{

    private static final ResourceBundle GAME_LABELS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "mainmenubuttons_eng");
    private static final String PATH_TO_GAME_MENUS = "ooga.view.%sMenuScreen";


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

    public void setGameDisplay(GamePlay newGame) {
//        public void setGameDisplay(GamePlay newGame, Consumer pause, Consumer play, Consumer restart) {
//        gameScreen = new GamePlayScreen();
        gameScreen = new GamePlayScreen(newGame);

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
        GameMenuScreen gameMenu;
        String gameClassName = GAME_LABELS.getString(gameLabel);
        Consumer<String> launchGame = this::launchGame;
        Consumer<String> randomGame = this::randomGame;
        try {
            Constructor menuConstructor = Class.forName(String.format(PATH_TO_GAME_MENUS,gameClassName))
                .getDeclaredConstructor(Consumer.class, Consumer.class);
            gameMenu = (GameMenuScreen) menuConstructor.newInstance(launchGame,randomGame);
        } catch (Exception er) {
            er.printStackTrace();
            throw new RuntimeException ("Error in reflection");//TODO
        }
        gameController.setScene(gameMenu.getView());
    }

    public void setSplashScreen(GameEndStatus displayKey) {
        SplashScreen resultScreen = new SplashScreen(displayKey,this::setMainMenuScreen,this::restartGame);
        gameController.setScene(resultScreen.getView());
    }

    private void launchGame(String levelChosen) {
        String gameLevelComboChosen = String.format("%s%s", gameTitle,levelChosen);
        try {
            gameController.launchGame(gameLevelComboChosen);
            setGameDisplay(gameController.getGame());
        } catch (FactoryException e) {
            handleFactoryException(e);
        }
    }

    private void restartGame() {
        try {
            gameController.restartGame();
            setGameDisplay(gameController.getGame());
        }catch (FactoryException e) {
            handleFactoryException(e);
        }
    }

    private void randomGame(String gameTitle) {
        this.gameTitle = gameTitle;
        try {
            gameController.makeRandomGame(gameTitle);
            setGameDisplay(gameController.getGame());
        }catch (FactoryException e) {
            handleFactoryException(e);
        }
    }

    private void handleFactoryException( FactoryException e) {
        Alert factoryAlert = new Alert(AlertType.ERROR,e.getMessage());
        factoryAlert.show();
        factoryAlert.setOnCloseRequest(d -> setMainMenuScreen());
    }
    

    public void test() {
        Group root = new Group();
        Scene scene = new Scene(root,500,500);
        gameController.setScene(scene);
    }
}


