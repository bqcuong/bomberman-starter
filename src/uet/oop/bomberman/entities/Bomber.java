package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.gamelogic.*;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
  private int cnt = 0;

  private int maxBomb = 2;

  private int bombLength = 3;




  public int getMaxBomb() {
    return maxBomb;
  }

  public void setMaxBomb(int maxBomb) {
    this.maxBomb = maxBomb;
  }

  public int getBombLength() {
    return bombLength;
  }

  public void setBombLength(int bombLength) {
    this.bombLength = bombLength;
  }

  public Bomber(int x, int y, Image img) {
    super(x, y, img);
    this.getBoundingBox().setWidth(24);
  }

  ArrayList<Image> player_left =
      new ArrayList<>(
          Arrays.asList(
              Sprite.player_left.getFxImage(),
              Sprite.player_left_1.getFxImage(),
              Sprite.player_left_2.getFxImage()));

  ArrayList<Image> player_right =
      new ArrayList<>(
          Arrays.asList(
              Sprite.player_right.getFxImage(),
              Sprite.player_right_1.getFxImage(),
              Sprite.player_right_2.getFxImage()));

  ArrayList<Image> player_up =
      new ArrayList<>(
          Arrays.asList(
              Sprite.player_up.getFxImage(),
              Sprite.player_up_1.getFxImage(),
              Sprite.player_up_2.getFxImage()));

  ArrayList<Image> player_down =
      new ArrayList<>(
          Arrays.asList(
              Sprite.player_down.getFxImage(),
              Sprite.player_down_1.getFxImage(),
              Sprite.player_down_2.getFxImage()));

  private String direction = "right";
  private String pastDirection = "";

  public void setDirection(String input) {
    if (input.equalsIgnoreCase("d")) {
      this.direction = "right";
    } else if (input.equalsIgnoreCase("a")) {
      this.direction = "left";
    } else if (input.equalsIgnoreCase("w")) {
      this.direction = "up";
    } else if (input.equalsIgnoreCase("s")) {
      this.direction = "down";
    }
  }

  public String getDirection() {
    return this.direction;
  }

  public void moveBomber() {
    this.x += getDx();
    this.y += getDy();
  }

  public void checkAnimation() {
    if (!isMoving()) {
      if (getDirection().equalsIgnoreCase("right")) {
        this.img = Sprite.player_right.getFxImage();
      } else if (getDirection().equalsIgnoreCase("left")) {
        this.img = Sprite.player_left.getFxImage();
      } else if (getDirection().equalsIgnoreCase("up")) {
        this.img = Sprite.player_up.getFxImage();
      } else if (getDirection().equalsIgnoreCase("down")) {
        this.img = Sprite.player_down.getFxImage();
      }
    } else {
      cnt++;
      if (cnt == 29) cnt = 0;
      if (!pastDirection.equalsIgnoreCase(direction)) {
        cnt = 0;
        pastDirection = direction;
      }
      if (getDirection().equalsIgnoreCase("right")) {
        this.img = player_right.get(cnt / 10);
      } else if (getDirection().equalsIgnoreCase("left")) {
        this.img = player_left.get(cnt / 10);
      } else if (getDirection().equalsIgnoreCase("up")) {
        this.img = player_up.get(cnt / 10);
      } else if (getDirection().equalsIgnoreCase("down")) {
        this.img = player_down.get(cnt / 10);
      }
    }
  }

  @Override
  public void update() {
    checkAnimation();
    if (isMoving()) {
      moveBomber();
    }
    this.getBoundingBox().setX(x);

    this.getBoundingBox().setY(y);

  }
}
