package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class SpeedItem extends Entity {

    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeedItem)) return false;
        SpeedItem entity = (SpeedItem) o;
        return getX() == entity.getX() && getY() == entity.getY();
    }
}
