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


public class HeadsUpDisplay extends HBox {
    public HeadsUpDisplay() {
        super(300);
        setUpHud();
    }

    private void setUpHud() {
        Node lives = new Text("lives");
        Node points = new Text("points");
        Button settingsButton = new Button("settings");
        Button pauseButton = new Button("pause");

        HBox status = new HBox(lives,points);
        HBox buttons = new HBox(pauseButton,settingsButton);

        this.getChildren().addAll(status,buttons);
    }
}
