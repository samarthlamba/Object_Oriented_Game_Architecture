package ooga.view;

import ooga.engine.entities.MovableBounds;

import java.util.Collection;

public interface UpdateObjectsOnScreen {
    void spawn(Collection<MovableBounds> add);
    void remove(Collection<MovableBounds> remove);
}
