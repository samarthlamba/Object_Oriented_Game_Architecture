package ooga.engine.games;

import java.util.*;

public class GamePropertyFileReader {
    private ResourceBundle gameConfigBundle;
    private static final String DEFAULT_BUNDLE = "Default";

    public GamePropertyFileReader(String game, String objectName){
        try {
            this.gameConfigBundle = ResourceBundle.getBundle(game + objectName);
        }
        catch(MissingResourceException m){
            this.gameConfigBundle = ResourceBundle.getBundle(String.format(DEFAULT_BUNDLE,objectName));
        }
    }
    public Collection<String> getMethods(String key){
        Collection<String> methods = getContent(key, 0);
        return methods;
    }

    private Collection<String> getContent(String key, int option) {

        Collection<String> methods= new ArrayList<>();
        String options = gameConfigBundle.getString(key);
        String [] split = options.split(":");
        for (String val:split){
            methods.add(val.split(",",2)[option]);
        }
        return methods;
    }

    public Collection<String> getParameters(String key){
        Collection<String> methods = getContent(key, 1);
        return methods;
    }
}
