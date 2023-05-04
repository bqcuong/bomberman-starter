package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class BombItem extends Entity {

    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BombItem)) return false;
        BombItem entity = (BombItem) o;
        return getX() == entity.getX() && getY() == entity.getY();
    }
}
