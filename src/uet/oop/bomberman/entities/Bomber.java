package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyListener;

public class Bomber extends Entity {
    public static int coordinates = Sprite.SCALED_SIZE;
    public static int coordinatesY = Sprite.SCALED_SIZE;
    private int bomberSpeed = 3;
    EventHandler<KeyEvent> keyEvent;

    public void setBomberSpeed(int bomberSpeed) {
        this.bomberSpeed = bomberSpeed;
    }

    public int getBomberSpeed() {
        return bomberSpeed;
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        keyEvent = event -> {
            switch (event.getCode()) {
                case A:
                    if (count_img % 30 == 0) {
                        this.img = Sprite.player_left.getFxImage();
                    } else if (count_img % 30 == 10) {
                        this.img = Sprite.player_left_1.getFxImage();
                    } else if (count_img % 30 == 20) {
                        this.img = Sprite.player_left_2.getFxImage();
                    }
                    count_img += 4;
                    location_x -= bomberSpeed;
                    go = 'A';
                    break;
                case D:
                    if (count_img % 30 == 0) {
                        this.img = Sprite.player_right.getFxImage();
                    } else if (count_img % 30 == 10) {
                        this.img = Sprite.player_right_1.getFxImage();
                    } else if (count_img % 30 == 20) {
                        this.img = Sprite.player_right_2.getFxImage();
                    }
                    count_img += 4;
                    location_x += bomberSpeed;
                    go = 'D';
                    break;
                case S:
                    if (count_img % 30 == 0) {
                        this.img = Sprite.player_down.getFxImage();
                    } else if (count_img % 30 == 10) {
                        this.img = Sprite.player_down_1.getFxImage();
                    } else if (count_img % 30 == 20) {
                        this.img = Sprite.player_down_2.getFxImage();
                    }
                    count_img += 4;
                    location_y += bomberSpeed;
                    go = 'S';
                    break;
                case W:
                    if (count_img % 30 == 0) {
                        this.img = Sprite.player_up.getFxImage();
                    } else if (count_img % 30 == 10) {
                        this.img = Sprite.player_up_1.getFxImage();
                    } else if (count_img % 30 == 20) {
                        this.img = Sprite.player_up_2.getFxImage();
                    }
                    count_img += 4;
                    location_y -= bomberSpeed;
                    go = 'W';
                    break;
                case ENTER:
                    Bomb.placeBomb();
                    break;
            }
        };
        BombermanGame.scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent);
    }

    public void update() {
        //this.entityCollide();
        y = location_y;
        go = ' ';
        coordinates = location_x;
        coordinatesY = location_y;
    }
}
