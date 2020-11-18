package ooga.view.screens;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class Menu extends VBox {

    ResourceBundle menuItems;
    Consumer<String> menuFunction;

    public Menu(ResourceBundle menuButtonProperties, Consumer<String> e) {
        menuFunction = e;
        for(String property : menuButtonProperties.keySet()) {
            Button button = makeNewButton(property, menuButtonProperties);
            this.getChildren().add(button);
        }
        menuItems = menuButtonProperties;
        this.getStyleClass().add("menu");
    }

    private Button makeNewButton(String property, ResourceBundle menuButtonProperties) {
        Button newButton = new Button();
        String label = (String) menuButtonProperties.getObject(property); //TODO (String?)
        newButton.setText(label);
        newButton.setId(property);
        newButton.setOnAction(e -> menuFunction.accept(property));//label
        return newButton;
    }

    private void createMethod(String methodName, String className) {
        try {
            Class c = Class.forName(className);//.getClass();
            Method myObjMethod = c.getDeclaredMethod(methodName);
            myObjMethod.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException("method not found");
        }
    }
}
