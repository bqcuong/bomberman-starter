package uet.oop.bomberman.entities.Player;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Tia lửa do Bomb tạo ra
 */
public class Explosion extends Entity {
  public static final int DISAPPEARTIME = 15;
  private int disappearTime = 0;
  private boolean disappear = false;
  private final String direction;
  private final boolean edge;



  public Explosion(int x, int y, Image img, String direction, boolean edge) {
    super(x, y, img);
    this.direction = direction;
    this.edge = edge;
  }



  public boolean isDisappear() {
    return disappear;
  }

  //Xử lí hoạt ảnh của tia lửa cuối
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
                .getFxImage();
        break;
    }
  }

  //Xử lí hoạt ảnh tia lửa ở giữa
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
    if (edge) {
      playAnimationEdge();
    } else {
      playAnimation();
    }
  }
}
