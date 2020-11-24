package ooga.view;

import ooga.engine.entities.MovableBounds;

import java.util.Collection;

/**
 * Used for display game interactions to know which entites die from the game
 * so they can be removed from the screen
 */
public interface UpdateObjectsOnScreen {
    void spawn(Collection<MovableBounds> add);
    void remove(Collection<MovableBounds> remove);
}
