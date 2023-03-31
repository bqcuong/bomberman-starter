package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;
import java.util.ArrayList;
import java.util.Arrays;
import uet.oop.bomberman.gamelogic.*;

public class Bomber extends Entity {
  private int cnt = 0;

  public Bomber(int x, int y, Image img) {
    super(x, y, img);
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
      if (cnt == 44) cnt = 0;
      if (!pastDirection.equalsIgnoreCase(direction)) {
        cnt = 0;
        System.out.println(pastDirection + " " + getDirection() + " " + cnt);
        pastDirection = direction;
      }
      switch (getDirection()) {
        case "right":
          this.img = player_right.get(cnt / 15);
          break;
        case "left":
          this.img = player_left.get(cnt / 15);
          break;
        case "up":
          this.img = player_up.get(cnt / 15);
          break;
        case "down":
          this.img = player_down.get(cnt / 15);
          break;
      }

    }
  }

  @Override
  public void update() {
    checkAnimation();
    if (isMoving()) {
      moveBomber();
    }
  }
}
