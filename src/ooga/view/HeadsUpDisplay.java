package ooga.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.ResourceBundle;


public class HeadsUpDisplay extends HBox {
    private static final double STATUS_SPACING = 30.0;
    private static final double LABEL_SPACING = 10.0;
    private static final ResourceBundle hudResources = ResourceBundle.getBundle("hud.properties");

    public HeadsUpDisplay() {
        super(300);
        setUpHud();
    }

    private void setUpHud() {
        Node livesLabel = new Text("lives");
        Node lives = new Text(Integer.toString(getLives()));//TODO
        Node pointsLabel = new Text("points");
        Node points = new Text(Integer.toString(getPoints()));
        Button settingsButton = new Button("settings");
        Button pauseButton = new Button("pause");
        settingsButton.setFocusTraversable(false);
        pauseButton.setFocusTraversable(false);

        HBox lifeStatus = new HBox(livesLabel,lives);
        lifeStatus.setSpacing(LABEL_SPACING);
        HBox pointsStatus = new HBox(pointsLabel,points);
        pointsStatus.setSpacing(LABEL_SPACING);
        HBox status = new HBox(lifeStatus,pointsStatus);
        status.setSpacing(STATUS_SPACING);
        HBox buttons = new HBox(pauseButton,settingsButton);

        this.getChildren().addAll(status,buttons);
    }

    private int getPoints() {
        return 0; //TODO
    }

    private int getLives() {
        return 3; //tODO
    }
}
