package ooga.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ooga.engine.entities.MovableBounds;
import ooga.engine.games.GamePlay;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class GamePlayScreen extends Screen{

    private static final String MAIN_PLAYER_ID = "player"; //TODO resource file
    private static final String OBSTACLE_NAME = "wall";
    private Scene scene;
    private double mainY;
    private double mainX;
    private double mainWidth;
    private double mainHeight;
    private ResourceBundle defaultKeyResources = ResourceBundle.getBundle("DefaultKeys");//tODO
    private ResourceBundle characterImages = ResourceBundle.getBundle("ooga.view.resources.CharacterImages");//tODO
    private List<Object> keys;
    private GamePlay game;
    private Group background;
    private Moveables mainPlayer;
    private Collection onScreen;
    //          private Consumer pauseConsumer;
//          private Consumer playConsumer;
//          private Consumer restartConsumer;

    public GamePlayScreen() {
//        public GamePlayScreen(Consumer pause, Consumer play, Consumer restart) {
//          pauseConsumer = pause;
//          playConsumer = play;
//          restartConsumer = restart;
    }

    public void setGameScreen(GamePlay givenGame) {
        Pane gamePane = new Pane(); //Todo justify
        background = new Group();
        game = givenGame;
        keys = new ArrayList<>();

        onScreen = background.getChildren();
        for (Moveables entity : game.getEntities()) {
            Shape view;
            if(!onScreen.contains(entity)) {
                if (entity.getId().equals(MAIN_PLAYER_ID)) {
                    mainPlayer = entity;
                    double width = entity.getNode().getLayoutBounds().getWidth();
                    double height = entity.getNode().getLayoutBounds().getHeight();
                    mainWidth = width;
                    mainHeight = height;
                }
                view = (Shape) entity.getNode();
                Image image = new Image(characterImages.getString(entity.getId()));
                view.setFill(new ImagePattern(image));
                view.setSmooth(true);

                background.getChildren().add(view);
            }
        }
        for (Collideable obstacle : game.getBackground()) {
            if(!onScreen.contains(obstacle)) {
                Shape view = (Shape) obstacle.getNodeObject();
                Image image = new Image(characterImages.getString(OBSTACLE_NAME)); //TODO
                ImagePattern p = new ImagePattern(image);//TODO remove
                view.setFill(p);

                background.getChildren().add(view);
            }
        }
        update();
        gamePane.getChildren().add(background);

        HeadsUpDisplay hud = new HeadsUpDisplay();
//        HeadsUpDisplay hud = new HeadsUpDisplay(pauseConsumer, playConsumer, restartConsumer);
        BorderPane root = new BorderPane();
        root.getStylesheets().add("mario.css");//TODO
        root.setCenter(gamePane);

        setKeys();
        root.setTop(hud);
        scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);//todo
        bindKeys();
    }

    private void setKeys() {
        for (String key : defaultKeyResources.keySet()) {
            keys.add(KeyCode.valueOf(key));
        }
    }

    private void bindKeys() { //TODO
        scene.setOnKeyPressed(e -> handleKey(e.getCode()));
    }

    private void handleKey(KeyCode code) {
        if (keys.contains(code)) {
            String methodName = defaultKeyResources.getString(code.toString());
            try {
                Method method = game.getClass().getMethod(methodName);
                method.invoke(game);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    private Rectangle addEntityToScene(double xPos, double yPos, double width, double height) {
//        Rectangle rect = new Rectangle(xPos,yPos,width,height);
//        rect.setFill(Color.BLUE); //TODO
//        return rect;
//    }

    public void update(){
        mainX = mainPlayer.getCenterX() - mainWidth/2;
        mainY = mainPlayer.getMaxY() - mainHeight;
        double sceneShiftX = -(mainX - (SCREEN_WIDTH/2 - mainWidth/2));
        double sceneShiftY = -(mainY - (SCREEN_HEIGHT/2 - mainHeight));
        background.setTranslateX(sceneShiftX);
        background.setTranslateY(sceneShiftY);
    }

    public void spawn(MovableBounds entity){}

    public void remove(MovableBounds entity){}

    @Override
    public Scene getView() {
        return scene;
    }

    public void spawn(Collection<Entity> entities) {
        try {
            background.getChildren().addAll(entities);
        } catch (IllegalArgumentException e) {
            for (Entity entity : entities) {
                if (background.getChildren().contains(entity)) {
                    background.getChildren().remove(entity);
                }
            }
        }
    }

    public void remove(Collection<Entity> entities) {
        for (Entity entity : entities) {
            if (background.getChildren().contains(entity)) {
                background.getChildren().remove(entity);
            }
        }
    }
}
