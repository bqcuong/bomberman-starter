package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Arrays;

public class Explosion extends Entity {
  private int disappearTime = 15;
  private boolean disappear = false;
  private String direction;
  private boolean edge = false;

  private final ArrayList<Image> explosionEndLeft =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_horizontal_left_last2.getFxImage(),
              Sprite.explosion_horizontal_left_last1.getFxImage(),
              Sprite.explosion_horizontal_left_last.getFxImage()));

  private final ArrayList<Image> explosionEndRight =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_horizontal_right_last2.getFxImage(),
              Sprite.explosion_horizontal_right_last1.getFxImage(),
              Sprite.explosion_horizontal_right_last.getFxImage()));

  private final ArrayList<Image> explosionEndUp =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_vertical_top_last2.getFxImage(),
              Sprite.explosion_vertical_top_last1.getFxImage(),
              Sprite.explosion_vertical_top_last.getFxImage()));

  private final ArrayList<Image> explosionEndDown =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_vertical_down_last2.getFxImage(),
              Sprite.explosion_vertical_down_last1.getFxImage(),
              Sprite.explosion_vertical_down_last.getFxImage()));

  private final ArrayList<Image> explosionHorizontal =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_horizontal2.getFxImage(),
              Sprite.explosion_horizontal1.getFxImage(),
              Sprite.explosion_horizontal.getFxImage()));

  private final ArrayList<Image> explosionVertical =
          new ArrayList<>(
                  Arrays.asList(
                          Sprite.explosion_vertical2.getFxImage(),
                          Sprite.explosion_vertical1.getFxImage(),
                          Sprite.explosion_vertical.getFxImage()));

  public boolean isEdge() {
    return edge;
  }

  public void setEdge(boolean edge) {
    this.edge = edge;
  }

  public Explosion(int x, int y, Image img, String direction, boolean edge) {
    super(x, y, img);
    this.direction = direction;
    this.edge = edge;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public boolean isDisappear() {
    return disappear;
  }

  private void playAnimationEdge() {
    switch (direction) {
      case "right":
        this.img = explosionEndRight.get(disappearTime / 5);
        break;
      case "left":
        this.img = explosionEndLeft.get(disappearTime / 5);
        break;
      case "up":
        this.img = explosionEndUp.get(disappearTime / 5);
        break;
      case "down":
        this.img = explosionEndDown.get(disappearTime / 5);
        break;
    }
  }

  @Override
  public void playAnimation() {
    switch (direction) {
      case "right":
        this.img = explosionHorizontal.get(disappearTime / 5);
        break;
      case "left":
        this.img = explosionHorizontal.get(disappearTime / 5);
        break;
      case "up":
        this.img = explosionVertical.get(disappearTime / 5);
        break;
      case "down":
        this.img = explosionVertical.get(disappearTime / 5);
        break;
    }
  }

  @Override
  public void update() {
    disappearTime--;
    if (disappearTime == 0) {
      disappear = true;
    }
    if (edge == true) {
      playAnimationEdge();
    } else {
      playAnimation();
    }
  }
}
