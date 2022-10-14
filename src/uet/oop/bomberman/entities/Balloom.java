package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import java.util.Random;

public class Balloom extends Animal {

    public Balloom(int is_move, int swap, String direction, int count, int count_to_run) {
        super(4, 1, "up", 0, 0);
    }
    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0:
                Move.down(this);
                break;
            case 1:
                Move.up(this);
                break;
            case 2:
                Move.left(this);
                break;
            case 3:
                Move.right(this);
                break;
        }
    }
}
