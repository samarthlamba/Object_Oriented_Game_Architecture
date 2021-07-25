package ooga.engine.entities;

/**
 * This interface represents any non-static "moveable" object, i.e. an entity. Entities should
 * have an x and y position, hit points (a way to remove them from the Game), and a facing. This interface
 * is implemented by the Entity abstract class
 */
public interface Moveable {

  /**
   * Used to get the absolute x position of the moveable within the level
   * @return a double corresponding to the moveable's x position
   */
  double getX();

  /**
   * Used to get the absolute y position of the moveable within the level
   * @return a double corresponding to the moveable's y position
   */
  double getY();

  /**
   * Used to change the moveable's x position to a given location
   * @param inputX the absolute x position within the level of the new x coordinate
   */
  void setX(double inputX);

  /**
   * Used to change the moveable's y position to a given location
   * @param inputX the absolute y position within the level of the new y coordinate
   */
  void setY(double inputY);

  /**
   * Used to set the hit points of the moveable, which control whether or not it is removed
   * from the game
   * @param hitpoints the number of hitpoints, as an int, to set the moveable's hitpoints to
   */
  void setHitpoints(int hitpoints);


  /**
   * Used to get the number of hit poitns the moveable has, which represents how close it is to being
   * removed from the gmae
   * @return an int corresponding to the moveable's hit points
   */
  int getHitpoints();

  /**
   * Used to determine which direction the moveable is facing. True means it is facing right, i.e. moving
   * in the positive x direction, and false means it is facing left, i.e. moving in the negative x direction
   * @return true if right, false if left
   */
  boolean getFacing();
  //true = right


}