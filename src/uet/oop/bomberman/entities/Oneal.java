package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.bomber;

public class Oneal extends Animal {

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public Oneal(int is_move, int swap, String direction, int count, int count_to_run) {
        super(4, 1, "up", 0, 0);
    }

    public void update() {
        if (bomber.getX() < this.x) {
            Move.left(this);
        }
        if (bomber.getX() > this.x) {
            Move.right(this);
        }
        if (bomber.getY() > this.y) {
            Move.down(this);
        }
        if (bomber.getY() < this.y) {
            Move.up(this);
        }
    }
}
