package ooga.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ooga.engine.entities.MovableBounds;
import ooga.engine.games.GamePlay;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GamePlayScreen extends Screen{

    private Scene scene;
    private double mainY;
    private double mainX;
    private double mainWidth;
    private double mainHeight;
    private ResourceBundle defaultKeyResources = ResourceBundle.getBundle("DefaultKeys");//tODO
    private List<Object> keys;
    private GamePlay game;
    private Group background;
    private MovableBounds mainPlayer;

    public void setGameScreen(GamePlay givenGame) {
        Pane gamePane = new Pane(); //Todo justify
        background = new Group();
        game = givenGame;
        keys = new ArrayList<>();
        for (MovableBounds entity : game.getEntities()) {
            if (entity.getId().equals("player")) {
                mainPlayer = entity;
                double width = entity.getNode().getLayoutBounds().getWidth();
                double height = entity.getNode().getLayoutBounds().getHeight();
                mainWidth = width;
                mainHeight = height;
                Shape entityShape = (Shape) entity.getNode();
                entityShape.setFill(Color.BLUE);
                background.getChildren().add(entityShape);
            }
            else {
                Shape entityNode = (Shape) entity.getNode();
                entityNode.setFill(Color.GREEN);
                background.getChildren().add(entityNode);
            }
        }

        for (Node obstacle : game.getBackground()) {
            Shape obstacleNode = (Shape) obstacle;
            obstacleNode.setFill(Color.BROWN);
            background.getChildren().add(obstacleNode);
        }
        update();
        gamePane.getChildren().add(background);
//        HeadsUpDisplay hud = new HeadsUpDisplay();
        BorderPane root = new BorderPane();
//        root.setTop(hud);
        root.setCenter(gamePane);
        scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);//todo
        setKeys();
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
                method.invoke(game,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Rectangle addEntityToScene(double xPos, double yPos, double width, double height) {
        Rectangle rect = new Rectangle(xPos,yPos,width,height);
        rect.setFill(Color.BLUE); //TODO
        return rect;
    }

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
}
