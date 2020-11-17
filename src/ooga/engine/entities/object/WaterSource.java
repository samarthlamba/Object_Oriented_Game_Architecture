package ooga.engine.entities.object;

import ooga.engine.entities.Entity;

public class WaterSource extends Entity {
    public WaterSource(int objectWidth, int objectHeight, double initialX, double initialY) {
        super(objectWidth, objectHeight, initialX, initialY);
        setId("watersource");
        setSource(true);
    }

    @Override
    public boolean hasGravity(){
        return false;
    }


}
