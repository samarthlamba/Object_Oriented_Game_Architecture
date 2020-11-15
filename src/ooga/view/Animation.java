package ooga.view;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
//https://netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/
public class Animation extends Transition { //fsm backend if seperation    //look at unity
    private final Duration duration;
    private final ImageView image;
    private final double width;
    private final double height;
    private final double xOffset;
    private final double yOffset;
    private final double framePerRow;
    private final double framePerCol;
    private int index;
    public Animation(Image image,Double width, Double height, double xOffset, double yOffset, double length, double framePerRow, double framePerCol){
        duration = new Duration(length);
        this.image = new ImageView(image);
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.framePerRow = framePerRow;
        this.framePerCol = framePerCol;
        this.setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);

    }

    public void swapDirection(){
        image.setScaleX(-1*image.getScaleX());
    }

    @Override
    protected void interpolate(double frac) {
        double xPos = index%framePerRow*width + xOffset;
        double yPos = index/framePerCol*height+yOffset;
        image.setViewport(new Rectangle2D(xPos, yPos, width, height));
        index = index+1;
    }
    public ImageView getImage(){
        return image;
    }
}
