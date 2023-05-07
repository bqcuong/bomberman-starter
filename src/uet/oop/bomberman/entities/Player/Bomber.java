package uet.oop.bomberman.entities.Player;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.gamelogic.*;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
  public static final int ANIMATIONTIME = 9;
  private int cnt = 0;
  private int live = 3;

  private int maxBomb = 1;

  private int bombLength = 1;

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
      if (!pastDirection.equalsIgnoreCase(direction)) {
        cnt = 0;
        pastDirection = direction;
      }
      if (getDirection().equalsIgnoreCase("right")) {
        this.img =
            Sprite.movingSprite(
                    Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, cnt, ANIMATIONTIME)
                .getFxImage();
      } else if (getDirection().equalsIgnoreCase("left")) {
        this.img =
            Sprite.movingSprite(
                    Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, cnt, ANIMATIONTIME)
                .getFxImage();
      } else if (getDirection().equalsIgnoreCase("up")) {
        this.img =
            Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, cnt, ANIMATIONTIME)
                .getFxImage();
      } else if (getDirection().equalsIgnoreCase("down")) {
        this.img =
            Sprite.movingSprite(
                    Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, cnt, ANIMATIONTIME)
                .getFxImage();
      }
    }
  }

  @Override
  public void update() {
    checkAnimation();
    if (x % 2 != 0) {
      if (Math.random() > 0.5) {
        x++;
      } else {
        x--;
      }
    }
    if (y % 2 != 0) {
      if (Math.random() > 0.5) {
        y++;
      } else {
        y--;
      }
    }
    
    this.getBoundingBox().setX(x);

    this.getBoundingBox().setY(y);
  }
}
