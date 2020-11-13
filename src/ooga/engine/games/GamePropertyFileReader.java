package ooga.engine.games;

import java.util.*;

public class GamePropertyFileReader {
    private final ResourceBundle gameConfigBundle;

    public GamePropertyFileReader(String game, String objectName){
        this.gameConfigBundle = ResourceBundle.getBundle(game+objectName);
    }
    public Collection<String> getMethods(String key){
        Collection<String> methods = getContent(key, 0);
        return methods;
    }

    private Collection<String> getContent(String key, int option) {
        Collection<String> methods= new ArrayList<>();
        String options = gameConfigBundle.getString(key);
        String [] split = options.split(":", 2);
        String [] methodOptions = split[option].split(",");
        methods = Arrays.asList(methodOptions);
        return methods;
    }

    public Collection<String> getParameters(String key){
        Collection<String> methods = getContent(key, 1);
        return methods;
    }
}
