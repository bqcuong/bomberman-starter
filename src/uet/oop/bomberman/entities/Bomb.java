package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.block;
import static uet.oop.bomberman.BombermanGame.bomber;

public class Bomb extends Entity {

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public static void set_Bomb() {
        Bomb bomb = new Bomb(bomber.getX() / 32, bomber.getY() / 32, Sprite.bomb.getFxImage());
        block.add(bomb);
    }

}
