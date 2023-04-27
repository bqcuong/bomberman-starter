package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Arrays;

public class Explosion extends Entity {
  public static final int DISAPPEARTIME = 15;
  private int disappearTime = 0;
  private boolean disappear = false;
  private String direction;
  private boolean edge = false;

  private final ArrayList<Sprite> explosionEndLeft =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_horizontal_left_last2,
              Sprite.explosion_horizontal_left_last1,
              Sprite.explosion_horizontal_left_last));

  private final ArrayList<Sprite> explosionEndRight =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_horizontal_right_last2,
              Sprite.explosion_horizontal_right_last1,
              Sprite.explosion_horizontal_right_last));

  private final ArrayList<Sprite> explosionEndUp =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_vertical_top_last2,
              Sprite.explosion_vertical_top_last1,
              Sprite.explosion_vertical_top_last));

  private final ArrayList<Sprite> explosionEndDown =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_vertical_down_last2,
              Sprite.explosion_vertical_down_last1,
              Sprite.explosion_vertical_down_last));

  private final ArrayList<Sprite> explosionHorizontal =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_horizontal2,
              Sprite.explosion_horizontal1,
              Sprite.explosion_horizontal));

  private final ArrayList<Sprite> explosionVertical =
      new ArrayList<>(
          Arrays.asList(
              Sprite.explosion_vertical2, Sprite.explosion_vertical1, Sprite.explosion_vertical));

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
        this.img =
            Sprite.movingSprite(
                    Sprite.explosion_horizontal_right_last,
                    Sprite.explosion_horizontal_right_last1,
                    Sprite.explosion_horizontal_right_last2,
                    disappearTime,
                    DISAPPEARTIME)
                .getFxImage();
        break;
      case "left":
        this.img = Sprite.movingSprite(
                        Sprite.explosion_horizontal_left_last,
                        Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2,
                        disappearTime,
                        DISAPPEARTIME)
                .getFxImage();
        break;
      case "up":
        this.img = Sprite.movingSprite(
                        Sprite.explosion_vertical_top_last,
                        Sprite.explosion_vertical_top_last1,
                        Sprite.explosion_vertical_top_last2,
                        disappearTime,
                        DISAPPEARTIME)
                .getFxImage();
        break;
      case "down":
        this.img = Sprite.movingSprite(
                        Sprite.explosion_vertical_down_last,
                        Sprite.explosion_vertical_down_last1,
                        Sprite.explosion_vertical_down_last2,
                        disappearTime,
                        DISAPPEARTIME)
                .getFxImage();;
        break;
    }
  }

  @Override
  public void playAnimation() {
    switch (direction) {
      case "right":
      case "left":
        this.img = Sprite.movingSprite(
                        Sprite.explosion_horizontal,
                        Sprite.explosion_horizontal1,
                        Sprite.explosion_horizontal2,
                        disappearTime,
                        DISAPPEARTIME)
                .getFxImage();
        break;
      case "up":
      case "down":
        this.img = Sprite.movingSprite(
                        Sprite.explosion_vertical,
                        Sprite.explosion_vertical1,
                        Sprite.explosion_vertical2,
                        disappearTime,
                        DISAPPEARTIME)
                .getFxImage();
        break;
    }
  }

  @Override
  public void update() {
    disappearTime++;
    if (disappearTime == DISAPPEARTIME) {
      disappear = true;
    }
    if (edge == true) {
      playAnimationEdge();
    } else {
      playAnimation();
    }
  }
}
