package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Wall extends Entity implements IObstacle {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}
