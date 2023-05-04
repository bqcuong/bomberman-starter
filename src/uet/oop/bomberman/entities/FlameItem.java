package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class FlameItem extends Entity {

    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlameItem)) return false;
        FlameItem entity = (FlameItem) o;
        return getX() == entity.getX() && getY() == entity.getY();
    }
}
