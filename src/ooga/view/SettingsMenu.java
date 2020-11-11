package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class SettingsMenu extends Popup {
    public SettingsMenu() {
        BorderPane root = new BorderPane();
        Node text = new Text("Settings");
        Button close = new Button();
        close.setAlignment(Pos.CENTER_RIGHT);

        root.setCenter(text);
        root.setTop(close);

        this.getContent().add(root);
    }
}
