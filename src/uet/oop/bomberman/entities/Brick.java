package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Brick extends Entity {

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brick)) return false;
        Brick entity = (Brick) o;
        return getX() == entity.getX() && getY() == entity.getY();
    }
}
