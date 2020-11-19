package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.GameController;
import ooga.GameEndStatus;
import ooga.engine.games.GamePlay;
import ooga.view.screens.*;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import ooga.loader.FactoryException;


public class Display {//implements Viewer{

    private static final String PATH_TO_GAME_MENUS = "ooga.view.screens.%sMenuScreen";
    private static final ResourceBundle GAME_LABELS = ResourceBundle.getBundle("ooga.view.resources.mainmenubuttons");//_en");//TODO
    private static final ResourceBundle LEVEL_FILE_LOCATIONS = ResourceBundle.getBundle("LevelFileLocations");

    private GameController gameController;
    private GamePlayScreen gameScreen;
    private String gameTitle;
    private Screen settingsScreen;
    private Screen mainMenu;
//>>>>>>> jnh24

    public Display(GameController gameController) {
        this.gameController = gameController;
        settingsScreen = new SettingsScreen(new Scene(new Group()), gameController,this::changeTheme);
    }

    public void setMainMenuScreen() {
        mainMenu = new MainMenuScreen(this::setGameMenuScreen, settingsScreen, gameController);
        gameController.setScene(mainMenu.getView());
    }

//<<<<<<< HEAD
//=======
//    private void launchGame(String levelChosen) {
//        String gameLevelComboChosen = String.format("%s,%s", gameTitle,levelChosen);
//        String filePath = LEVEL_FILE_LOCATIONS.getString(gameLevelComboChosen);
//        gameController.launchGame(filePath);
//        gameController.setId(gameLevelComboChosen);
//        setGameDisplay(gameController.getGame());
//    }
//
//    private void restartGame() {
//        gameController.restartGame();
//        setGameDisplay(gameController.getGame());
//    }

//>>>>>>> jnh24
    public void setGameDisplay(GamePlay newGame) {
//        public void setGameDisplay(GamePlay newGame, Consumer pause, Consumer play, Consumer restart) {
//        gameScreen = new GamePlayScreen(newGame);
        gameScreen = new GamePlayScreen(newGame, gameController, settingsScreen, this::setGameMenuScreenFromSettings, this::restartGame, this::changeTheme);

//        gameScreen = new GamePlayScreen(pause, play, restart);
        if (newGame !=null) {
            gameScreen.setGameScreen(newGame);
        } else {
            throw new RuntimeException("Game never defined"); //TODO maybe remove
        }
        gameController.setScene(gameScreen.getView());
    }

    private void changeTheme(Object themeObject) {
        String theme = (String) themeObject;

        //TODO change theme using properties files
    }

    public void updateDisplay() {
        gameScreen.update();
    }

    private void setGameMenuScreenFromSettings() {
        setGameMenuScreen(gameTitle);
    }
//<<<<<<< HEAD
    public void setGameMenuScreen (String gameLabel) { //TODO
        this.gameTitle = gameLabel;
        Screen gameMenu;

//=======

//
//    private void setGameMenuScreen (String gameLabel) { //TODO
//        this.gameTitle = gameLabel;
//>>>>>>> jnh24
        String gameClassName = GAME_LABELS.getString(gameLabel);
        Consumer<String> launchGame = this::launchGame;
        Consumer<String> randomGame = this::randomGame;
        try {
//<<<<<<< HEAD
            Constructor menuConstructor = Class.forName(String.format(PATH_TO_GAME_MENUS,gameClassName))
                .getDeclaredConstructor(Consumer.class, Consumer.class, GameController.class);
            gameMenu = (Screen) menuConstructor.newInstance(launchGame,randomGame,gameController);
//=======
//            Constructor ruleCellTypeCons = Class.forName("ooga.view.screens." + gameClassName + "MenuScreen").getDeclaredConstructor(Consumer.class,GameController.class);
//            gameMenu = (Screen) ruleCellTypeCons.newInstance((Consumer<String>) this::launchGame, gameController);
//>>>>>>> jnh24
        } catch (Exception er) {
            er.printStackTrace();//TODO
            throw new RuntimeException ("Error in reflection");//TODO
        }
        gameMenu.setOldScene(mainMenu.getView());
        gameController.setScene(gameMenu.getView());
    }

    public void setSplashScreen(GameEndStatus displayKey) {
        SplashScreen resultScreen = new SplashScreen(displayKey,this::setMainMenuScreen,this::restartGame);
        gameController.setScene(resultScreen.getView());
    }
//<<<<<<< HEAD

    private void launchGame(String levelChosen) {
        String gameLevelComboChosen = String.format("%s,%s", gameTitle,levelChosen);
        gameController.setId(gameLevelComboChosen);
        try {
            gameController.launchGame(LEVEL_FILE_LOCATIONS.getString(gameLevelComboChosen));
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
    
//=======
//>>>>>>> jnh24

    public void test() {
        Group root = new Group();
        Scene scene = new Scene(root,500,500);
        gameController.setScene(scene);
    }
}


