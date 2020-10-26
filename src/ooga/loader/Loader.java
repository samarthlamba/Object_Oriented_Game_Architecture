package ooga.loader;

import ooga.engine.Games.Game;

public interface Loader {
    Game getCorrectGame(String fileLocation);
}
