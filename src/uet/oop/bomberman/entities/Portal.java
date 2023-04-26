package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Portal extends Entity {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Portal)) return false;
        Portal entity = (Portal) o;
        return getX() == entity.getX() && getY() == entity.getY();
    }
}
