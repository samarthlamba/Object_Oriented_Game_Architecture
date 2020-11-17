package ooga.engine.games.beans;

public class VikingsBean extends GameBean{
    private int arrowWidth;
    private int arrowHeight;
    private double arrowVelocityX;

    public int getArrowWidth() {
        return arrowWidth;
    }

    public int getArrowHeight() {
        return arrowHeight;
    }

    public double getArrowVelocityX() {
        return arrowVelocityX;
    }

    public void setArrowWidth(String arrowWidth) {
        this.arrowWidth = Integer.valueOf(arrowWidth);
    }

    public void setArrowHeight(String arrowHeight) {
        this.arrowHeight = Integer.valueOf(arrowHeight);
    }

    public void setArrowVelocityX(String arrowVelocityX) {
        this.arrowVelocityX = Double.valueOf(arrowVelocityX);
    }
}
