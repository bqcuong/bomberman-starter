package uet.oop.bomberman.entities.Powerup;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

/**
 * Powerup tăng tốc độ
 */
public class SpeedItem extends Entity {

  public SpeedItem(int x, int y, Image img) {
    super(x, y, img);
  }

  @Override
  public void update() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SpeedItem)) return false;
    SpeedItem entity = (SpeedItem) o;
    return getX() == entity.getX() && getY() == entity.getY();
  }
}
