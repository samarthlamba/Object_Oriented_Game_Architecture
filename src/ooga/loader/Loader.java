package ooga.loader;

import ooga.engine.Games.Game;

public interface Loader {
    public Game getCorrectGame(String fileLocation);
}
