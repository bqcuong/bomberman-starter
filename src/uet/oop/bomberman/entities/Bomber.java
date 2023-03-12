package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;
import java.util.ArrayList;

public class Bomber extends Entity {


  public Bomber(int x, int y, Image img) {
    super(x, y, img);
  }


  private String direction = "right";

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

  public void moveBomber(int w, int h) {
    if ((x + getDx() >= Sprite.SCALED_SIZE)
        && (x + getDx() <= Sprite.SCALED_SIZE * (w - 2))
        && (y + getDy() >= Sprite.SCALED_SIZE)
        && (y + getDy() <= Sprite.SCALED_SIZE * (h - 2))) {
      this.x += getDx();
      this.y += getDy();
    }
  }

  public void checkDirection() {
    if (getDirection().equalsIgnoreCase("right")) {
      this.img = Sprite.player_right.getFxImage();
    } else if (getDirection().equalsIgnoreCase("left")) {
      this.img = Sprite.player_left.getFxImage();
    } else if (getDirection().equalsIgnoreCase("up")) {
      this.img = Sprite.player_up.getFxImage();
    } else if (getDirection().equalsIgnoreCase("down")) {
      this.img = Sprite.player_down.getFxImage();
    }
  }

  @Override
  public void update() {
    checkDirection();
  }
}
