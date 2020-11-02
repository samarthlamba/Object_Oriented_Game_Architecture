package ooga.view;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ooga.engine.entities.Entity;
import ooga.engine.games.GamePlay;
import ooga.engine.obstacles.Obstacle;

public class GamePlayScreen extends Screen{

    private Scene scene;
    private double mainY;
    private double mainX;
    private double mainWidth;
    private double mainHeight;
//
    public void setGameScreen(GamePlay game) {
        Pane root = new Pane(); //Todo justify
        Group background = new Group();
        for (Entity entity : game.getEntities()) {
            if (entity.getID() == 0) {
                mainX = entity.getX();
                mainY = entity.getY();
                double width = entity.getNode().getLayoutBounds().getWidth();
                double height = entity.getNode().getLayoutBounds().getHeight();
                double x = SCREEN_WIDTH / 2 - width / 2;
                double y = SCREEN_HEIGHT / 2 - height / 2;
                Rectangle entityFig = addEntityToScene(x, y, width, height);
                System.out.println(x + "  " + y);
                mainWidth = width;
                mainHeight = height;
                root.getChildren().add(entityFig);
            }
            else {
//                Shape entityNode = (Shape) entity.getNode();
                Bounds layoutBounds = entity.getNode().getLayoutBounds();
                Rectangle rect = new Rectangle(entity.getX(),entity.getY(),layoutBounds.getWidth(),layoutBounds.getHeight());
//                entityNode.setFill(Color.GREEN);
                rect.setFill(Color.GREEN);
//                background.getChildren().add(entityNode);
                background.getChildren().add(rect);
            }
        }


        for (Obstacle obstacle : game.getBackground()) {
            Shape obstacleNode = (Shape) obstacle.getNodeObject();
            background.getChildren().add(obstacleNode);
        }
        double sceneShiftX = -(mainX - (SCREEN_WIDTH/2 - mainWidth/2));
        double sceneShiftY = -(mainY - (SCREEN_HEIGHT/2 - mainHeight/2));
        background.setTranslateX(sceneShiftX);
        background.setTranslateY(sceneShiftY);
        root.getChildren().add(background);
        scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    private Rectangle addEntityToScene(double xPos, double yPos, double width, double height) {
        Rectangle rect = new Rectangle(xPos,yPos,width,height);
        rect.setFill(Color.BLUE); //TODO
        return rect;
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
