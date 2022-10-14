package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends Animal {
    public static int swap_img = 1;

    public static int delay_swap = 0;

    public Bomber(int is_move, int swap, String direction, int count, int count_to_run) {
        super(8, 1, "down", 0, 0);
    }
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    private void Bomber_dead(Animal animal) {
        if (delay_swap % 10 == 0) {
            if (swap_img == 1) {
                animal.setImg(Sprite.player_dead1.getFxImage());
                swap_img = 2;
            } else if (swap_img == 2) {
                animal.setImg(Sprite.player_dead2.getFxImage());
                swap_img = 3;
            } else if (swap_img == 3) {
                animal.setImg(Sprite.player_dead3.getFxImage());
                swap_img = 4;
            } else {
                animal.setImg(Sprite.transparent.getFxImage());
            }
        }
    }

    private void checkEnemy() {
        int ax = bomber.getX() / 32;
        int ay = bomber.getY() / 32;
        for (Animal animal : entity) {
            int bx = animal.getX() / 32;
            int by = animal.getY() / 32;
            if (ax == bx && by - 0.5 <= ay && by + 0.5 >= ay
                    || ay == by && bx - 0.5 <= ax && bx + 0.5 >= ax) {
                bomber.life = false;
                break;
            }
        }
    }
    @Override
    public void update() {
        checkEnemy();
        delay_swap++;
        if (!bomber.life) {
            Bomber_dead(bomber);
        }
    }
}
