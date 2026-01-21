package uet.oop.bomberman.entities.Map;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

/**
 * Tường
 */
public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wall)) return false;
        Wall entity = (Wall) o;
        return getX() == entity.getX() && getY() == entity.getY();
    }
}
