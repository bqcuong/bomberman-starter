package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Entity {
    private int onealSpeed = 2;

    public void setOnealSpeed(int onealSpeed) {
        this.onealSpeed = onealSpeed;
    }

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        Random rd = new Random();
        int ok = rd.nextInt(4);
        if (ok == 2 && location_x % Sprite.SCALED_SIZE < 3 && location_y % Sprite.SCALED_SIZE < 3) {
            int numGo = rd.nextInt(4);
            switch (numGo % 4) {
                case 0:
                    go = 'A';
                    break;
                case 1:
                    go = 'D';
                    break;
                case 2:
                    go = 'W';
                    break;
                case 3:
                    go = 'S';
                    break;
            }
        }
        switch (go) {
            case 'A':
                location_x -= onealSpeed;
                if (count_img % 30 == 0) {
                    this.img = Sprite.oneal_left1.getFxImage();
                } else if (count_img % 30 == 10) {
                    this.img = Sprite.oneal_left2.getFxImage();
                } else if (count_img % 30 == 20) {
                    this.img = Sprite.oneal_left3.getFxImage();
                }
                count_img += 4;
                break;
            case 'D':
                location_x += onealSpeed;
                if (count_img % 30 == 0) {
                    this.img = Sprite.oneal_left1.getFxImage();
                } else if (count_img % 30 == 10) {
                    this.img = Sprite.oneal_left2.getFxImage();
                } else if (count_img % 30 == 20) {
                    this.img = Sprite.oneal_left3.getFxImage();
                }
                count_img += 4;
                break;
            case 'W':
                location_y -= onealSpeed;
                if (count_img % 30 == 0) {
                    this.img = Sprite.oneal_left1.getFxImage();
                } else if (count_img % 30 == 10) {
                    this.img = Sprite.oneal_left2.getFxImage();
                } else if (count_img % 30 == 20) {
                    this.img = Sprite.oneal_left3.getFxImage();
                }
                count_img += 4;
                break;
            case 'S':
                location_y += onealSpeed;
                if (count_img % 30 == 0) {
                    this.img = Sprite.oneal_left1.getFxImage();
                } else if (count_img % 30 == 10) {
                    this.img = Sprite.oneal_left2.getFxImage();
                } else if (count_img % 30 == 20) {
                    this.img = Sprite.oneal_left3.getFxImage();
                }
                count_img += 4;
                break;
        }
        this.entityCollide();
        y = getLocation_y();
    }
}
