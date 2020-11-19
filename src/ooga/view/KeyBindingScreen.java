package ooga.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.GameController;
import ooga.loader.KeyBinder;
import ooga.view.screens.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class KeyBindingScreen extends Screen {

    private Scene scene;
    private Scene oldScene;
    private GameController gameController;

    public KeyBindingScreen(Scene settingsScene, GameController controller) {
        gameController = controller;
        oldScene = settingsScene;
        setUpScene();
    }

    private void setUpScene() {//TODO long method
        BorderPane root = new BorderPane();
        KeyBinder keyBinder = new KeyBinder();
        Map<KeyCode,KeyCode> inputs = new HashMap<>();

        Button backButton = new Button("back");
        backButton.setOnMouseClicked(e->back());


        VBox options = new VBox();
//        Label currLabel = getCurrentBindings();
//        options.getChildren().add(currLabel);
//        options.setFillWidth(true);


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
//            inputs.put(key,input);
            entry.getChildren().add(input);
            entry.getChildren().add(new Label(key.toString()));
            entry.setAlignment(Pos.CENTER);
            options.getChildren().add(entry);
        }
        Button submitButton = new Button("save");
        submitButton.setOnMouseClicked(e -> submit(keyBinder,inputs));
        options.getChildren().add(submitButton);
        Button resetButton = new Button("reset to default");
        resetButton.setOnMouseClicked(e->reset(keyBinder));
        options.getChildren().add(resetButton);
        options.setAlignment(Pos.CENTER);
        root.setTop(backButton);
        root.setCenter(options);

        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private void reset(KeyBinder keyBinder) {
        keyBinder.setToDefault();
        try {
            keyBinder.saveMap();
        } catch (IOException e) {
            throw (new RuntimeException("BREEKEKEKEKEKEKEK"));
        }
        setUpScene();
    }

//    private Label getCurrentBindings() {
//        Label currentBindings = new Label();
//        String ;
//        String ;
//        String ;
//        String ;
//
//        String text = String.format("LEFT = %s, RIGHT = %s, JUMP = %s, ACTION = %s", left, right, jump, action);
//        return currentBindings;
//    }

    private void logThisKey(Map<KeyCode, KeyCode> inputs, TextField input, KeyCode key, KeyEvent e) {
        inputs.put(key,e.getCode());
        input.clear();
        input.setText(e.getText());
    }

    private void back() {
        gameController.setScene(oldScene);
    }

    private void submit(KeyBinder keyBinder,Map<KeyCode,KeyCode> inputs) {
        for (KeyCode key: inputs.keySet()) {
//            String x = inputs.get(key).getText();
            KeyCode code = inputs.get(key);
            String method = keyBinder.getKeyMethodMap().get(key);
            keyBinder.setBinding(code,method);
        }
        try {
           keyBinder.saveMap();
        } catch (IOException e){
            System.out.println("AIDFLASFDIALSDFIALSDIASD"); //TODO display alert
            keyBinder.setToDefault();
            return;
        }
        setUpScene();
    }


    @Override
    public Scene getView() {
        return scene;
    }
}
