package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Wall extends Tile implements Obstacle {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}
