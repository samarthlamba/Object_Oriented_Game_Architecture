package ooga.view.screens;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

//<<<<<<< HEAD:src/ooga/view/Menu.java
//import java.lang.reflect.InvocationTargetException;
//=======
//>>>>>>> jnh24:src/ooga/view/screens/Menu.java
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class Menu extends VBox {

    ResourceBundle menuItems;
//<<<<<<< HEAD:src/ooga/view/Menu.java
    Consumer<String> buttonAction;

    public Menu(ResourceBundle menuButtonProperties, Consumer<String> buttonAction) {
        this.buttonAction = buttonAction;
        List<String> buttonKeySet = menuButtonProperties.keySet().stream()
            .sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for(String property : buttonKeySet) {
//=======
//    Consumer<String> menuFunction;
//
//    public Menu(ResourceBundle menuButtonProperties, Consumer<String> e) {
//        menuFunction = e;
//        for(String property : menuButtonProperties.keySet()) {
//>>>>>>> jnh24:src/ooga/view/screens/Menu.java
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
//<<<<<<< HEAD:src/ooga/view/Menu.java
        newButton.setOnAction(e -> buttonAction.accept(property));//label
//=======
//        newButton.setOnAction(e -> menuFunction.accept(property));//label
//>>>>>>> jnh24:src/ooga/view/screens/Menu.java
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
