package ooga.view;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Performs the animation of given image based on information regarding the inputted image. Uses viewport to determine what to show to the user
 */
public class Animation extends Transition { //fsm backend if separation    //look at unity // dont look at unity for the love of god
  private final ImageView image;
  private double width;
  private double height;
  private final double xWhiteSpaceConstant;
  private final double yWhiteSpaceConstant;
  private final int positionOfFirstAnimation;
  private final int framesPerRow;
  private final int length;
  private int lastIndex = -1;
  private final int actualWidth;
  private final int actualHeight;
  public Animation(Image image,Double spriteWidth, Double spriteHeight, double xWhiteSpaceConstant, double yWhiteSpaceConstant, int lengthOfAnimation, int positionOfFirstAnimation, int framesPerRow, int actualSprintWidth, int actualSprintHeight, int duration){
    Duration duration1 = new Duration(duration);
    this.image = new ImageView(image);
    this.width = spriteWidth;
    this.height = spriteHeight;
    this.actualWidth = actualSprintWidth;
    this.actualHeight = actualSprintHeight;
    this.xWhiteSpaceConstant = xWhiteSpaceConstant;
    this.yWhiteSpaceConstant = yWhiteSpaceConstant;
    this.framesPerRow = framesPerRow;
    this.positionOfFirstAnimation = positionOfFirstAnimation;
    this.length = lengthOfAnimation;
    this.setCycleDuration(duration1);
    setInterpolator(Interpolator.LINEAR);




  }

  /**
   * Sets the X position of the image
   * @param value: position to set X to
   */
  public void setX(double value){
    image.setX(value);
  }

  /**
   * Sets the y position of the image
   * @param value position to set Y to
   */
  public void setY(double value){
    image.setY(value);
  }

  /**
   * Swap direction of image to showcase movement to the left or right
   */
  public void swapDirection(){
    image.setScaleX(image.getScaleX()*-1);
  }

  /**
   * Scale the image as the entity size changes to account for shrinking and increasing entity size
   * @param xValue: value for how big X should be
   * @param yValue value for how big y should be
   */
  public void scale(double xValue, double yValue) {

    image.setFitWidth(xValue);
    image.setFitHeight(yValue);
    this.width = xValue;
    this.height = yValue;
  }

  /**
   * Loops through the animation as needed based on the above inputted parameters
   * @param frac what position/part in animation to display
   */
  @Override
  protected void interpolate(double frac) {
    int index = Math.min((int) Math.floor(frac * length), length - 1)+ positionOfFirstAnimation -1; //the first part was a part of interpolate and the +positionOfFirstAnimation offsets things
    if (index != lastIndex) {
      double x = (index % framesPerRow) * actualWidth  + xWhiteSpaceConstant*((index % framesPerRow)); //current position in row * width of image + amount of white space to leave * the current position*2
      //System.out.println(x);
      final double y = (index / framesPerRow) * actualHeight + yWhiteSpaceConstant*(index / framesPerRow); //we expect this to be fine and this works as intended. It moves to the next row by this type of division

      image.setViewport(new Rectangle2D(x, y, actualWidth, actualHeight));
      lastIndex = index;
    }
    image.setFitWidth(width);
    image.setFitHeight(height);
    //   System.out.println(image.getViewport());
  }

  /**
   * gets the image after the effects have been put
   * @return imageView after the viewport, scaling, and reflection has been put
   */
  public ImageView getImage(){
    return image;

  }
}
