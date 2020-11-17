package ooga.view;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import static java.lang.Math.abs;

public class Animation extends Transition { //fsm backend if seperation    //look at unity // dont look at unity for the love of god
  private final Duration duration;
  private ImageView image;
  private ImageView currentImage;
  private double width;
  private double height;
  private final double xWhiteSpaceConstant;
  private final double yWhiteSpaceConstant;
  private final int postionOfFirstAnimation;
  private final int framesPerRow;
  private final int length;
  private int lastIndex = -1;
  private int index;
  private double currentScale;
  private final int actualWidth;
  private final int actualHeight;
  public Animation(Image image,Double spriteWidth, Double spriteHeight, double xWhiteSpaceConstant, double yWhiteSpaceConstant, int lengthOfAnimation, int positionOfFirstAnimation, int framesPerRow, int actualSprintWidth, int actualSprintHeight, int duration){
    this.duration = new Duration(duration);
    this.image = new ImageView(image);
    this.width = spriteWidth;
    this.height = spriteHeight;
    this.actualWidth = actualSprintWidth;
    this.actualHeight = actualSprintHeight;
    this.xWhiteSpaceConstant = xWhiteSpaceConstant;
    this.yWhiteSpaceConstant = yWhiteSpaceConstant;
    this.framesPerRow = framesPerRow;
    this.postionOfFirstAnimation = positionOfFirstAnimation;
    this.length = lengthOfAnimation;
    this.setCycleDuration(this.duration);
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

  public void scale(double xValue, double yValue) {

    image.setFitWidth(xValue);
    image.setFitHeight(yValue);
    this.width = xValue;
    this.height = yValue;
  }

    /*
    private boolean isScaled() {
        List<Scale> scales = image.getTransforms().stream().filter(t -> t.getClass().equals(Scale.class)).map(transform -> (Scale) transform).collect(
            Collectors.toList());
        List<Scale> halfScaled = scales.stream()
    }

     */


  @Override
  protected void interpolate(double frac) {
    int index = Math.min((int) Math.floor(frac * length), length - 1)+postionOfFirstAnimation-1; //the first part was a part of interpolate and the +positionOfFirstAnimation offsets things
    if (index != lastIndex) {
      double x = (index % framesPerRow) * actualWidth  + xWhiteSpaceConstant*((index % framesPerRow)); //current position in row * width of image + amount of white space to leave * the current position*2
      //System.out.println(x);
      final double y = (index / framesPerRow) * actualHeight + yWhiteSpaceConstant*(index / framesPerRow);

      image.setViewport(new Rectangle2D(x, y, actualWidth, actualHeight));
      lastIndex = index;
    }
    image.setFitWidth(width);
    image.setFitHeight(height);
    //   System.out.println(image.getViewport());
  }
  public ImageView getImage(){
    return image;

  }
}
