package ooga.view;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ooga.engine.entities.Entity;
import ooga.engine.entities.Moveables;
import ooga.engine.games.GamePlay;
import ooga.engine.obstacles.Collideable;
import ooga.engine.obstacles.Obstacle;

import java.lang.reflect.Method;
import java.security.Key;
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
//    private Object up;
//    private Object right;
//    private Object left;
    private List<Object> keys;
    private GamePlay game;
    private Group background;
    private Moveables mainPlayer;
//
    public void setGameScreen(GamePlay givenGame) {
        Pane gamePane = new Pane(); //Todo justify
        background = new Group();
        game = givenGame;
        keys = new ArrayList<>();
        for (Moveables entity : game.getEntities()) {
            if (entity.getId().equals("player")) {

//        Group gamePane = new Group(); //Todo justify
                mainPlayer = entity;
                mainX = entity.getNode().getLayoutBounds().getMinX();
                mainY = entity.getNode().getLayoutBounds().getMinY();
                double width = entity.getNode().getLayoutBounds().getWidth();
                double height = entity.getNode().getLayoutBounds().getHeight();
                double x = SCREEN_WIDTH / 2 - width / 2;
                double y = SCREEN_HEIGHT / 2 - height / 2;
//                Rectangle entityFig = addEntityToScene(x, y, width, height);
//                System.out.println(x + "  " + y);
                mainWidth = width;
                mainHeight = height;
//                gamePane.getChildren().add(entityFig);

//                gamePane.getChildren().add(entity.getNode());//TODO
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

        for (Collideable obstacle : game.getBackground()) {
            Shape obstacleNode = (Shape) obstacle.getNodeObject();
            obstacleNode.setFill(Color.BROWN);
            background.getChildren().add(obstacleNode);
        }
        double sceneShiftX = -(mainX - (SCREEN_WIDTH/2 - mainWidth/2));
        double sceneShiftY = -(mainY - (SCREEN_HEIGHT/2 - mainHeight/2));
        background.setTranslateX(sceneShiftX);
        background.setTranslateY(sceneShiftY);
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
                double x = mainX;
                double mx = mainPlayer.getNode().getLayoutBounds().getMinX();
                double mmx = mainPlayer.getCenterX();
                Method method = game.getClass().getMethod(methodName);
//
                method.invoke(game,null);
                game.updateMoveables();//TODO remove
                double xx = mainX;
                double mxx = mainPlayer.getCenterX();

                int w = 0;
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
        mainX = mainPlayer.getNode().getLayoutBounds().getMinX();
        mainY = mainPlayer.getNode().getLayoutBounds().getMinY();
        double x = mainPlayer.getCenterX();
        double y = mainPlayer.getMaxY();
        double sceneShiftX = -(mainX - (SCREEN_WIDTH/2 - mainWidth/2));
        double sceneShiftY = -(mainY - (SCREEN_HEIGHT/2 - mainHeight/2));
        background.setTranslateX(sceneShiftX);
        background.setTranslateY(sceneShiftY);
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
