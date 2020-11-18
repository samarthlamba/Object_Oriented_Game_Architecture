package ooga.view;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

//import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class Menu extends VBox {

    ResourceBundle menuItems;
    Consumer<String> buttonAction;

    public Menu(ResourceBundle menuButtonProperties, Consumer<String> buttonAction) {
        this.buttonAction = buttonAction;
        List<String> buttonKeySet = menuButtonProperties.keySet().stream()
            .sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for(String property : buttonKeySet) {
            Button button = makeNewButton(property, menuButtonProperties);
            this.getChildren().add(button);
        }
        menuItems = menuButtonProperties;
    }

    private Button makeNewButton(String property, ResourceBundle menuButtonProperties) {
        Button newButton = new Button();
        String label = (String) menuButtonProperties.getObject(property); //TODO (String?)
        newButton.setText(label);
        newButton.setId(property);
        newButton.setOnAction(e -> buttonAction.accept(property));//label
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
//
//    public void setButtonFunctionPrefix(String prefix) {
//        buttonFunctionPrefix = prefix;
//    }
//
//    public void setButtonFunctionSuffix(String suffix) {
//        buttonFunctionSuffix = suffix;
//    }
//
//    public void setButtonFunctionClassName(String className) {
//        buttonFunctionClassName = className;
//    }
}
