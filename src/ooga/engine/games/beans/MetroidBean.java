package ooga.engine.games.beans;

/**
 * Metroid beans to pass constants into game through game constructor
 */
public class MetroidBean extends GameBean{
    private int bulletWidth;
    private int bulletHeight;
    private double bulletVelocityX;

    /**
     * Returns width of bullet weapon entity
     * @return int bullet width
     */
    public int getBulletWidth() {
        return bulletWidth;
    }

    /**
     * Return height of bullet weapon entity
     * @return int bullet height
     */
    public int getBulletHeight() {
        return bulletHeight;
    }

    /**
     * Returns x velocity of bullet
     * @return double bullet velocity
     */
    public double getBulletVelocityX() {
        return bulletVelocityX;
    }

    /**
     * Sets width of bullet
     * @param bulletWidth read in from properties file
     */
    public void setBulletWidth(String bulletWidth) {
        this.bulletWidth = Integer.valueOf(bulletWidth);
    }

    /**
     * Sets height of bullets
     * @param bulletHeight read in from properties file
     */
    public void setBulletHeight(String bulletHeight) {
        this.bulletHeight = Integer.valueOf(bulletHeight);
    }

    /**
     * Sets x velocity of bullets
     * @param bulletVelocityX read in from properties file
     */
    public void setBulletVelocityX(String bulletVelocityX) {
        this.bulletVelocityX = Double.valueOf(bulletVelocityX);
    }

}
