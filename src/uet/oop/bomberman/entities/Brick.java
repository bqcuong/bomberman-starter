package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
  private boolean destroyed = false;
  public final int DISAPPEAR_TIME = 15;
  private int disappear = 0;

  public Brick(int x, int y, Image img) {
    super(x, y, img);
  }

  @Override
  public void update() {
    if (destroyed) disappear++;
    if (disappear > 0) {
      this.img =
          Sprite.movingSprite(
                  Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, disappear, 15)
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
