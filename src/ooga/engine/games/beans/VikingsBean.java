package ooga.engine.games.beans;

/**
 * Vikings beans to pass constants into game through game constructor
 */
public class VikingsBean extends GameBean{
    private int arrowWidth;
    private int arrowHeight;
    private double arrowVelocityX;

    /**
     * Returns width of arrow weapon entity
     * @return int arrow width
     */
    public int getArrowWidth() {
        return arrowWidth;
    }

    /**
     * Return height of arrow weapon entity
     * @return int bullet arrow
     */
    public int getArrowHeight() {
        return arrowHeight;
    }

    /**
     * Returns x velocity of arrow
     * @return double arrow velocity
     */
    public double getArrowVelocityX() {
        return arrowVelocityX;
    }

    /**
     * Sets width of arrow
     * @param arrowWidth read in from properties file
     */
    public void setArrowWidth(String arrowWidth) {
        this.arrowWidth = Integer.valueOf(arrowWidth);
    }

    /**
     * Sets height of arrow
     * @param arrowHeight read in from properties file
     */
    public void setArrowHeight(String arrowHeight) {
        this.arrowHeight = Integer.valueOf(arrowHeight);
    }

    /**
     * Sets x velocity of arrow
     * @param arrowVelocityX read in from properties file
     */
    public void setArrowVelocityX(String arrowVelocityX) {
        this.arrowVelocityX = Double.valueOf(arrowVelocityX);
    }
}
