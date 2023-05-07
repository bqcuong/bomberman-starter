package uet.oop.bomberman.entities.Map;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Gạch
 */
public class Brick extends Entity {
  private boolean damaged = false;
  private boolean isDisappear = false;
  public final int DISAPPEAR_TIME = 15;
  private int disappear = 0;

  public void setDamaged(boolean damaged) {
    this.damaged = damaged;
  }

  public boolean isDamaged() {
    return damaged;
  }

  public boolean isDisappear() {
    return isDisappear;
  }

  public Brick(int x, int y, Image img) {
    super(x, y, img);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Brick)) return false;
    Brick entity = (Brick) o;
    return this.getX() == entity.getX() && this.getY() == entity.getY();
  }

  @Override
  public void update() {
    if (isDamaged()) disappear++;
    if (disappear >= DISAPPEAR_TIME) isDisappear = true;
    if (disappear > 0) {
      this.img =
          Sprite.movingSprite(
                  Sprite.brick_exploded,
                  Sprite.brick_exploded1,
                  Sprite.brick_exploded2,
                  disappear,
                  DISAPPEAR_TIME)
              .getFxImage();
    }
  }


}
