package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomber;

public class SpeedItem extends Items {
    public static int speed = 1;

    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public SpeedItem() {
    }

    public SpeedItem(boolean received) {
        super(received);
    }

    @Override
    public void update() {
        if (!this.used)
            if (bomber.getX() == this.x && bomber.getY() == this.y) {
                //Grass grass = new Grass(this.x/32, this.y/32, Sprite.grass.getFxImage());
                this.setImg(Sprite.grass.getFxImage());
                this.used = true;
                speed = 2;
            }
    }
}
