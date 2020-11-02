package ooga.engine.entities;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class Player extends Entity{

  private static final int ID = 0;
  private int speed = 0;
  public Player(int sceneWidth, int sceneHeight, double initialX, double initialY) {
    super(sceneWidth, sceneHeight, initialX, initialY);
    this.onKeyPressedProperty().bind(this.getNode().onKeyPressedProperty());
    this.getNode().setOnKeyPressed(this::move);
  }

  public void move(KeyEvent boyo) {
    if (boyo.getCode() == KeyCode.RIGHT) {
      this.getNode().setTranslateX(this.getNode().getTranslateX()+20);
    }
    if(boyo.getCode() == KeyCode.LEFT) {
      this.getNode().setTranslateX((this.getNode().getTranslateX()-20));
    }
    if(boyo.getCode() == KeyCode.UP) {
      this.getNode().setTranslateY((this.getNode().getTranslateY()-20));
    }
  }
  public int getID(){
    return this.ID;
  }


/*  public void moveLeft(){
    setX(getX()-speed);
  }

  public void moveRight(){
    setX(getX()+ speed);
  }

  public void changeSpeed(int speed){
    this.speed = speed;
  }*/

}
