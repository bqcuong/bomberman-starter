package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
  private boolean damaged = false;
  private boolean isDisappear = false;
  public final int DISAPPEAR_TIME = 15;
  private int disappear = 0;

  public void setDamaged(boolean damaged) {
    this.damaged = damaged;
  }

  public boolean isDisappear() {
    return isDisappear;
  }

  public Brick(int x, int y, Image img) {
    super(x, y, img);
  }

  @Override
  public void update() {
    if (damaged) disappear++;
    if (disappear == 15) isDisappear = true;
    if (disappear > 0) {
      this.img =
          Sprite.movingSprite(
                  Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, disappear, 15)
              .getFxImage();
    }

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Brick)) return false;
    Brick entity = (Brick) o;
    return this.getX() == entity.getX() && this.getY() == entity.getY();
  }
}
