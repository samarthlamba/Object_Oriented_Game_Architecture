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
        this.width = width/3;
        this.height = height/3;
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
//        System.out.println("width " + width);
//        System.out.println("height " + height);
//
//        System.out.println("xOffset " + xOffset);
//
//        System.out.println("yOffset " + yOffset);
//
//        System.out.println("length " + length);
//        System.out.println("dimension: " + image.getImage().getHeight() + "   " + image.getImage().getWidth());

        final int index = Math.min((int)Math.floor(frac * length), (int)length - 1);

        final double x = (index % framePerRow) * width  + xOffset;
        final double y = (index / framePerRow) * height + yOffset;
      //  System.out.println(abs(x) + "   " + y);
        image.setViewport(new Rectangle2D(abs(x), abs(y), width, height));
        //System.out.println("interpolate viewport: " + new Rectangle2D(abs(x), abs(y), width, height));
        // System.out.println("rectangle: " + new Rectangle2D(abs(x), abs(y), width, height));



    }
    public ImageView getImage(){
        return image;
    }
}
