package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.GameController;
import ooga.loader.KeyBinder;
import ooga.view.screens.Screen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class KeyBindingScreen extends Screen {

    private Scene scene;
    private Scene oldScene;
    private GameController gameController;
    private KeyBinder keyBinder;

    public KeyBindingScreen(Scene settingsScene, GameController controller) {
        gameController = controller;
        oldScene = settingsScene;
        keyBinder = new KeyBinder();
        setUpScene();
    }

    private void setUpScene() {//TODO long method
        BorderPane root = new BorderPane();
        VBox options = new VBox();
        Map<KeyCode,KeyCode> inputs = new HashMap<>();
        placeButtons(inputs,options);
        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e->back());
        Button submitButton = new Button("save");
        submitButton.setOnMouseClicked(e -> submit(inputs));
        options.getChildren().add(submitButton);
        Button resetButton = new Button("reset to default");
        resetButton.setOnMouseClicked(e->reset());
        options.getChildren().add(resetButton);
        options.setAlignment(Pos.CENTER);
        root.setTop(backButton);
        root.setCenter(options);

        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private void placeButtons(Map<KeyCode, KeyCode> inputs, VBox options) {
        for(KeyCode key : keyBinder.getKeyMethodMap().keySet()) {
            HBox entry = new HBox();
            Label label = new Label(keyBinder.getKeyMethodMap().get(key));
            label.setAlignment(Pos.CENTER_LEFT);
            entry.getChildren().add(label); //TODO make sure label is good
            TextField input  = new TextField();
            input.setOnKeyPressed(e->{logThisKey(inputs,input,key,e);});
            input.lengthProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue.intValue() > 1) {
                    input.setText(input.getText(1,2));
                }
            });
            input.setPrefColumnCount(1);
            input.setAlignment(Pos.CENTER_RIGHT);
            entry.getChildren().add(input);
            entry.getChildren().add(new Label(key.toString()));
            entry.setAlignment(Pos.CENTER);
            options.getChildren().add(entry);
        }
    }

    private void reset() {
        keyBinder.setToDefault();
        try {
            keyBinder.saveMap();
        } catch (IOException e) {
            throw (new RuntimeException("BREEKEKEKEKEKEKEK"));
        }
        setUpScene();
        gameController.setScene(scene);
    }

    private void logThisKey(Map<KeyCode, KeyCode> inputs, TextField input, KeyCode key, KeyEvent e) {
        inputs.put(key,e.getCode());
        input.clear();
        input.setText(e.getText());
    }

    private void back() {
        gameController.setScene(oldScene);
    }

    private void submit(Map<KeyCode,KeyCode> inputs) {
        try {
            for (KeyCode key: inputs.keySet()) {
                KeyCode code = inputs.get(key);
                String method = keyBinder.getKeyMethodMap().get(key);
                keyBinder.setBinding(code, method);
            }
        } catch (Exception e) {
            makeAlert("invalid keys","certain keys are used multiple times");
        }
        try {
           keyBinder.saveMap();
        } catch (IOException e){
            throw new RuntimeException("corrupted key propperties file");
        }
        setUpScene();
        gameController.setScene(scene);
    }

    public void makeAlert (String header, String message) {
        Alert a = new Alert(Alert.AlertType.NONE);
        ButtonType close = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        a.getButtonTypes().addAll(close);
        a.setHeaderText(header);
        a.setContentText(message);
        a.initOwner(gameController.getStage());
        a.show();
    }

    @Override
    public Scene getView() {
        return scene;
    }
}
