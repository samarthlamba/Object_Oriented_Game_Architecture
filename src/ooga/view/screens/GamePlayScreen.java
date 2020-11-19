package ooga.view.screens;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
//<<<<<<< HEAD:src/ooga/view/GamePlayScreen.java
import ooga.engine.entities.Entity;
import ooga.engine.entities.MovableBounds;
import ooga.engine.games.GamePlay;
import ooga.engine.obstacles.Unmovable;
import ooga.loader.AnimationBrain;
//=======
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ooga.GameController;
import ooga.engine.entities.MovableBounds;
import ooga.engine.games.GamePlay;
import ooga.view.FiniteStateMachineAnimation;
import ooga.view.HeadsUpDisplay;
import ooga.view.UpdateObjectsOnScreen;
//>>>>>>> jnh24:src/ooga/view/screens/GamePlayScreen.java

import java.awt.*;
import java.lang.reflect.Method;
import java.util.*;
//<<<<<<< HEAD:src/ooga/view/GamePlayScreen.java
import java.util.List;
import ooga.loader.FactoryException;
//=======
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
//>>>>>>> jnh24:src/ooga/view/screens/GamePlayScreen.java

public class GamePlayScreen extends Screen implements UpdateObjectsOnScreen {

    private static final String MAIN_PLAYER_ID = "player"; //TODO resource file
    private static final String OBSTACLE_NAME = "wall";
    private static final String CHARACTER_IMAGES = "CharacterImages";
    private static final ResourceBundle GAME_STYLESHEETS = ResourceBundle.getBundle("GameStylesheets");
    private static final String DEFAULT = "Default";
    private static final ImagePattern DEFAULT_IMAGE = new ImagePattern(new Image("/images/defaultObject.png"));
    private final Runnable goToMenu;
    private final Runnable restart;
    private final Consumer changeTheme;
    private Scene scene;
    private double mainY;
    private double mainX;
    private double mainWidth;
    private double mainHeight;
    private Rectangle rec;
    private Map<FiniteStateMachineAnimation, ImageView> animations = new HashMap<>();
    private final ResourceBundle defaultKeyResources = ResourceBundle.getBundle("KeyBindings");
    private Map<KeyCode,String> keys;
    private GamePlay game;
    private Group background;
    private MovableBounds mainPlayer;
    private Collection onScreen;
    private Map<String, ImagePattern> characterImages;
    private GameController gameController;
    private BorderPane primaryRoot;
    private HeadsUpDisplay hud;
    private Screen settings;

    private String imageBundle = CHARACTER_IMAGES;

//    public GamePlayScreen(GamePlay givenGame, GameController control) {
//        background = new Group();
//        game = givenGame;
//        keys = new HashMap<>();
//        gameController = control;
//    }
    public GamePlayScreen() {
        background = new Group();
        keys = new HashMap<>();
        goToMenu = null;
        restart = null;
        changeTheme = null;
    }
//,settings,goToMenu,restart,changeTheme
    public GamePlayScreen(GamePlay newGame, GameController controller, Screen settingsScreen, Runnable setGameMenuScreenFromSettings, Runnable restartGame, Consumer theme) {
        background = new Group();
        game = newGame;
        keys = new HashMap<>();
        gameController = controller;
        settings = settingsScreen;
        goToMenu = setGameMenuScreenFromSettings;
        restart = restartGame;
        changeTheme = theme;
        imageBundle = CHARACTER_IMAGES+gameController.getTheme();
    }


    public void setGameScreen(GamePlay givenGame){//}, Screen settings, Runnable goToMenu, Runnable restart, Consumer changeTheme) {
        game = givenGame;//TODO remove
        game.setDisplay(this);
        Pane gamePane = new Pane(); //Todo justify
        ResourceBundle characterImageResources = getImageResources(givenGame);
        characterImages = getImages(characterImageResources);
        onScreen = background.getChildren();
        addEntities((Collection<MovableBounds>) game.getEntities());
        addObstacles((Collection<Node>) game.getBackground());
        gamePane.getChildren().add(background);

        BorderPane root = new BorderPane();
        hud = new HeadsUpDisplay(gameController,game.getPoints(),mainPlayer.getHealth());
        String[] gamePlayStylesheets =
                GAME_STYLESHEETS.getString(gameController.getId().split(",")[0]).split(",");
        root.getStylesheets().addAll(gamePlayStylesheets);//TODO GAME_STYLESHEETS
//        root.getStylesheets();
        root.setCenter(gamePane);

        setKeys();
        root.setTop(hud);
        primaryRoot = root;
        scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);//todo
        hud.setUpHud(scene,settings,goToMenu,restart,changeTheme);
        bindKeys();
        update();
    }

//    public void restartGame() {
//        gameController.restartGame();
////        setGameDisplay(gameController.getGame());
//    }

    private void addObstacles(Collection<Node> obstacles) {
        for (Node obstacle : obstacles) {
            if(!onScreen.contains(obstacle)) {
                Shape view = (Shape) obstacle;
                String levelSpecifier = gameController.getId().split(",")[1];
                String imageKey = String.format("%s,%s",obstacle.getId(),levelSpecifier);
                view.setFill(characterImages.getOrDefault(imageKey,DEFAULT_IMAGE));
                background.getChildren().add(view);
            }
        }
    }

    private void addEntities(Collection<MovableBounds> entities) {
        for (MovableBounds entity : entities) {
            Shape view;
            if(!onScreen.contains(entity)) {
                if (entity.getId().equals(MAIN_PLAYER_ID)) {
                    mainPlayer = entity;
                    double width = entity.getNode().getLayoutBounds().getWidth();
                    double height = entity.getNode().getLayoutBounds().getHeight();
                    mainWidth = width;
                    mainHeight = height;
                }
                    try {
                        AnimationBrain x = new AnimationBrain(game.getClass().getSimpleName()+entity.getId());
                        FiniteStateMachineAnimation currentFSM = new FiniteStateMachineAnimation((Entity) entity, x);
                        animations.put(currentFSM, currentFSM.getCurrentAnimation().getImage());
                        background.getChildren().add(currentFSM.getCurrentAnimation().getImage());
                        continue;
                        //SAM: How can we properly handle this?
                    } catch (FactoryException e) {
                        view = (Shape) entity.getNode();
                        view.setFill(characterImages.getOrDefault(entity.getId(),DEFAULT_IMAGE));
                        background.getChildren().add(view);
                    }
                }

            }
        }



    private Map<String, ImagePattern> getImages(ResourceBundle characterImageResources) {
        Map<String,ImagePattern> map = new HashMap<>();
        for (String key : characterImageResources.keySet()) {
            Image image = new Image(characterImageResources.getString(key));
            map.put(key, new ImagePattern(image));
        }
        return map;
    }

    private ResourceBundle getImageResources(GamePlay givenGame) {
        try {
            String gameId = givenGame.getClass().getSimpleName();
            return ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE +gameId + imageBundle);
        } catch (MissingResourceException e) {
            return ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+DEFAULT+imageBundle);
        }
    }

    private void setKeys() {
        try{
            Properties prop = new Properties();
            InputStream stream = new FileInputStream("src/resources/KeyBindings.properties");
            prop.load(stream);
            stream.close();
            Set<Object> objets = prop.keySet();
            Set<String> keySet = objets.stream().map(object -> (String) object).collect(Collectors.toSet());
            for (String key : keySet) {
                keys.put(KeyCode.valueOf(key),prop.getProperty(key));
            }
        } catch (IOException e) {
            Set<String> keySet = defaultKeyResources.keySet();
            for(String key : keySet) {
                keys.put(KeyCode.valueOf(key),defaultKeyResources.getString(key.toString()));
            }
        }
    }

    private void bindKeys() { //TODO
        scene.setOnKeyPressed(e -> handleKey(e.getCode()));
    }

    private void handleKey(KeyCode code) {
        if (keys.containsKey(code)) {
            String methodName = keys.get(code);
            try {
                Method method = game.getClass().getMethod(methodName);
                method.invoke(game);
            } catch (Exception e) {
//<<<<<<< HEAD:src/ooga/view/GamePlayScreen.java
                //VISUALIZE AN ERROR HERE?
//=======
                System.out.println("No player here boss"); //TODO
//>>>>>>> jnh24:src/ooga/view/screens/GamePlayScreen.java
            }
        }
    }

    public void update(){
        mainX = mainPlayer.getCenterX() - mainWidth/2;
        mainY = mainPlayer.getMaxY() - mainHeight;
        double sceneShiftX = -(mainX - (SCREEN_WIDTH/2 - mainWidth/2));
        double sceneShiftY = -(mainY - (SCREEN_HEIGHT/2 - mainHeight));
        background.setTranslateX(sceneShiftX);
        background.setTranslateY(sceneShiftY);
//<<<<<<< HEAD:src/ooga/view/GamePlayScreen.java
        updateAnimations();
//=======
        hud.update(game.getPoints(),mainPlayer.getHealth());
//>>>>>>> jnh24:src/ooga/view/screens/GamePlayScreen.java
    }

    private void updateAnimations(){
        for (FiniteStateMachineAnimation fsm : animations.keySet()){
            fsm.update();
            background.getChildren().remove(animations.get(fsm));
            animations.put(fsm, fsm.getCurrentAnimation().getImage());
            background.getChildren().add(fsm.getCurrentAnimation().getImage());
        }
    }
    @Override
    public Scene getView() {
        return scene;
    }

    public void spawn(Collection<MovableBounds> entities) {
        addEntities(entities);
    }

    public void remove(Collection<MovableBounds> entities) {
        for (MovableBounds entity : entities) {
            background.getChildren().remove(entity.getNode());
        }
    }
}
