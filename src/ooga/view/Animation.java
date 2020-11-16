package ooga.view;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static java.lang.Math.abs;

//https://netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/
public class Animation extends Transition { //fsm backend if seperation    //look at unity
    private final Duration duration;
    private final ImageView image;
    private ImageView currentImage;
    private final double width;
    private final double height;
    private final double xOffset;
    private final double yOffset;
    private final double framePerRow;
    private final double length;
    private final double framePerCol;
    private int lastIndex;
    private int index;
    public Animation(Image image,Double width, Double height, double xOffset, double yOffset, double length, double framePerRow, double framePerCol){
        duration = new Duration(1000);
        this.image = new ImageView(image);
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.framePerRow = framePerRow;
        this.length = length;
        this.framePerCol = framePerCol;
        this.setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);


    }

    public void setX(double value){
        image.setX(value);
    }

    public void setY(double value){
        image.setY(value);
    }
    public void swapDirection(){
        image.setScaleX(image.getScaleX()*-1);
    }

    @Override
    protected void interpolate(double frac) {
  //System.out.println("width " + width);
        final int index = Math.min((int) Math.floor(frac * (int)2), (int)2 - 1);
        if (index != lastIndex) {
            final int x = (index % (int)framePerCol) * (int)width  + (int)xOffset;
            final int y = (index / (int)framePerCol) * (int)height + (int)yOffset;
            image.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;

        }



    }
    public ImageView getImage(){

        System.out.println("getimage: " + image.getViewport());

        return image;

    }
}
