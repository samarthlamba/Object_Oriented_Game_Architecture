package ooga.engine.games.beans;

public class MetroidBean extends GameBean{
    private int bulletWidth;
    private int bulletHeight;
    private double bulletVelocityX;

    public int getBulletWidth() {
        return bulletWidth;
    }

    public int getBulletHeight() {
        return bulletHeight;
    }

    public double getBulletVelocityX() {
        return bulletVelocityX;
    }

    public void setBulletWidth(String bulletWidth) {
        this.bulletWidth = Integer.valueOf(bulletWidth);
    }

    public void setBulletHeight(String bulletHeight) {
        this.bulletHeight = Integer.valueOf(bulletHeight);
    }

    public void setBulletVelocityX(String bulletVelocityX) {
        this.bulletVelocityX = Double.valueOf(bulletVelocityX);
    }

}
